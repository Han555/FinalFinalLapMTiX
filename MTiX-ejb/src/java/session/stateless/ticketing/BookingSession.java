/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.ticketing;

import session.stateless.propertymanagement.ReservePropertyBeanLocal;
import entity.Event;
import entity.Promotion;
import entity.PropertyEntity;
import entity.SectionCategoryEntity;
import entity.SectionEntity;
import entity.SessionEntity;
import entity.SessionSeatsInventory;
import entity.ShopCartRecordEntity;
import entity.SubEvent;
import entity.TicketSales;
import entity.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import session.stateless.propertymanagement.SeatingPlanManagementBeanLocal;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class BookingSession implements BookingSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ReservePropertyBeanLocal rpm;
    @EJB
    private SeatingPlanManagementBeanLocal spm;

    @Override
    public List<SessionEntity> getSessionsByEventIdSorted(Long id) {
        Event e = rpm.getEventById(id);

        Query q = em.createQuery("SELECT s FROM SessionEntity s WHERE s.event=:event ORDER BY s.timeStart");
        q.setParameter("event", e);
        return q.getResultList();
    }

    @Override
    public List<SessionEntity> getSessionsBySubeventId(Long id) {
        Event e = rpm.getEventById(id);
        Query q = em.createQuery("SELECT s FROM SessionEntity s WHERE s.subEvent=:subevent");
        q.setParameter("subevent", e);
        return q.getResultList();
    }

    @Override
    public Double getPriceBySessionAndSectionId(Long sessionId, Long sectionId) {
        SectionEntity s = spm.getSectionById(sectionId);
        SessionEntity session = em.find(SessionEntity.class, sessionId);
        SectionCategoryEntity sc = s.getCategory();
        Query q = em.createQuery("SELECT scp.price FROM SessionCategoryPrice scp WHERE scp.category=:category AND scp.session=:session");
        q.setParameter("category", sc);
        q.setParameter("session", session);
        
        return (Double) q.getSingleResult();
    }

    @Override
    public List<SectionEntity> getReservedSectionsBySessionId(Long id) {
        SessionEntity sessionE = em.find(SessionEntity.class, id);
        Date currentD = new Date();

        Query q = em.createQuery("SELECT s FROM SessionSeatsInventory s WHERE s.session=:session AND s.reserveTickets=TRUE");
        q.setParameter("session", sessionE);
        long Hour = 3600 * 1000;
        List<SectionEntity> result = new ArrayList<SectionEntity>();
        for (SessionSeatsInventory s : (List<SessionSeatsInventory>) q.getResultList()) {
            if (s.getReservationEndDate().after(new Date(currentD.getTime() - 24 * Hour))) {
                result.add(s.getSectionEntity());
            }
        }
        return result;
    }

    @Override
    public List<SectionEntity> getClosedSectionsBySessionId(Long id) {
        SessionEntity sessionE = em.find(SessionEntity.class, id);

        Query q = em.createQuery("SELECT s.sectionEntity FROM SessionSeatsInventory s WHERE s.session=:session AND s.stopTicketsSales=TRUE");
        q.setParameter("session", sessionE);
        return q.getResultList();
    }

    public HashMap<Long, List<Double>> getSessionsPricingByEventId(Long id) {
        Event event = em.find(Event.class, id);
        HashMap<Long, List<Double>> map = new HashMap<Long, List<Double>>();
        List<SessionEntity> sessions;
        sessions = getSessionsByEventIdSorted(id);
        for (SessionEntity s : sessions) {
            Long sid = s.getId();
            List<SectionEntity> sections = spm.getAllSectionsInOneProperty(event.getProperty().getId());
            List<Double> prices = new ArrayList<Double>();
            for (SectionEntity sec : sections) {
                prices.add(getPriceBySessionAndSectionId(sid, sec.getId()));
            }
            map.put(sid, prices);
        }
        return map;
    }

    @Override
    public List<Double> getSessionsPricingBySessionId(Long id, String type) {
        SessionEntity s = em.find(SessionEntity.class, id);
        List<Double> prices = new ArrayList<Double>();
        if (type.equals("event")) {
            List<SectionEntity> sections = spm.getAllSectionsInOneProperty(s.getEvent().getProperty().getId());
            System.out.println("=====getSessionsPricingBySessionId: "+s.getEvent().getProperty().getPropertyName());
            for (SectionEntity sec : sections) {
                prices.add(getPriceBySessionAndSectionId(id, sec.getId()));
            }
        } else {
            List<SectionEntity> sections = spm.getAllSectionsInOneProperty(s.getSubEvent().getProperty().getId());

            for (SectionEntity sec : sections) {
                prices.add(getPriceBySessionAndSectionId(id, sec.getId()));
            }
        }
        return prices;
    }

    @Override
    public Collection<Promotion> getPromotionsByEventId(Long id) {
        Event e = em.find(Event.class, id);
        Collection<Promotion> promotions = new ArrayList();
        promotions = e.getPromotions();
        return promotions;

    }

    @Override
    public Collection<Promotion> getPromotionsBySubEventId(Long id) {
        SubEvent e = em.find(SubEvent.class, id);
        Collection<Promotion> promotions = new ArrayList();
        promotions = e.getPromotions();
        return promotions;
    }

    @Override
    public Boolean addToCartByUsernameFree(String username, Long sessionId, Long promotionId, String numOfTickets, String price) {
        try {
            SessionEntity s = em.find(SessionEntity.class, sessionId);
            String receiver;
            if(s.getEvent()== null){
                SubEvent se = s.getSubEvent();
                receiver = se.getUser().getUsername();
            } else {
                Event e = s.getEvent();
                receiver = e.getUser().getUsername();
            }
            String promotion;
            if (promotionId == 0) {
                promotion = "Standard";
            } else {
                Promotion p = em.find(Promotion.class, promotionId);
                promotion = p.getName();
            }
            String eventOrganizer;
            if(s.getEvent()==null){
               
               eventOrganizer =s.getSubEvent().getUser().getUsername();
            } else {
              
               eventOrganizer = s.getEvent().getUser().getUsername();
            }
            Promotion p = em.find(Promotion.class, promotionId);
            Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.username=:username");
            q.setParameter("username", username);
            UserEntity user = (UserEntity) q.getSingleResult();
            Double priceD = Double.valueOf(price);
            Integer number = Integer.valueOf(numOfTickets);
            Double priceTotal = priceD * number;

            ShopCartRecordEntity scre = new ShopCartRecordEntity();
            scre.setSession(s);

            scre.setPromotion(promotion);
            scre.setTicketQuantity(numOfTickets);
            scre.setAmount(String.valueOf(priceTotal));
            scre.setSection(null);
            scre.setSeats(null);
            scre.setPayer(user.getUsername());
            scre.setReceiver(eventOrganizer);
            scre.setPaymentStatus("unpaid");
            scre.setReceiver(receiver);
            if (s.getEvent() == null) {
                scre.setEventName(s.getSubEvent().getName());
            } else {
                scre.setEventName(s.getEvent().getName());
            }
            em.persist(scre);
            em.flush();
            user.getPayments().add(scre);
            em.merge(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
      public Boolean addToCartByUsernameFreeSection(String username, Long sessionId, Long promotionId, String numOfTickets, String price, String section) {
        try {
            SessionEntity s = em.find(SessionEntity.class, sessionId);
            String promotion;
            if (promotionId == 0) {
                promotion = "Standard";
            } else {
                Promotion p = em.find(Promotion.class, promotionId);
                promotion = p.getName();
            }
            PropertyEntity property = new PropertyEntity();
            String eventOrganizer;
            if(s.getEvent()==null){
               property=s.getSubEvent().getProperty();
               eventOrganizer =s.getSubEvent().getUser().getUsername();
            } else {
               property=s.getEvent().getProperty(); 
               eventOrganizer = s.getEvent().getUser().getUsername();
            }
            Integer num = Integer.valueOf(section);
            Query query =em.createQuery("SELECT s FROM SectionEntity s WHERE s.property=:property AND s.numberInProperty=:number");
            query.setParameter("property", property);
            query.setParameter("number", num);
            SectionEntity sec = (SectionEntity) query.getSingleResult();
            //Promotion p = em.find(Promotion.class, promotionId);
            Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.username=:username");
            
            q.setParameter("username", username);
            UserEntity user = (UserEntity) q.getSingleResult();
            Double priceD = Double.valueOf(price);
            Integer number = Integer.valueOf(numOfTickets);
            Double priceTotal = priceD * number;

            ShopCartRecordEntity scre = new ShopCartRecordEntity();
            scre.setSession(s);

            scre.setPromotion(promotion);
            scre.setTicketQuantity(numOfTickets);
            scre.setAmount(String.valueOf(priceTotal));
            scre.setPayer(user.getUsername());
            scre.setReceiver(eventOrganizer);
            scre.setSeats(null);
            scre.setPaymentStatus("unpaid");
            scre.setSection(sec);
            if (s.getEvent() == null) {
                scre.setEventName(s.getSubEvent().getName());
            } else {
                scre.setEventName(s.getEvent().getName());
            }
            em.persist(scre);
            em.flush();
            user.getPayments().add(scre);
            em.merge(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Integer> getTicketSalesBySessionId(Long id, String type) {
        SessionEntity s = em.find(SessionEntity.class, id);
        Collection<TicketSales> sales = s.getTicketSales();
        List<Integer> capacityList = new ArrayList();
        if (type == "event") {
            Event event = s.getEvent();
            List<SectionEntity> sections = spm.getAllSectionsInOneProperty(event.getProperty().getId());
            for (SectionEntity section : sections) {
                Integer capacity = section.getCapacity();
                for (TicketSales ts : sales) {
                    if (ts.getSectionEntity().equals(section)) {
                        capacity -= ts.getTicketQuantity();
                    }
                }
                capacityList.add(capacity);
            }
        } else {

            Event event = s.getEvent();
            List<SectionEntity> sections = spm.getAllSectionsInOneProperty(event.getProperty().getId());
            for (SectionEntity section : sections) {
                Integer capacity = section.getCapacity();
                for (TicketSales ts : sales) {
                    if (ts.getSectionEntity().equals(section)) {
                        capacity -= ts.getTicketQuantity();
                    }
                }
                capacityList.add(capacity);
            }

        }

        return capacityList;

    }

}
