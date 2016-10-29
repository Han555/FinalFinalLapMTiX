/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.EquipmentEntity;
import entity.Event;
import entity.ManpowerEntity;
import entity.PropertyEntity;
import entity.SubEvent;
import entity.UserEntity;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import util.exception.ReservationConflictException;

/**
 *
 * @author catherinexiong
 */
@Local
public interface ReservePropertyBeanLocal {

   

    

  
    //public List<PropertyEntity> getReservationSearchResult(String visual, String eventScale, String daterange) throws ParseException;

    public List<PropertyEntity> checkRecommendation(List<PropertyEntity> properties, String visual);

    public UserEntity getUserByEmail(String email);

    public Event addNewEventWithSub(String eventName, String eventDescription, String eoEmail);

    public Boolean checkUser(String username);

    public List<Event> getEventReservationByProperty(Long propertyId);

    public List<SubEvent> getSubEventReservationByProperty(Long propertyId);

    //public Event addNewEvent(String eventName, String eventDescription, Date startDateTime, Date endDateTime, Long propertyId, String email);

    //public SubEvent addNewSubEvent(String eventName, Date start, Date end, Long propertyId, Long eId, String email);

    public Event getEventById(Long id);

    public List<EquipmentEntity> saveEquipmentSub(String[] evalues, Long pid, Long seid);

    public List<EquipmentEntity> saveEquipmentEvent(String[] evalues, Long pid, Long seid);

    public List<ManpowerEntity> saveManpowerSub(String[] evalues, Long pid, Long seid);

    public List<ManpowerEntity> saveManpowerEvent(String[] evalues, Long pid, Long seid);

    public Boolean deleteEventReservationById(Long eId);

    public Boolean deleteSubEventReservationById(Long eId);

    public Boolean editEventReservation(Long eId, String name, String des);

    public Boolean editSubEventReservation(Long eId, String name);

    public SubEvent getSubEventById(Long id);

    public List<SubEvent> getListOfSubEvent(Event event);

    public List<PropertyEntity> getAvailableProperties(String eventcate, String eventScale, String daterange) throws ParseException;

    public List<PropertyEntity> getReservationSearchResult(List<PropertyEntity> properties, String eventcate, String eventScale) throws ParseException;

    public Event addNewEvent(String eventName, String eventDescription, Date startDateTime, Date endDateTime, Long propertyId, String email, String type);

    //public SubEvent addNewSubEvent(String eventName, Date start, Date end, Long propertyId, Long eId, String email);

    public SubEvent addNewSubEvent(String eventName, Date start, Date end, Long propertyId, Long eId, String email, String type);


    
}
