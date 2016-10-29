/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import java.util.Date;
import entity.MaintenanceScheduleEntity;
import entity.PropertyEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MaintenanceBean implements MaintenanceBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    SeatingPlanManagementBeanLocal spm;

    @Override
    public MaintenanceScheduleEntity getMaintenanceScheduleById(Long id) {
        return (MaintenanceScheduleEntity) em.find(MaintenanceScheduleEntity.class, id);
    }

    private List getPropertyConflictList(Date startDate, Date endDate, Long propertyId) {
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.property = :property AND e.start <= :endDate AND e.end >= :startDate");
        query.setParameter("property", spm.getPropertyById(propertyId));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List resultList = query.getResultList();
        Query query2 = em.createQuery("SELECT e FROM SubEvent e WHERE e.property = :property AND e.start <= :endDate AND e.end >= :startDate");
        query2.setParameter("property", spm.getPropertyById(propertyId));
        query2.setParameter("startDate", startDate);
        query2.setParameter("endDate", endDate);
        resultList.addAll(query2.getResultList());
        return resultList;
    }
    
    private Boolean checkPropertyConflict(Date startDate, Date endDate, Long propertyId) {
        
        if (getPropertyConflictList(startDate, endDate, propertyId).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean checkMaintenanceConflict(Date startDate, Date endDate, Long propertyId) {
        if (getMaintenanceConflictList(startDate, endDate, propertyId).isEmpty()) {
            return false;
        } else {
            return true;
        }

    }
    
    private List<MaintenanceScheduleEntity> getMaintenanceConflictList (Date startDate, Date endDate, Long propertyId) {
        List<MaintenanceScheduleEntity> conflict = new ArrayList<MaintenanceScheduleEntity>();
        List<MaintenanceScheduleEntity> ms = spm.getPropertyById(propertyId).getMaintenanceSchedule();
        for (MaintenanceScheduleEntity m : ms) {
            if (endDate.getTime() >= m.getStartDate().getTime()) {
                if (startDate.getTime() <= m.getEndDate().getTime()) {
                    conflict.add(m);
                }
            }
        }
        
        return conflict;
    }
    

    @Override
    public Boolean addMaintenance(Long propertyId, Date start, Date end) {
        
        try {
            if (!checkMaintenanceConflict(start, end, propertyId)) {
                if (!checkPropertyConflict(start, end, propertyId)) {
                    System.out.println("=====================no conflict");
                    PropertyEntity property = spm.getPropertyById(propertyId);
                    MaintenanceScheduleEntity ms = new MaintenanceScheduleEntity();
                    ms.setEndDate(end);
                    ms.setStartDate(start);
                    ms.setProperty(spm.getPropertyById(propertyId));

                    em.persist(ms);
                    System.out.println("=====================persisted");
                    em.flush();
                    property.getMaintenanceSchedule().add(ms);
                    em.merge(property);
                } else return false;
            } else return false;
        } catch (Exception e) {
            
            System.out.println("EXCEPTION");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Boolean removeMaintenance(Long id) {
        Boolean result = true;
        try {
            em.remove(getMaintenanceScheduleById(id));
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    @Override
    public Boolean updateMaintenance(Long mid, Long propertyId, Date start, Date end) {

        Boolean result = false;
        try {
            
            List<MaintenanceScheduleEntity> conflicts = getMaintenanceConflictList(start, end, propertyId);
            if (conflicts.size() == 0 || (conflicts.size() == 1 && conflicts.get(0).getId() == mid)) {
                MaintenanceScheduleEntity ms = getMaintenanceScheduleById(mid);
                ms.setEndDate(end);
                ms.setStartDate(start);

                em.merge(ms);

                result = true;
            }
        } catch (Exception e) {
            result = false;
        }

        return result;

    }

    @Override
    public List<MaintenanceScheduleEntity> getMaintenanceInProperty(Long propertyId) {
        PropertyEntity property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT ms FROM MaintenanceScheduleEntity ms where ms.property=:inProperty");
        query.setParameter("inProperty", property);
        List<MaintenanceScheduleEntity> res = query.getResultList();
        System.out.println("===============" + res.size());
        return res;
    }

}
