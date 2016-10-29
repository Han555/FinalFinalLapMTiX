/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import entity.Alert;
import javax.ejb.Stateless;
import entity.Event;
import entity.Promotion;
import entity.PromotionType;
import entity.PropertyEntity;
import entity.SectionCategoryEntity;
import entity.SectionEntity;
import entity.SessionCategoryPrice;
import entity.SessionEntity;
import entity.SessionSeatsInventory;
import entity.SubEvent;
import entity.UserEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Student-ID
 */
@Stateless
public class ProductSession implements ProductSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    Event event;
    SessionEntity session = new SessionEntity();
    SubEvent subEvent;
    UserEntity user;
    int toggle = 1;

    @Override
    public void generateUser() {
        for (int i = 0; i < 10; i++) {
            user = new UserEntity();
            user.createAccount("name" + i, "password" + i, "salt" + i, "9847345" + i);
            em.persist(user);
            em.flush();
        }
        user = new UserEntity();

        for (int i = 1; i <= 3; i++) {
            SectionCategoryEntity section = new SectionCategoryEntity();
            section.createSectionCategory("CAT" + i, i);
            em.persist(section);
            em.flush();
        }

        PromotionType promotionType = new PromotionType();
        promotionType.setName("Credit Card");
        em.persist(promotionType);
        em.flush();

        promotionType = new PromotionType();
        promotionType.setName("Membership");
        em.persist(promotionType);
        em.flush();

        promotionType = new PromotionType();
        promotionType.setName("Ticket Pricing Category");
        em.persist(promotionType);
        em.flush();

        promotionType = new PromotionType();
        promotionType.setName("Volume Discount");
        em.persist(promotionType);
        em.flush();

        promotionType = new PromotionType();
        promotionType.setName("Bundled Event Promotion");
        em.persist(promotionType);
    }

    @Override
    public boolean signIn(String name) {
        try {
            event = new Event();
            subEvent = new SubEvent();
            session = new SessionEntity();
            Query q = em.createQuery("SELECT a FROM UserEntity a WHERE a.username=:name");
            q.setParameter("name", name);
            user = (UserEntity) q.getSingleResult(); //The user will be point to the real user here
            for (int i = 0; i < user.getRoles().size(); i++) {
                if (user.getRoles().get(i).equals("event organizer")) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void createEvent(String name, String equipment, Integer manpower, Date start, Date end) {
        UserEntity u = new UserEntity();
        Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + "is3102mtix@gmail.com" + "'");
        for (Object o : q.getResultList()) {
            u = (UserEntity) o;
            System.out.println(u.getUsername());
            System.out.println(u.getMobileNumber());
        }
        event = new Event();
        event.createEvent(name, equipment, manpower, start, end);
        System.out.println(name + " Event Created");
        event.setUser(u);//Cause they alr login and have the memory here!! NOTE
        em.persist(event);
        u.getEvents().add(event);

        if (toggle == 0) {
            toggle = 1;
        } else {
            toggle = 0;
        }
    }

    @Override
    public void createSubEvent(String name, String equipment, Integer manpower, Date start, Date end) {
        subEvent = new SubEvent();
        subEvent.createSubEvent(name, equipment, manpower, start, end, toggle);
        System.out.println(name + " SubEvent Created");
        subEvent.setEvent(event);
        subEvent.setUser(user);
        em.persist(subEvent);
        event.getSubEvents().add(subEvent);
        event.setHasSubEvent(true);                             //PROBLEM : Ask how come it will become unmanaged?
        em.merge(event);
        user.getSubEvents().add(subEvent);
        System.out.println(event.getHasSubEvent() + " " + event.getId());

        if (toggle == 0) {
            toggle = 1;
        } else {
            toggle = 0;
        }
    }

    @Override
    public List<ArrayList> getEventList() {
        Query q = em.createQuery("SELECT a FROM Event a WHERE a.user.userId=:id");
        q.setParameter("id", user.getUserId());

        List<ArrayList> eventList = new ArrayList();
        ArrayList list;

        for (Object o : q.getResultList()) {
            Event eventEntity = (Event) o;
            list = new ArrayList();
            em.refresh(eventEntity);

            if (!eventEntity.getHasSubEvent()) { //no subEvent
                list.add(eventEntity.getId());
                list.add(eventEntity.getName());
                list.add(eventEntity.getStart());
                list.add(eventEntity.getEnd());
                list.add("event");
                list.add(eventEntity.getProperty().getPropertyName());
                list.add(eventEntity.getProperty().getCategory().size());

                String promotions = "";
                    for (Object obj : eventEntity.getPromotions()) {
                        Promotion promotion = (Promotion) obj;
                        promotions += promotion.getName() + " (" + promotion.getDiscountRate() + "%), ";
                    }

                    if (!promotions.equals("")) {
                        promotions = promotions.substring(0, promotions.length() - 2);
                    }

                list.add(promotions);

                eventList.add(list);
            }
        }

        q = em.createQuery("SELECT a FROM SubEvent a WHERE a.user.userId=:id");
        q.setParameter("id", user.getUserId());

        for (Object o : q.getResultList()) {
            SubEvent subEventEntity = (SubEvent) o;
            em.refresh(subEventEntity);
            list = new ArrayList();
            list.add(subEventEntity.getId());
            list.add(subEventEntity.getName());
            list.add(subEventEntity.getStart());
            list.add(subEventEntity.getEnd());
            list.add("subevent");
            list.add(subEventEntity.getProperty().getPropertyName());
            list.add(subEventEntity.getProperty().getCategory().size());
            
             String promotions = "";
                    for (Object obj : subEventEntity.getPromotions()) {
                        Promotion promotion = (Promotion) obj;
                       promotions += promotion.getName() + " (" + promotion.getDiscountRate() + "%), ";
                    }

                    if (!promotions.equals("")) {
                        promotions = promotions.substring(0, promotions.length() - 2);
                    }

                list.add(promotions);
                
            eventList.add(list);
        }
        return eventList;
    }

    @Override
    public int createSession(String name, ArrayList<Date> start, ArrayList<Date> end, String description, String type, Long id) {

        Integer errorChecking;
        if (type.equals("event")) {
            Event event = em.find(Event.class, id);
            for (int j = 0; j < start.size(); j++) {
                errorChecking = checkEventTime(event, start.get(j), end.get(j));
                if (errorChecking == 0) {
                    return 0;
                }
            }
            for (int i = 0; i < start.size(); i++) {
                session = new SessionEntity();
                session.createSession(name, start.get(i), end.get(i), description);
                session.setEvent(event);
                session.setSubEvent(null);
                em.persist(session);
                event.getSessions().add(session);
                System.out.println("persisted");
                em.flush();
            }
        } else {
            SubEvent subevent = em.find(SubEvent.class, id);
            for (int j = 0; j < start.size(); j++) {
                errorChecking = checkSubEventTime(subevent, start.get(j), end.get(j));
                if (errorChecking == 0) {
                    return 0;
                }
            }
            for (int i = 0; i < start.size(); i++) {
                session = new SessionEntity();
                session.createSession(name, start.get(i), end.get(i), description);
                session.setSubEvent(subevent);
                session.setEvent(null);
                em.persist(session);
                subevent.getSessions().add(session);
                System.out.println("persisted");
                em.flush();
            }
        }

        return 1;

    }

    @Override
    public List<ArrayList> searchEventSessions(Long id, String type) {
        try {
            if (type.equals("event")) {
                List<ArrayList> eventSessions = new ArrayList();
                Event event = em.find(Event.class, id);
                em.refresh(event);

                if (event.getSessions().isEmpty()) {
                    return null;
                } else {
                    for (Object obj : event.getSessions()) {
                        ArrayList attributes = new ArrayList();
                        SessionEntity sessionEntity = (SessionEntity) obj;
                        attributes.add(sessionEntity.getId()); //0
                        attributes.add(sessionEntity.getName()); //1
                        attributes.add(sessionEntity.getDescriptions()); //2

                        String start = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(sessionEntity.getTimeStart());
                        String end = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(sessionEntity.getTimeEnd());

                        attributes.add(start); //3
                        attributes.add(end);  //4
                        attributes.add(sessionEntity.getEvent().getProperty().getId());  //5
                        attributes.add(event.getProperty().getCategory().size()); //6

                        eventSessions.add(attributes);
                    }
                    return eventSessions;
                }
            } else {
                List<ArrayList> subEventSessions = new ArrayList();
                SubEvent subEvent = em.find(SubEvent.class, id);
                em.refresh(subEvent);

                if (subEvent.getSessions().isEmpty()) {
                    return null;
                } else {
                    for (Object obj : subEvent.getSessions()) {
                        ArrayList attributes = new ArrayList();
                        SessionEntity sessionEntity = (SessionEntity) obj;
                        attributes.add(sessionEntity.getId());
                        attributes.add(sessionEntity.getName());
                        attributes.add(sessionEntity.getDescriptions());

                        String start = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(sessionEntity.getTimeStart());
                        String end = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(sessionEntity.getTimeEnd());

                        attributes.add(start);
                        attributes.add(end);
                        attributes.add(sessionEntity.getSubEvent().getProperty().getId());
                        attributes.add(subEvent.getProperty().getCategory().size());

                        subEventSessions.add(attributes);
                    }

                    return subEventSessions;
                }

            }
        } catch (Exception ex) {
            System.out.println("No session for the event");
            return null;
        }
    }

    @Override
    public ArrayList editSessions(Long id, String type) {
        ArrayList attributes = new ArrayList();
        System.out.println(id);

        SessionEntity sessionEntity = em.find(SessionEntity.class, id);

        attributes.add(sessionEntity.getId());
        attributes.add(sessionEntity.getName());
        attributes.add(sessionEntity.getDescriptions());

        String start = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(sessionEntity.getTimeStart());
        String end = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(sessionEntity.getTimeEnd());

        attributes.add(start);
        attributes.add(end);

        if (type.equals("event")) {
            attributes.add(sessionEntity.getEvent().getId());
        } else {
            attributes.add(sessionEntity.getSubEvent().getId());
        }

        attributes.add(type);

        return attributes;
    }

    @Override
    public int writeSession(ArrayList data) {
        try {
            String type = data.get(7).toString();
            Long i = Long.valueOf(data.get(0).toString());
            Long eventId = Long.valueOf(data.get(6).toString());
            String name = data.get(1).toString();
            String desc = data.get(2).toString();

            //Combine date
            String date = data.get(3).toString().substring(6);
            String start = data.get(4).toString() + " " + date;
            String end = data.get(5).toString() + " " + date;

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            Date startDate = formatter.parse(start);
            Date endDate = formatter.parse(end);

            boolean timeError = false;

            if (startDate.after(endDate)) {
                return 0;
            }

            if (type.equals("event")) {
                Event eventEntity = em.find(Event.class, eventId);
                for (Object obj : eventEntity.getSessions()) {
                    SessionEntity sessionEntity = (SessionEntity) obj;
                    if (!sessionEntity.getId().toString().equals(i.toString())) {
                        if (sessionEntity.getTimeStart().before(startDate)) {
                            if (sessionEntity.getTimeEnd().before(startDate)) {
                                System.out.println("Start before start");
                                System.out.println(sessionEntity.getTimeEnd());
                                System.out.println(startDate);
                                System.out.println();
                                timeError = false;
                            } else {
                                timeError = true;
                                System.out.println("Start before start");
                                System.out.println("TIME ERROR");
                                System.out.println("One of the Session : " + sessionEntity.getId() + " " + sessionEntity.getTimeStart() + " "
                                        + sessionEntity.getTimeEnd());
                                System.out.println("Edited Time : " + i + " " + startDate + " " + endDate);
                            }
                        } else if (sessionEntity.getTimeStart().after(startDate)) {
                            if (sessionEntity.getTimeStart().after(endDate)) {
                                System.out.println("End before start");
                                System.out.println(sessionEntity.getTimeStart());
                                System.out.println(endDate);
                                System.out.println();
                                timeError = false;
                            } else {
                                timeError = true;
                                System.out.println("End before start");
                                System.out.println("TIME ERROR");
                                System.out.println("One of the Session : " + sessionEntity.getId() + " " + sessionEntity.getTimeStart() + " "
                                        + sessionEntity.getTimeEnd());
                                System.out.println("Edited Time : " + i + startDate + " " + endDate);

                            }

                        } else {
                            timeError = true;
                        }
                    }
                    if (timeError) {
                        return 0;
                    }
                }
            } else {
                SubEvent subEventEntity = em.find(SubEvent.class, eventId);
                for (Object obj : subEventEntity.getSessions()) {
                    SessionEntity sessionEntity = (SessionEntity) obj;
                    if (!sessionEntity.getId().toString().equals(i.toString())) {
                        if (sessionEntity.getTimeStart().before(startDate)) {
                            if (sessionEntity.getTimeEnd().before(startDate)) {
                                System.out.println("Start before start");
                                System.out.println(sessionEntity.getTimeEnd());
                                System.out.println(startDate);
                                System.out.println();
                                timeError = false;
                            } else {
                                timeError = true;
                                System.out.println("Start before start");
                                System.out.println("TIME ERROR");
                                System.out.println("One of the Session : " + sessionEntity.getId() + " " + sessionEntity.getTimeStart() + " "
                                        + sessionEntity.getTimeEnd());
                                System.out.println("Edited Time : " + i + " " + startDate + " " + endDate);
                            }
                        } else if (sessionEntity.getTimeStart().after(startDate)) {
                            if (sessionEntity.getTimeStart().after(endDate)) {
                                System.out.println("End before start");
                                System.out.println(sessionEntity.getTimeStart());
                                System.out.println(endDate);
                                System.out.println();
                                timeError = false;
                            } else {
                                timeError = true;
                                System.out.println("End before start");
                                System.out.println("TIME ERROR");
                                System.out.println("One of the Session : " + sessionEntity.getId() + " " + sessionEntity.getTimeStart() + " "
                                        + sessionEntity.getTimeEnd());
                                System.out.println("Edited Time : " + i + startDate + " " + endDate);

                            }
                        } else {
                            timeError = true;
                        }

                    }
                    if (timeError) {
                        return 0;
                    }
                }
            }

            SessionEntity sessionEntity = em.find(SessionEntity.class, i);
            sessionEntity.createSession(name, startDate, endDate, desc);
            em.flush();
            return 1;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public void deleteSessions(String[] id) {

        for (int i = 0; i < id.length; i++) {
            SessionEntity sessionEntity = em.find(SessionEntity.class, Long.parseLong(id[i]));
            em.remove(sessionEntity);
        }
    }

    @Override
    public ArrayList getSessionEvent(String type, Long id, int no) {
        ArrayList arr = new ArrayList();
        if (type.equals("event")) {
            Event event = em.find(Event.class, id);
            arr.add((String) new SimpleDateFormat("yyyy-MM-dd").format(event.getStart()));
            arr.add((String) new SimpleDateFormat("yyyy-MM-dd").format(event.getEnd()));;
            arr.add(no);
            arr.add(type);
            arr.add(id);
        } else {
            SubEvent subevent = em.find(SubEvent.class, id);
            arr.add((String) new SimpleDateFormat("yyyy-MM-dd").format(subevent.getStart()));
            arr.add((String) new SimpleDateFormat("yyyy-MM-dd").format(subevent.getEnd()));;
            arr.add(no);
            arr.add(type);
            arr.add(id);
        }
        return arr;
    }

    private Integer checkSubEventTime(SubEvent subEventEntity, Date startDate, Date endDate) {
        Boolean timeError = false;
        for (Object obj : subEventEntity.getSessions()) {
            SessionEntity sessionEntity = (SessionEntity) obj;
            if (sessionEntity.getTimeStart().before(startDate)) {
                if (sessionEntity.getTimeEnd().before(startDate)) {
                    System.out.println("Start before start");
                    System.out.println(sessionEntity.getTimeEnd());
                    System.out.println(startDate);
                    System.out.println();
                    timeError = false;
                } else {
                    timeError = true;
                    System.out.println("Start before start");
                    System.out.println("TIME ERROR");
                    System.out.println("One of the Session : " + sessionEntity.getId() + " " + sessionEntity.getTimeStart() + " "
                            + sessionEntity.getTimeEnd());
                }
            } else if (sessionEntity.getTimeStart().after(startDate)) {
                if (sessionEntity.getTimeStart().after(endDate)) {
                    System.out.println("End before start");
                    System.out.println(sessionEntity.getTimeStart());
                    System.out.println(endDate);
                    System.out.println();
                    timeError = false;
                } else {
                    timeError = true;
                    System.out.println("End before start");
                    System.out.println("TIME ERROR");
                    System.out.println("One of the Session : " + sessionEntity.getId() + " " + sessionEntity.getTimeStart() + " "
                            + sessionEntity.getTimeEnd());

                }
            } else {
                timeError = true;
            }

            if (timeError) {
                return 0;
            }
        }
        return 1;
    }

    private Integer checkEventTime(Event eventEntity, Date startDate, Date endDate) {
        Boolean timeError = false;
        for (Object obj : eventEntity.getSessions()) {
            SessionEntity sessionEntity = (SessionEntity) obj;
            if (sessionEntity.getTimeStart().before(startDate)) {
                if (sessionEntity.getTimeEnd().before(startDate)) {
                    System.out.println("Start before start");
                    System.out.println(sessionEntity.getTimeEnd());
                    System.out.println(startDate);
                    System.out.println();
                    timeError = false;
                } else {
                    timeError = true;
                    System.out.println("Start before start");
                    System.out.println("TIME ERROR");
                    System.out.println("One of the Session : " + sessionEntity.getId() + " " + sessionEntity.getTimeStart() + " "
                            + sessionEntity.getTimeEnd());
                    System.out.println("Edited Time : " + " " + startDate + " " + endDate);
                }
            } else if (sessionEntity.getTimeStart().after(startDate)) {
                if (sessionEntity.getTimeStart().after(endDate)) {
                    System.out.println("End before start");
                    System.out.println(sessionEntity.getTimeStart());
                    System.out.println(endDate);
                    System.out.println();
                    timeError = false;
                } else {
                    timeError = true;
                    System.out.println("End before start");
                    System.out.println("TIME ERROR");
                    System.out.println("One of the Session : " + sessionEntity.getId() + " " + sessionEntity.getTimeStart() + " "
                            + sessionEntity.getTimeEnd());
                    System.out.println("Edited Time : " + startDate + " " + endDate);

                }

            } else {
                timeError = true;
            }

            if (timeError) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int setPricing(Long id, ArrayList<Double> cat, int no, String apply, String seatsOption) {
        SessionEntity session = em.find(SessionEntity.class, id);
        if (session == null) {
            return 0;
        } else {
            if (apply.equals("yes")) {
                if (session.getEvent() != null) { //If it is a event session
                    Long eventID = session.getEvent().getId();
                    Event eventEntity = em.find(Event.class, eventID);
                    for (Object obj : eventEntity.getSessions()) {
                        SessionEntity sessionEntity = (SessionEntity) obj;
                        sessionEntity.setSeatOption(seatsOption);
                        setIndividualPricing(sessionEntity, cat, no, eventEntity.getProperty().getId());
                    }
                } else {
                    Long subEventID = session.getSubEvent().getId(); //If it is a sub event session
                    SubEvent subEvent = em.find(SubEvent.class, subEventID);
                    for (Object obj : subEvent.getSessions()) {
                        SessionEntity sessionEntity = (SessionEntity) obj;
                        sessionEntity.setSeatOption(seatsOption);
                        setIndividualPricing(sessionEntity, cat, no, subEvent.getProperty().getId());
                    }
                }
            } else {
                if (session.getEvent() != null) {
                    long propertyID = session.getEvent().getProperty().getId();
                    session.setSeatOption(seatsOption);
                    setIndividualPricing(session, cat, no, propertyID);
                } else {
                    long propertyID = session.getSubEvent().getProperty().getId();
                    session.setSeatOption(seatsOption);
                    setIndividualPricing(session, cat, no, propertyID);
                }

            }
            return 1;
        }
    }

    private void setIndividualPricing(SessionEntity session, ArrayList<Double> cat, int no, Long propertyID) {
        if (session.getPrice().isEmpty()) { //Never set price at all
            for (int i = 1; i <= no; i++) {
                Query q = em.createQuery("SELECT a FROM SectionCategoryEntity a WHERE a.categoryNum=:cat AND a.property.id=:id");
                q.setParameter("cat", i);
                q.setParameter("id", propertyID);
                SectionCategoryEntity section = (SectionCategoryEntity) q.getSingleResult();

                SessionCategoryPrice price = new SessionCategoryPrice();
                price.setPrice(cat.get(i - 1));
                price.setCategory(section);
                price.setSession(session);
                em.persist(price);
                em.flush();
                session.getPrice().add(price);
            }

        } else {
            for (int i = 1; i <= no; i++) {
                SessionCategoryPrice price;
                Query q = em.createQuery("SELECT a FROM SessionCategoryPrice a WHERE a.category.categoryNum=:cat AND a.session.id=:id");
                q.setParameter("cat", i);
                q.setParameter("id", session.getId());
                price = (SessionCategoryPrice) q.getSingleResult();
                price.setPrice(cat.get(i - 1));
            }
        }
    }

    @Override
    public List<ArrayList> getSessionsPricing(long id, String type) {
        List<ArrayList> sessionsPricing = new ArrayList();
        if (type.equals("event")) {
            Query q = em.createQuery("SELECT a FROM SessionCategoryPrice a where a.session.event.id=:id");
            q.setParameter("id", id);

            for (Object o : q.getResultList()) {
                ArrayList pricing = new ArrayList();
                SessionCategoryPrice priceEntity = (SessionCategoryPrice) o;
                pricing.add(priceEntity.getSession().getId());
                pricing.add(priceEntity.getCategory().getCategoryNum());
                pricing.add(priceEntity.getPrice());
                pricing.add(priceEntity.getSession().getSeatOption());

                sessionsPricing.add(pricing);
            }
        } else {
            Query q = em.createQuery("SELECT a FROM SessionCategoryPrice a where a.session.subEvent.id=:id");
            q.setParameter("id", id);

            for (Object o : q.getResultList()) {
                ArrayList pricing = new ArrayList();
                SessionCategoryPrice priceEntity = (SessionCategoryPrice) o;
                pricing.add(priceEntity.getSession().getId());
                pricing.add(priceEntity.getCategory().getCategoryNum());
                pricing.add(priceEntity.getPrice());
                pricing.add(priceEntity.getSession().getSeatOption());

                sessionsPricing.add(pricing);
            }
        }
        return sessionsPricing;
    }

    @Override
    public ArrayList getPricing(Long id) {
        Query q = em.createQuery("SELECT a FROM SessionCategoryPrice a where a.session.id=:id");
        q.setParameter("id", id);
        ArrayList price = new ArrayList();
        //Table is in order, so do not need to sort the cat in order before getting
        for (Object o : q.getResultList()) {
            SessionCategoryPrice priceEntity = (SessionCategoryPrice) o;
            price.add(priceEntity.getPrice());
        }
        return price;
    }

    @Override
    public void setPromotion_1(String[] info, String type, String name, double discount, String requirement, String desc) {
        if (type.equals("5")) {
            this.setPromotion_2(info, type, name, discount, requirement, desc);
        } else {
            long eventId;
            String eventType;
            long promotionType = Long.valueOf(type);
            Query q = em.createQuery("SELECT a FROM PromotionType a where a.id=:id");
            q.setParameter("id", promotionType);
            PromotionType promotionTypeEntity = (PromotionType) q.getSingleResult();
            Promotion promotion;

            for (int i = 0; i < info.length; i++) {
                String[] idType = info[i].split(" ");
                eventId = Long.valueOf(idType[0]);
                eventType = idType[1];
                promotion = new Promotion();
                promotion.create(name, discount, desc, requirement);
                promotion.getPromotionsType().add(promotionTypeEntity);
                if (eventType.equals("event")) {
                    q = em.createQuery("SELECT a FROM Event a where a.id=:id");
                    q.setParameter("id", eventId);
                    Event eventEntity = (Event) q.getSingleResult();
                    promotion.setEvent(eventEntity);
                    promotion.setSubEvent(null);
                    em.persist(promotion);
                    eventEntity.getPromotions().add(promotion);
                } else {
                    q = em.createQuery("SELECT a FROM SubEvent a where a.id=:id");
                    q.setParameter("id", eventId);
                    SubEvent subEventEntity = (SubEvent) q.getSingleResult();
                    promotion.setEvent(null);
                    promotion.setSubEvent(subEventEntity);
                    em.persist(promotion);
                    subEventEntity.getPromotions().add(promotion);
                }
            }
        }
    }

    private void setPromotion_2(String[] info, String type, String name, double discount, String requirement, String desc) {
        requirement = "";
        long eventId;
        String eventType;
        long promotionType = Long.valueOf(type);
        Query q = em.createQuery("SELECT a FROM PromotionType a where a.id=:id");
        q.setParameter("id", promotionType);
        PromotionType promotionTypeEntity = (PromotionType) q.getSingleResult();
        Promotion promotion;
        String[] idType;

        for (int j = 0; j < info.length; j++) {
            idType = info[j].split(" ");
            eventId = Long.valueOf(idType[0]);
            eventType = idType[1];
            if (eventType.equals("event")) {
                q = em.createQuery("SELECT a FROM Event a where a.id=:id");
                q.setParameter("id", eventId);
                Event eventEntity = (Event) q.getSingleResult();
                requirement += "Event : " + eventEntity.getId() + " ";
            } else {
                q = em.createQuery("SELECT a FROM SubEvent a where a.id=:id");
                q.setParameter("id", eventId);
                SubEvent subEventEntity = (SubEvent) q.getSingleResult();
                requirement += "SubEvent : " + subEventEntity.getId() + " ";
            }
        }

        for (int i = 0; i < info.length; i++) {
            idType = info[i].split(" ");
            eventId = Long.valueOf(idType[0]);
            eventType = idType[1];
            promotion = new Promotion();
            promotion.create(name, discount, desc, requirement);
            promotion.getPromotionsType().add(promotionTypeEntity);
            if (eventType.equals("event")) {
                q = em.createQuery("SELECT a FROM Event a where a.id=:id");
                q.setParameter("id", eventId);
                Event eventEntity = (Event) q.getSingleResult();
                promotion.setEvent(eventEntity);
                promotion.setSubEvent(null);
                em.persist(promotion);
                eventEntity.getPromotions().add(promotion);
            } else {
                q = em.createQuery("SELECT a FROM SubEvent a where a.id=:id");
                q.setParameter("id", eventId);
                SubEvent subEventEntity = (SubEvent) q.getSingleResult();
                promotion.setEvent(null);
                promotion.setSubEvent(subEventEntity);
                em.persist(promotion);
                subEventEntity.getPromotions().add(promotion);
            }
        }
    }

    @Override
    public void setPromotion_3(String[] type, String[] info, String name, double discount, String requirement, String desc) {
        String bundleEvent = "";
        long eventId;
        String eventType;
        String[] idType;
        Query q;
        long promotionType;
        boolean hasBundleEvent = false;
        Promotion promotion;
        for (int j = 0; j < info.length; j++) {
            idType = info[j].split(" ");
            eventId = Long.valueOf(idType[0]);
            eventType = idType[1];
            if (eventType.equals("event")) {
                q = em.createQuery("SELECT a FROM Event a where a.id=:id");
                q.setParameter("id", eventId);
                Event eventEntity = (Event) q.getSingleResult();
                bundleEvent += "Event : " + eventEntity.getId() + " ";
            } else {
                q = em.createQuery("SELECT a FROM SubEvent a where a.id=:id");
                q.setParameter("id", eventId);
                SubEvent subEventEntity = (SubEvent) q.getSingleResult();
                bundleEvent += "SubEvent : " + subEventEntity.getId() + " ";
            }
        }

        for (int i = 0; i < info.length; i++) {
            idType = info[i].split(" ");
            eventId = Long.valueOf(idType[0]);
            eventType = idType[1];
            promotion = new Promotion();
            for (int j = 0; j < type.length; j++) {
                if (type[j].equals("5")) {
                    hasBundleEvent = true;
                }
                promotionType = Long.valueOf(type[j]);
                q = em.createQuery("SELECT a FROM PromotionType a where a.id=:id");
                q.setParameter("id", promotionType);
                PromotionType promotionTypeEntity = (PromotionType) q.getSingleResult();
                promotion.getPromotionsType().add(promotionTypeEntity);
            }

            if (eventType.equals("event")) {
                q = em.createQuery("SELECT a FROM Event a where a.id=:id");
                q.setParameter("id", eventId);
                Event eventEntity = (Event) q.getSingleResult();
                promotion.setEvent(eventEntity);
                promotion.setSubEvent(null);
                if (hasBundleEvent) {
                    promotion.create(name, discount, desc, requirement + " " + bundleEvent);
                } else {
                    promotion.create(name, discount, desc, requirement);
                }
                em.persist(promotion);
                eventEntity.getPromotions().add(promotion);
            } else {
                q = em.createQuery("SELECT a FROM SubEvent a where a.id=:id");
                q.setParameter("id", eventId);
                SubEvent subEventEntity = (SubEvent) q.getSingleResult();
                promotion.setEvent(null);
                promotion.setSubEvent(subEventEntity);
                if (hasBundleEvent) {
                    promotion.create(name, discount, desc, requirement + " " + bundleEvent);
                } else {
                    promotion.create(name, discount, desc, requirement);
                }
                em.persist(promotion);
                subEventEntity.getPromotions().add(promotion);
            }
        }
    }

    @Override
    public int getCategory(Long id, String type) {
        Query q;
        int category = 0;
        SessionEntity sessionEntity;
        SessionCategoryPrice price;
        if (type.equals("event")) {
            q = em.createQuery("SELECT a FROM SessionEntity a where a.event.id=:id");
            q.setParameter("id", id);
            for (Object o : q.getResultList()) {
                sessionEntity = (SessionEntity) o;
                category = sessionEntity.getPrice().size();
                break;
            }
        } else {
            q = em.createQuery("SELECT a FROM SessionEntity a where a.subEvent.id=:id");
            q.setParameter("id", id);
            for (Object o : q.getResultList()) {
                sessionEntity = (SessionEntity) o;
                category = sessionEntity.getPrice().size();
                break;
            }
        }
        return category;
    }

    @Override
    public List<ArrayList> searchEventPromotion(Long id, String type) {
        try {
            System.out.println(type + id + "SEARCHEVENTPROMOTION");
            if (type.equals("event")) {
                List<ArrayList> eventPromotions = new ArrayList();
                Event event = em.find(Event.class, id);
                em.refresh(event);

                if (event.getPromotions().isEmpty()) {
                    return null;
                } else {
                    for (Object obj : event.getPromotions()) {
                        ArrayList attributes = new ArrayList();
                        Promotion promotion = (Promotion) obj;
                        attributes.add(promotion.getId()); //0
                        attributes.add(promotion.getName()); //1
                        attributes.add(promotion.getRequirements()); //2
                        attributes.add(promotion.getDiscountRate());

                        for (Object o : promotion.getPromotionsType()) {
                            PromotionType promotionType = (PromotionType) o;
                            attributes.add(promotionType.getName());
                        }

                        eventPromotions.add(attributes);
                    }
                    return eventPromotions;
                }
            } else {
                List<ArrayList> subEventPromotions = new ArrayList();
                SubEvent subEvent = em.find(SubEvent.class, id);
                em.refresh(subEvent);

                if (subEvent.getPromotions().isEmpty()) {
                    return null;
                } else {
                    for (Object obj : subEvent.getPromotions()) {
                        ArrayList attributes = new ArrayList();
                        Promotion promotion = (Promotion) obj;
                        attributes.add(promotion.getId()); //0
                        attributes.add(promotion.getName()); //1
                        attributes.add(promotion.getRequirements()); //2
                        attributes.add(promotion.getDiscountRate());

                        for (Object o : promotion.getPromotionsType()) {
                            PromotionType promotionType = (PromotionType) o;
                            attributes.add(promotionType.getName());
                        }

                        subEventPromotions.add(attributes);
                    }
                    return subEventPromotions;
                }

            }
        } catch (Exception ex) {
            System.out.println("No session for the event");
            return null;
        }
    }

    @Override
    public ArrayList editPromotion(Long id, String type) {
        ArrayList attributes = new ArrayList();

        Promotion promotionEntity = em.find(Promotion.class, id);

        if (type.equals("event")) {
            attributes.add(promotionEntity.getEvent().getId());
        } else {
            attributes.add(promotionEntity.getSubEvent().getId());
        }

        attributes.add(type);

        attributes.add(promotionEntity.getId());
        attributes.add(promotionEntity.getName());
        attributes.add(promotionEntity.getRequirements());
        attributes.add(promotionEntity.getDiscountRate());
        attributes.add(promotionEntity.getDescriptions());

        for (Object o : promotionEntity.getPromotionsType()) {
            PromotionType promotionType = (PromotionType) o;
            attributes.add(promotionType.getName());
        }
        return attributes;
    }

    @Override
    public void writePromotion(ArrayList data) {
        long eventId = Long.valueOf(data.get(0).toString());
        String eventType = data.get(1).toString();

        Query q = em.createQuery("SELECT a FROM Promotion a where a.id=:id");
        q.setParameter("id", Long.valueOf(data.get(2).toString()));
        Promotion promotionEntity = (Promotion) q.getSingleResult();
        promotionEntity.create(data.get(6).toString(), Double.valueOf(data.get(4).toString()), data.get(3).toString(), data.get(5).toString());
    }

    @Override
    public void deletePromotion(String[] id) {

        for (int i = 0; i < id.length; i++) {
            Promotion promotionEntity = em.find(Promotion.class, Long.parseLong(id[i]));
            em.remove(promotionEntity);
        }
    }

    @Override
    public Date getEventStartDate(long id, String type) {
        if (type.equals("event")) {
            Query q = em.createQuery("SELECT a FROM Event a WHERE a.id=:id");
            q.setParameter("id", id);
            Event event = (Event) q.getSingleResult();
            return event.getStart();
        } else {
            Query q = em.createQuery("SELECT a FROM SubEvent a WHERE a.id=:id");
            q.setParameter("id", id);
            SubEvent subevent = (SubEvent) q.getSingleResult();
            return subevent.getStart();
        }
    }

    @Override
    public Date getEventEndDate(long id, String type) {
        if (type.equals("event")) {
            Query q = em.createQuery("SELECT a FROM Event a WHERE a.id=:id");
            q.setParameter("id", id);
            Event event = (Event) q.getSingleResult();
            return event.getEnd();
        } else {
            Query q = em.createQuery("SELECT a FROM SubEvent a WHERE a.id=:id");
            q.setParameter("id", id);
            SubEvent subevent = (SubEvent) q.getSingleResult();
            return subevent.getEnd();
        }
    }

    @Override
    public void setReserveSection(String apply, long sessionID, String purpose, String date, String sectionIDs) {
        Query q = em.createQuery("SELECT a FROM SessionEntity a WHERE a.id=:id");
        q.setParameter("id", sessionID);
        SessionEntity sessionEntity = (SessionEntity) q.getSingleResult();
        long propertyID;
        SectionEntity sectionEntity;
        String[] sectionID = sectionIDs.split(" ");

        if (apply.equals("yes")) {
            if (sessionEntity.getEvent() != null) { //If it is a event session
                Long eventID = sessionEntity.getEvent().getId();
                Event eventEntity = em.find(Event.class, eventID);
                propertyID = eventEntity.getProperty().getId();
                for (Object obj : eventEntity.getSessions()) {
                    sessionEntity = (SessionEntity) obj;
                    for (int i = 0; i < sectionID.length; i++) {
                        q = em.createQuery("SELECT a FROM SectionEntity a WHERE a.property.id=:id AND a.numberInProperty=:sectionID");
                        q.setParameter("id", propertyID);
                        q.setParameter("sectionID", Long.valueOf(sectionID[i]));
                        sectionEntity = (SectionEntity) q.getSingleResult();

                        this.setSection(sectionEntity, sessionEntity, purpose, date);
                        em.flush();
                    }

                }
            } else {
                Long eventID = sessionEntity.getSubEvent().getId();
                SubEvent eventEntity = em.find(SubEvent.class, eventID);
                propertyID = eventEntity.getProperty().getId();
                for (Object obj : eventEntity.getSessions()) {
                    sessionEntity = (SessionEntity) obj;
                    for (int i = 0; i < sectionID.length; i++) {
                        q = em.createQuery("SELECT a FROM SectionEntity a WHERE a.property.id=:id AND a.numberInProperty=:sectionID");
                        q.setParameter("id", propertyID);
                        q.setParameter("sectionID", Long.valueOf(sectionID[i]));
                        sectionEntity = (SectionEntity) q.getSingleResult();

                        this.setSection(sectionEntity, sessionEntity, purpose, date);
                        em.flush();
                    }
                }
            }
        } else {
            if (sessionEntity.getEvent() != null) { //Link to event
                propertyID = sessionEntity.getEvent().getProperty().getId();
                for (int i = 0; i < sectionID.length; i++) {
                    q = em.createQuery("SELECT a FROM SectionEntity a WHERE a.property.id=:id AND a.numberInProperty=:sectionID");
                    q.setParameter("id", propertyID);
                    q.setParameter("sectionID", Long.valueOf(sectionID[i]));
                    System.out.println(propertyID + " +++++++++++++++++++++ " + sectionID[i]);
                    sectionEntity = (SectionEntity) q.getSingleResult();

                    this.setSection(sectionEntity, sessionEntity, purpose, date);
                }

            } else {
                propertyID = sessionEntity.getSubEvent().getProperty().getId();
                for (int i = 0; i < sectionID.length; i++) {
                    q = em.createQuery("SELECT a FROM SectionEntity a WHERE a.property.id=:id AND a.numberInProperty=:sectionID");
                    q.setParameter("id", propertyID);
                    q.setParameter("sectionID", Long.valueOf(sectionID[i]));
                    sectionEntity = (SectionEntity) q.getSingleResult();

                    this.setSection(sectionEntity, sessionEntity, purpose, date);
                }
            }
        }

    }

    private void setSection(SectionEntity sectionEntity, SessionEntity sessionEntity, String purpose, String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = formatter.parse(date);
            SessionSeatsInventory reservedSeatsInventory = new SessionSeatsInventory();
            boolean reserved = false;
            em.refresh(sessionEntity);
            for (Object o : sessionEntity.getSeatsInventory()) {
                SessionSeatsInventory seatsInventory = (SessionSeatsInventory) o;
                System.out.println("Enter");
                System.out.println(seatsInventory.getSectionEntity().getId() + "     " + sectionEntity.getId());
                em.refresh(seatsInventory);
                if (seatsInventory.getSectionEntity().getId() == sectionEntity.getId()) { //If there are such data in the entity
                    reserved = true;
                    reservedSeatsInventory = seatsInventory;
                    if (seatsInventory.getStopTicketsSales()) { //Overide the data from stop sales to reserve ticket sales
                        reservedSeatsInventory.setReserveTickets(true);
                        reservedSeatsInventory.setStopTicketsSales(false);
                    }
                    break;
                }
            }

            if (reserved) { //There is such reserved section
                reservedSeatsInventory.setReason(purpose);
                reservedSeatsInventory.setReservationEndDate(endDate);
            } else {
                SessionSeatsInventory inventory = new SessionSeatsInventory();
                inventory.setReason(purpose);
                inventory.setReserveTickets(true);
                inventory.setStopTicketsSales(false);
                inventory.setReservationEndDate(endDate);
                inventory.setSectionEntity(sectionEntity);
                inventory.setSession(sessionEntity);
                em.persist(inventory);
                session.getSeatsInventory().add(inventory);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<ArrayList> getReservedSections(long id, String type) {
        List<ArrayList> sessionsInventory = new ArrayList();
        if (type.equals("event")) {
            Event event = em.find(Event.class, id);

            for (Object o : event.getSessions()) {
                SessionEntity session = (SessionEntity) o;
                Query q = em.createQuery("SELECT a FROM SessionSeatsInventory a where a.session.id=:id AND a.reserveTickets=:reserved");
                q.setParameter("id", session.getId());
                q.setParameter("reserved", true);
                ArrayList sessionInventory = new ArrayList();
                sessionInventory.add(session.getId()); //For those that doesnt has reserved section, they would only has 1 attribute

                for (Object obj : q.getResultList()) {
                    SessionSeatsInventory inventory = (SessionSeatsInventory) obj;
                    sessionInventory.add(inventory.getSectionEntity().getNumberInProperty()); //2
                    sessionInventory.add(inventory.getReason()); //3
                    sessionInventory.add(inventory.getReservationEndDate()); //4
                }

                sessionsInventory.add(sessionInventory);
            }
        } else {
            SubEvent subevent = em.find(SubEvent.class, id);

            for (Object o : subevent.getSessions()) {
                SessionEntity session = (SessionEntity) o;
                Query q = em.createQuery("SELECT a FROM SessionSeatsInventory a where a.session.id=:id AND a.reserveTickets=:reserved");
                q.setParameter("id", session.getId());
                q.setParameter("reserved", true);
                ArrayList sessionInventory = new ArrayList();
                sessionInventory.add(session.getId()); //For those that doesnt has reserved section, they would only has 1 attribute

                for (Object obj : q.getResultList()) {
                    SessionSeatsInventory inventory = (SessionSeatsInventory) obj;
                    sessionInventory.add(inventory.getSectionEntity().getNumberInProperty());
                    sessionInventory.add(inventory.getReason()); //3
                    sessionInventory.add(inventory.getReservationEndDate()); //4
                }

                sessionsInventory.add(sessionInventory);
            }

        }

        return sessionsInventory;
    }

    @Override
    public List<ArrayList> getSessionReservedSections(long id) {
        SessionEntity session = em.find(SessionEntity.class, id);
        List<ArrayList> sessionsInventory = new ArrayList();
        em.refresh(session);

        for (Object o : session.getSeatsInventory()) {
            SessionSeatsInventory inventory = (SessionSeatsInventory) o;
            if (inventory.getReserveTickets()) {
                ArrayList sessionInventory = new ArrayList();
                sessionInventory.add(inventory.getId()); //0
                sessionInventory.add(inventory.getSectionEntity().getNumberInProperty()); //1
                sessionInventory.add(inventory.getReason()); //2
                sessionInventory.add(inventory.getReservationEndDate()); //3
                sessionsInventory.add(sessionInventory);
            }
        }
        return sessionsInventory;
    }
   

    public long getPropertyID(long id) { //sessionID
        SessionEntity session = em.find(SessionEntity.class, id);
        if (session.getEvent() != null) {
            return session.getEvent().getProperty().getId();
        } else {
            return session.getSubEvent().getProperty().getId();
        }
    }

    @Override
    public void deleteSessionReservedSections(String[] id) {

        for (int i = 0; i < id.length; i++) {
            SessionSeatsInventory inventory = em.find(SessionSeatsInventory.class, Long.valueOf(id[i]));
            em.remove(inventory);
        }
    }

    @Override
    public List<ArrayList> getClosedSections(long id, String type) {
        List<ArrayList> sessionsInventory = new ArrayList();
        if (type.equals("event")) {
            Event event = em.find(Event.class, id);

            for (Object o : event.getSessions()) {
                SessionEntity session = (SessionEntity) o;
                Query q = em.createQuery("SELECT a FROM SessionSeatsInventory a where a.session.id=:id AND a.stopTicketsSales=:close");
                q.setParameter("id", session.getId());
                q.setParameter("close", true);
                ArrayList sessionInventory = new ArrayList();
                sessionInventory.add(session.getId()); //For those that doesnt has reserved section, they would only has 1 attribute

                for (Object obj : q.getResultList()) {
                    SessionSeatsInventory inventory = (SessionSeatsInventory) obj;
                    sessionInventory.add(inventory.getSectionEntity().getNumberInProperty()); //2
                    sessionInventory.add(inventory.getReason()); //3
                }
                sessionsInventory.add(sessionInventory);
            }
        } else {
            SubEvent subevent = em.find(SubEvent.class, id);

            for (Object o : subevent.getSessions()) {
                SessionEntity session = (SessionEntity) o;
                Query q = em.createQuery("SELECT a FROM SessionSeatsInventory a where a.session.id=:id AND a.stopTicketsSales=:close");
                q.setParameter("id", session.getId());
                q.setParameter("close", true);
                ArrayList sessionInventory = new ArrayList();
                sessionInventory.add(session.getId()); //For those that doesnt has reserved section, they would only has 1 attribute

                for (Object obj : q.getResultList()) {
                    SessionSeatsInventory inventory = (SessionSeatsInventory) obj;
                    sessionInventory.add(inventory.getSectionEntity().getNumberInProperty());
                    sessionInventory.add(inventory.getReason()); //3
                }
                sessionsInventory.add(sessionInventory);
            }
        }
        return sessionsInventory;
    }

    @Override
    public void setCloseSections(String apply, long sessionID, String purpose, String sectionIDs) {
        Query q = em.createQuery("SELECT a FROM SessionEntity a WHERE a.id=:id");
        q.setParameter("id", sessionID);
        SessionEntity sessionEntity = (SessionEntity) q.getSingleResult();
        long propertyID;
        SectionEntity sectionEntity;
        String[] sectionID = sectionIDs.split(" ");

        if (apply.equals("yes")) {
            if (sessionEntity.getEvent() != null) { //If it is a event session
                Long eventID = sessionEntity.getEvent().getId();
                Event eventEntity = em.find(Event.class, eventID);
                propertyID = eventEntity.getProperty().getId();
                for (Object obj : eventEntity.getSessions()) {
                    sessionEntity = (SessionEntity) obj;
                    for (int i = 0; i < sectionID.length; i++) {
                        q = em.createQuery("SELECT a FROM SectionEntity a WHERE a.property.id=:id AND a.numberInProperty=:sectionID");
                        q.setParameter("id", propertyID);
                        q.setParameter("sectionID", Long.valueOf(sectionID[i]));
                        sectionEntity = (SectionEntity) q.getSingleResult();

                        this.setCloseSection(sectionEntity, sessionEntity, purpose);
                        em.flush();
                    }

                }
            } else {
                Long eventID = sessionEntity.getSubEvent().getId();
                SubEvent eventEntity = em.find(SubEvent.class, eventID);
                propertyID = eventEntity.getProperty().getId();
                for (Object obj : eventEntity.getSessions()) {
                    sessionEntity = (SessionEntity) obj;
                    for (int i = 0; i < sectionID.length; i++) {
                        q = em.createQuery("SELECT a FROM SectionEntity a WHERE a.property.id=:id AND a.numberInProperty=:sectionID");
                        q.setParameter("id", propertyID);
                        q.setParameter("sectionID", Long.valueOf(sectionID[i]));
                        sectionEntity = (SectionEntity) q.getSingleResult();

                        this.setCloseSection(sectionEntity, sessionEntity, purpose);
                        em.flush();
                    }
                }
            }
        } else {
            if (sessionEntity.getEvent() != null) { //Link to event
                propertyID = sessionEntity.getEvent().getProperty().getId();
                for (int i = 0; i < sectionID.length; i++) {
                    q = em.createQuery("SELECT a FROM SectionEntity a WHERE a.property.id=:id AND a.numberInProperty=:sectionID");
                    q.setParameter("id", propertyID);
                    q.setParameter("sectionID", Long.valueOf(sectionID[i]));
                    System.out.println(propertyID + " +++++++++++++++++++++ " + sectionID[i]);
                    sectionEntity = (SectionEntity) q.getSingleResult();

                    this.setCloseSection(sectionEntity, sessionEntity, purpose);
                }

            } else {
                propertyID = sessionEntity.getSubEvent().getProperty().getId();
                for (int i = 0; i < sectionID.length; i++) {
                    q = em.createQuery("SELECT a FROM SectionEntity a WHERE a.property.id=:id AND a.numberInProperty=:sectionID");
                    q.setParameter("id", propertyID);
                    q.setParameter("sectionID", Long.valueOf(sectionID[i]));
                    sectionEntity = (SectionEntity) q.getSingleResult();

                    this.setCloseSection(sectionEntity, sessionEntity, purpose);
                }
            }
        }

    }

    private void setCloseSection(SectionEntity sectionEntity, SessionEntity sessionEntity, String purpose) {
        try {
            SessionSeatsInventory reservedSeatsInventory = new SessionSeatsInventory();
            boolean reserved = false;
            em.refresh(sessionEntity);
            for (Object o : sessionEntity.getSeatsInventory()) {
                SessionSeatsInventory seatsInventory = (SessionSeatsInventory) o;
                em.refresh(seatsInventory);
                if (seatsInventory.getSectionEntity().getId() == sectionEntity.getId()) { //If there are such data in the entity
                    reserved = true;
                    reservedSeatsInventory = seatsInventory;
                    if (seatsInventory.getReserveTickets()) { //Overide the data from stop sales to reserve ticket sales
                        reservedSeatsInventory.setReserveTickets(false);
                        reservedSeatsInventory.setStopTicketsSales(true);
                        reservedSeatsInventory.setReservationEndDate(null);
                    }
                    break;
                }
            }

            if (reserved) { //There is such reserved section
                reservedSeatsInventory.setReason(purpose);
            } else {
                SessionSeatsInventory inventory = new SessionSeatsInventory();
                inventory.setReason(purpose);
                inventory.setReserveTickets(false);
                inventory.setStopTicketsSales(true);
                inventory.setSectionEntity(sectionEntity);
                inventory.setSession(sessionEntity);
                em.persist(inventory);
                session.getSeatsInventory().add(inventory);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ArrayList> getSessionClosedSections(long id) {
        SessionEntity session = em.find(SessionEntity.class, id);
        List<ArrayList> sessionsInventory = new ArrayList();
        em.refresh(session);

        for (Object o : session.getSeatsInventory()) {
            SessionSeatsInventory inventory = (SessionSeatsInventory) o;
            if (inventory.getStopTicketsSales()) {
                ArrayList sessionInventory = new ArrayList();
                sessionInventory.add(inventory.getId()); //0
                sessionInventory.add(inventory.getSectionEntity().getNumberInProperty()); //1
                sessionInventory.add(inventory.getReason()); //2
                sessionsInventory.add(sessionInventory);
            }
        }
        return sessionsInventory;
    }

    @Override
    public void createAlert(String apply, long sessionID, String type, String startDate, String endDate, int sales, String inCharge) {
        SessionEntity session = em.find(SessionEntity.class, sessionID);
        if (apply.equals("yes")) {
            if (session.getEvent() != null) { //If it is a event session
                Long eventID = session.getEvent().getId();
                Event eventEntity = em.find(Event.class, eventID);
                em.refresh(eventEntity);
                for (Object obj : eventEntity.getSessions()) {
                    SessionEntity sessionEntity = (SessionEntity) obj;
                    this.createIndividualAlert(sessionEntity, sales, type, inCharge, startDate, endDate);
                    em.flush();
                }
            } else {
                Long subEventID = session.getSubEvent().getId(); //If it is a sub event session
                SubEvent subEvent = em.find(SubEvent.class, subEventID);
                em.refresh(subEvent);
                for (Object obj : subEvent.getSessions()) {
                    SessionEntity sessionEntity = (SessionEntity) obj;
                    this.createIndividualAlert(sessionEntity, sales, type, inCharge, startDate, endDate);
                }
            }
        } else {
            this.createIndividualAlert(session, sales, type, inCharge, startDate, endDate);
        }
    }

    private void createIndividualAlert(SessionEntity sessionEntity, int percentage, String alertType, String email, String startDate, String endDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date start = formatter.parse(startDate);
            Date end = formatter.parse(endDate);
            if (alertType.equals("1")) {
                alertType = "Informative Alert";
            } else if (alertType.equals("2")) {
                alertType = "Important Alert";
            } else {
                alertType = "Urgent Alert";
            }

            if (sessionEntity.getAlert() != null) {
                long alertID = sessionEntity.getAlert().getId();
                Alert alertEntity = em.find(Alert.class, alertID);
                alertEntity.createAlert(percentage, alertType, email, start, end);

            } else {
                Alert alert = new Alert();
                alert.createAlert(percentage, alertType, email, start, end);
                alert.setSession(sessionEntity);
                em.persist(alert);
                sessionEntity.setAlert(alert);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ArrayList> getAlerts(long id, String type) {
        List<ArrayList> sessionsAlert = new ArrayList();
        if (type.equals("event")) {
            Event event = em.find(Event.class, id);

            for (Object o : event.getSessions()) {
                SessionEntity session = (SessionEntity) o;
                em.refresh(session);

                if (session.getAlert() != null) {
                    ArrayList alert = new ArrayList();
                    alert.add(session.getId());
                    alert.add(session.getAlert().getSales());
                    alert.add(session.getAlert().getAlertType());
                    alert.add(session.getAlert().getInChargePersonEmail());
                    alert.add(session.getAlert().getAlertStartDate());
                    alert.add(session.getAlert().getAlertEndDate());
                    sessionsAlert.add(alert);
                }
            }
        } else {
            SubEvent subevent = em.find(SubEvent.class, id);

            for (Object o : subevent.getSessions()) {
                SessionEntity session = (SessionEntity) o;
                em.refresh(session);

                if (session.getAlert() != null) {
                    ArrayList alert = new ArrayList();
                    alert.add(session.getId());
                    alert.add(session.getAlert().getSales());
                    alert.add(session.getAlert().getAlertType());
                    alert.add(session.getAlert().getInChargePersonEmail());
                    alert.add(session.getAlert().getAlertStartDate());
                    alert.add(session.getAlert().getAlertEndDate());
                    sessionsAlert.add(alert);
                }
            }
        }
        return sessionsAlert;
    }
    
    @Override
    public ArrayList getEventOrganizersEmail(){
        ArrayList userEmail = new ArrayList();
        Query q = em.createQuery("SELECT a FROM UserEntity a");
        boolean isEventOrganizer = false;
        for (Object o: q.getResultList()){
            UserEntity user = (UserEntity) o;
             for (int i = 0; i < user.getRoles().size(); i++) {
                if (user.getRoles().get(i).equals("event organizer")) {
                    userEmail.add(user.getUsername());
                    break;
                }
            }
        }
        return userEmail;
    }
}
