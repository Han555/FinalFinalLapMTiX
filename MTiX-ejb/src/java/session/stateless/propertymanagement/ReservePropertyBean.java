/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.EquipmentEntity;
import entity.Event;
import entity.MaintenanceScheduleEntity;
import entity.ManpowerEntity;
import entity.PropertyEntity;
import entity.SubEvent;
import entity.UserEntity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ReservationConflictException;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class ReservePropertyBean implements ReservePropertyBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private SeatingPlanManagementBeanLocal spm;

    @EJB
    private EquipmentBeanLocal ebl;

    @EJB
    private ManpowerBeanLocal mbl;

    /**
     * ****
     *
     * private methods
     */
    private UserEntity getUserEntityById(Long userId) {
        UserEntity user = em.find(UserEntity.class, userId);
        return user;
    }

    private Boolean checkPropertyConflict(Date startDate, Date endDate, Long propertyId) {
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.property = :property AND e.start <= :endDate AND e.end >= :startDate");
        query.setParameter("property", spm.getPropertyById(propertyId));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List resultList = query.getResultList();
        Query query2 = em.createQuery("SELECT e FROM SubEvent e WHERE e.property = :property AND e.start <= :endDate AND e.end >= :startDate");
        query2.setParameter("property", spm.getPropertyById(propertyId));
        query2.setParameter("startDate", startDate);
        query2.setParameter("endDate", endDate);
        List resultList2 = query2.getResultList();
        if (resultList.isEmpty() && resultList2.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean checkMaintenanceConflict(Date startDate, Date endDate, Long propertyId) {
        List<MaintenanceScheduleEntity> ms = spm.getPropertyById(propertyId).getMaintenanceSchedule();
        for (MaintenanceScheduleEntity m : ms) {
            if (endDate.getTime() >= m.getStartDate().getTime()) {
                if (startDate.getTime() <= m.getEndDate().getTime()) {
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public List<PropertyEntity> checkRecommendation(List<PropertyEntity> properties, String eventcate) {

        List<PropertyEntity> pRListFinal = new ArrayList();

        for (PropertyEntity p : properties) {
            if (eventcate.equals(p.getRecommend())) {
                pRListFinal.add(p);
            }

        }

        return pRListFinal;
    }

    @Override
    public Event getEventById(Long id) {
        return (Event) em.find(Event.class, id);
    }

    @Override
    public SubEvent getSubEventById(Long id) {
        return (SubEvent) em.find(SubEvent.class, id);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        Query query = em.createQuery("SELECT u FROM UserEntity u where u.username=:inEmail");
        query.setParameter("inEmail", email);

        return (UserEntity) query.getSingleResult();

    }

    @Override
    public Boolean checkUser(String username) {
        try {
            System.out.println("check user entry 1" );
            System.out.println("check user username: " + username);
            Query q = em.createQuery("SELECT a FROM UserEntity a"); // WHERE a.username=:name
            System.out.println("check user entry 2" );
            //q.setParameter("name", username);
            //UserEntity user = (UserEntity) q.getSingleResult(); The user will be point to the real user here
            System.out.println("check user entry 3" );
            //em.refresh(user);
            for (Object o: q.getResultList()) {
                UserEntity u = (UserEntity) o;
                System.out.println("User: " + u.getUsername());
                System.out.println("roles: " + u.getRoles().get(0));
                /*if (u.getRoles().get(0).equals("event organizer")) {
                    return true;
                } else {
                    return false;
                }*/
                if(u.getUsername().equals(username)) {
                    if(u.getRoles().get(0).equals("event organizer")) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Event addNewEvent(String eventName, String eventDescription, Date startDateTime, Date endDateTime, Long propertyId, String email,String type) {
        if (!checkPropertyConflict(startDateTime, endDateTime, propertyId) && !checkMaintenanceConflict(startDateTime, endDateTime, propertyId)) {
            UserEntity user = getUserByEmail(email);
            Event event = new Event();
            event.setName(eventName);
            event.setDescription(eventDescription);
            event.setStart(startDateTime);
            event.setEnd(endDateTime);
            event.setHasSubEvent(false);
            event.setType(type);
            event.setProperty(spm.getPropertyById(propertyId));
            event.setUser(user);
            //  event.setStatus("Pending");
            em.persist(event);
            em.flush();
            user.getEvents().add(event);
            em.merge(user);
            return event;
        } else {
            return null;
        }
    }

    @Override
    public SubEvent addNewSubEvent(String eventName, Date start, Date end, Long propertyId, Long eId, String email,String type) {
        if (!checkPropertyConflict(start, end, propertyId) && !checkMaintenanceConflict(start, end, propertyId)) {
            UserEntity user = getUserByEmail(email);
            Event event = getEventById(eId);
            System.out.println(eId + event.getName());
            SubEvent subevent = new SubEvent();
            subevent.setName(eventName);
            // event.setDescription(eventDescription);
            subevent.setStart(start);
            subevent.setEnd(end);
            subevent.setType(type);
            //event.setHasSubEvent(false);
            subevent.setProperty(spm.getPropertyById(propertyId));
            subevent.setUser(user);
            subevent.setEvent(event);
            //  event.setStatus("Pending");
            em.persist(subevent);
            em.flush();
            event.getSubEvents().add(subevent);
            user.getSubEvents().add(subevent);
            em.merge(event);
            em.merge(user);
            return subevent;
        } else {
            return null;
        }
    }

    @Override
    public Event addNewEventWithSub(String eventName, String eventDescription, String eoEmail) {

        UserEntity user = getUserByEmail(eoEmail);
        Event event = new Event();
        event.setName(eventName);
        event.setDescription(eventDescription);
        event.setHasSubEvent(true);

        event.setUser(user);
        //  event.setStatus("Pending");
        em.persist(event);
        em.flush();
        user.getEvents().add(event);
        em.merge(user);
        return event;
    }
    @Override
    public List<PropertyEntity> getAvailableProperties(String eventcate, String eventScale, String daterange) throws ParseException {
        String[] parts = daterange.split("-");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = df.parse(parts[0]);
        Date endDate = df.parse(parts[1]);

        List<PropertyEntity> pList = spm.getAllProperties();
        List<PropertyEntity> aList = new ArrayList();
        for (PropertyEntity p : pList) {
            if ((!checkPropertyConflict(startDate, endDate, p.getId())) && (!checkMaintenanceConflict(startDate, endDate, p.getId()))) {
                aList.add(p);

            }
        }
        return aList;
    }

    @Override
    public List<PropertyEntity> getReservationSearchResult(List<PropertyEntity> properties,String eventcate, String eventScale) throws ParseException {

        List<PropertyEntity> scaleList = new ArrayList();
        if (eventScale.equalsIgnoreCase("SS")) {
            for (PropertyEntity pa : properties) {
                if (pa.getCapacity() <= 3000) {
                    String[] types = pa.getTypes().split(",");
                    for(int i=0;i<types.length;i++){
                        if (eventcate.equals(types[i])) {
                            scaleList.add(pa);
                    }
                    

                            
                        }
                    }
                }
          

        } else if (eventScale.equalsIgnoreCase("MS")) {
            for (PropertyEntity pa : scaleList) {
                if (pa.getCapacity()>3000 && pa.getCapacity() <= 7000) {
                    String[] types = pa.getTypes().split(",");
                    for(int i=0;i<types.length;i++){
                        if (eventcate.equals(types[i])) {
                            scaleList.add(pa);
                    }
                    

                            
                        }
                    }
                }
        } else {
            for (PropertyEntity pa : scaleList) {
                if (pa.getCapacity() > 7000) {
                    String[] types = pa.getTypes().split(",");
                    for(int i=0;i<types.length;i++){
                        if (eventcate.equals(types[i])) {
                    scaleList.add(pa);
                        }
                    

                            
                        }
                    }
                }
        }
        return scaleList;

    
    }

    @Override
    public List<Event> getEventReservationByProperty(Long propertyId) {
        PropertyEntity property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.property = :property");
        query.setParameter("property", property);
        return query.getResultList();

    }

    @Override
    public List<SubEvent> getSubEventReservationByProperty(Long propertyId) {
        PropertyEntity property = spm.getPropertyById(propertyId);
        System.out.println("====getSubRES" + property.getPropertyName());
        Query query = em.createQuery("SELECT e FROM SubEvent e WHERE e.property = :property");
        query.setParameter("property", property);
        return query.getResultList();
    }

    @Override
    public List<EquipmentEntity> saveEquipmentSub(String[] evalues, Long pid, Long seid) {
        if (evalues.length != 0) {
            List<EquipmentEntity> eList = new ArrayList();
            for (int i = 0; i < evalues.length; i++) {
                Long eid = Long.valueOf(evalues[i]);
                EquipmentEntity eq = ebl.getEquipmentById(eid);
                SubEvent se = getSubEventById(seid);
                PropertyEntity p = spm.getPropertyById(pid);
                se.getEquipments().add(eq);
                em.merge(se);

//            eq.getSubEvents().add(se);
//            em.merge(eq);
                eList.add(eq);
            }
            return eList;
        } else {
            return null;
        }

    }

    @Override
    public List<EquipmentEntity> saveEquipmentEvent(String[] evalues, Long pid, Long eventid) {
        if (evalues.length != 0) {
            List<EquipmentEntity> eList = new ArrayList();
            for (int i = 0; i < evalues.length; i++) {
                Long eid = Long.valueOf(evalues[i]);
                EquipmentEntity eq = ebl.getEquipmentById(eid);
                Event e = getEventById(eventid);
                PropertyEntity p = spm.getPropertyById(pid);
                e.getEquipments().add(eq);
                em.merge(e);
//            eq.getEvents().add(e);
//            em.merge(eq);
                eList.add(eq);
            }
            return eList;
        } else {
            return null;
        }

    }

    @Override
    public List<ManpowerEntity> saveManpowerSub(String[] evalues, Long pid, Long seid) {
        if (evalues.length != 0) {
            List<ManpowerEntity> mList = new ArrayList();
            for (int i = 0; i < evalues.length; i++) {
                Long eid = Long.valueOf(evalues[i]);
                ManpowerEntity m = mbl.getManpowerById(eid);
                SubEvent se = getSubEventById(seid);
                PropertyEntity p = spm.getPropertyById(pid);
                se.getManpower().add(m);
                em.merge(se);
//            m.getSubEvents().add(se);
//            em.merge(m);
                mList.add(m);
            }
            return mList;
        } else {
            return null;
        }

    }

    @Override
    public List<ManpowerEntity> saveManpowerEvent(String[] evalues, Long pid, Long eventid) {
        if (evalues.length != 0) {
            List<ManpowerEntity> mList = new ArrayList();
            for (int i = 0; i < evalues.length; i++) {
                Long eid = Long.valueOf(evalues[i]);
                ManpowerEntity m = mbl.getManpowerById(eid);
                Event e = getEventById(eid);
                PropertyEntity p = spm.getPropertyById(pid);
                e.getManpower().add(m);
                em.merge(e);
//            m.getEvents().add(e);
//            em.merge(m);
                mList.add(m);
            }
            return mList;
        } else {
            return null;
        }

    }

    @Override
    public Boolean deleteEventReservationById(Long eId) {

        System.out.println("deleteEventRervationById" + eId);
        try {
            Event event = getEventById(eId);
            em.remove(event);
            System.out.println("The event" + eId + "is deleted successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove the event:\n" + ex);
            return false;
        }
    }

    @Override
    public Boolean deleteSubEventReservationById(Long eId) {

        System.out.println("deleteSubEventRervationById" + eId);
        try {
            SubEvent subevent = getSubEventById(eId);
            em.remove(subevent);
            System.out.println("The sub event" + eId + "is deleted successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove the sub event:\n" + ex);
            return false;
        }
    }

    @Override
    public Boolean editEventReservation(Long eId, String name, String des) {
        System.out.println("editEvent() called with Event ID:" + eId);
        try {
            Event event = getEventById(eId);
            if (event == null) {
                System.out.println("Cannot find event");
                return false;
            }

            event.setName(name);
            event.setDescription(des);

            em.merge(event);
            System.out.println("event details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the event\n" + ex);
            return false;
        }
    }

    @Override
    public Boolean editSubEventReservation(Long eId, String name) {
        System.out.println("editSubEvent() called with Sub Event ID:" + eId);
        try {
            SubEvent subevent = getSubEventById(eId);
            if (subevent == null) {
                System.out.println("Cannot find subevent");
                return false;
            }

            subevent.setName(name);
           // event.setDescription(des);

            em.merge(subevent);
            System.out.println("subevent details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the subevent\n" + ex);
            return false;
        }
    }

    public List<SubEvent> getListOfSubEvent(Event event) {
        Query query = em.createQuery("SELECT e FROM SubEvent e WHERE e.event=:event");
        query.setParameter("event", event);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList;

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
