/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.EquipmentEntity;
import entity.PropertyEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class EquipmentBean implements EquipmentBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private SeatingPlanManagementBeanLocal spm;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<EquipmentEntity> getEquipmentInProperty(Long propertyId) {

        PropertyEntity property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT eq FROM EquipmentEntity eq where eq.property=:inProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();

    }

    @Override
    public List<EquipmentEntity> getNonSEquipmentInProperty(Long propertyId) {

        PropertyEntity property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT eq FROM EquipmentEntity eq where eq.property=:inProperty AND eq.standard=:standard");
        query.setParameter("inProperty", property);
        query.setParameter("standard", Boolean.FALSE);
        return query.getResultList();

    }

    @Override
    public PropertyEntity getPropertyById(Long id) {
        PropertyEntity property = em.find(PropertyEntity.class, id);
        return property;

    }

    @Override
    public EquipmentEntity getEquipmentById(Long equipmentId) {
        EquipmentEntity equipment = em.find(EquipmentEntity.class, equipmentId);
        return equipment;

    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public EquipmentEntity addEquipment(String name, String location, Boolean standard, Long propertyId) {
        //  System.out.println("addEquipment() called with equipment ID:" + id);
        try {
            PropertyEntity property = getPropertyById(propertyId);
            // Equipment equipment = getEquipmentById(id);
            EquipmentEntity equipment = new EquipmentEntity();
            equipment.setEquipmentName(name);
            equipment.setLocation(location);
            // equipment.setPrice(price);
            equipment.setStandard(standard);
            if (standard.equals(Boolean.TRUE)) {
                equipment.setPrice(0);
            }
            equipment.setProperty(property);
            em.persist(equipment);
            em.flush();
            property.getEquipments().add(equipment);
            em.merge(property);
            return equipment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean editEquipment(Long equipmentId, String name, String location) {
        System.out.println("editEquipment() called with equipment ID:" + equipmentId);
        try {
            EquipmentEntity equipment = em.getReference(EquipmentEntity.class, equipmentId);
            if (equipment == null) {
                System.out.println("Cannot find equipment");
                return false;
            }
            
            equipment.setEquipmentName(name);
            equipment.setLocation(location);
           
            em.merge(equipment);
            System.out.println("Equipment details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the Equipment\n" + ex);
            return false;
        }
    }

    @Override
    public Boolean deleteEquipmentById(Long equipmentId) {

        System.out.println("deleteEquipmentById" + equipmentId);
        try {
            EquipmentEntity equipment = getEquipmentById(equipmentId);
            em.remove(equipment);
            System.out.println("The equipment" + equipmentId + "is deleted successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove the equipment:\n" + ex);
            return false;
        }
    }

    @Override
    public List<EquipmentEntity> getAllEquipments() {

        Query q = em.createQuery("SELECT e FROM EquipmentEntity e");
        return q.getResultList();

    }

    @Override
    public EquipmentEntity setNoSPrice(Long eid, Integer price) {
        EquipmentEntity e = getEquipmentById(eid);
        System.out.println("=======Bean" + price);
        e.setPrice(price);
        em.merge(e);
        em.flush();
        return e;

    }

    @Override
    public List<EquipmentEntity> getAllNonStandardEquipments() {
        Query q = em.createQuery("SELECT * FROM EquipmentEntity e WHERE e.standard = FALSE");
        return q.getResultList();
    }
    
   /* 
    @Override
    public Equipment editEquipment(Long id,String name,String location){
        Equipment e = getEquipmentById(id);
        System.out.println("=======Bean" + location);
        e.setEquipmentName(name);
        e.setLocation(location);
        em.merge(e);
      //  em.flush();
        return e;
        
    } */
}

//    public boolean setPrice(Long id,Integer price) {
//       try{
//        Equipment equipment = getEquipmentById(id);
//       if(equipment.getStandard()){
//           equipment.setPrice(price);
//        }
//    } cat
//}
