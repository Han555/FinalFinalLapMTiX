/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.ManpowerEntity;
import entity.PropertyEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hyc528
 */
@Stateless
public class ManpowerBean implements ManpowerBeanLocal {

    @PersistenceContext(unitName = "MTiX-ejbPU")
    private EntityManager em;

    @EJB
    SeatingPlanManagementBeanLocal spm;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<ManpowerEntity> getManpowerInProperty(Long propertyId) {

        PropertyEntity property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT m FROM ManpowerEntity m where m.property=:inProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();

    }

    @Override
    public List<ManpowerEntity> getNonSManpowerInProperty(Long propertyId) {

        PropertyEntity property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT m FROM ManpowerEntity m where m.property=:inProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();

    }

    public PropertyEntity getPropertyById(Long id) {
        PropertyEntity property = em.find(PropertyEntity.class, id);
        return property;
    }

    @Override
    public ManpowerEntity getManpowerById(Long manpowerId) {
        ManpowerEntity manpower = em.find(ManpowerEntity.class, manpowerId);
        return manpower;

    }

    public ManpowerEntity addManpower(String role, Integer number, Boolean standard, Long propertyId) {

        //System.out.println("addManpower() called with manpower ID:" + manpowerId);
        try {
            PropertyEntity property = getPropertyById(propertyId);
            //Manpower manpower = getManpowerById(manpowerId);
            ManpowerEntity manpower = new ManpowerEntity();
            manpower.setStaffRole(role);
            manpower.setNumber(number);
            manpower.setStandard(standard);

            if (standard.equals(Boolean.TRUE)) {
                manpower.setPrice(0);
            }
            manpower.setProperty(property);
            em.persist(manpower);
            em.flush();
            property.getManpower().add(manpower);
            em.merge(property);
            return manpower;
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean editManpower(Long manpowerId, String role, Integer number) {
        System.out.println("editManpower() called with manpower ID:" + manpowerId);
        try {
//            Property property = getPropertyById(id);
            ManpowerEntity manpower = em.getReference(ManpowerEntity.class, manpowerId);
            if (manpower == null) {
                System.out.println("The staff" + manpowerId + "is not found");
                return false;
            }
            manpower.setStaffRole(role);
            manpower.setNumber(number);
//            manpower.setStandard(standard);
//            manpower.setProperty(property);
            em.merge(manpower);
//            property.getManpower().add(manpower);
            System.out.println("Manpower details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the Manpower\n" + ex);
            return false;
        }
    }
    
    

    public Boolean deleteManpowerById(Long manpowerId) {

        System.out.println("deleteManpowerById" + manpowerId);
        try {
//            Manpower manpower = em.find(Manpower.class, manpowerId);
            ManpowerEntity manpower = getManpowerById(manpowerId);
            em.remove(manpower);
            System.out.println("The Staff" + manpowerId + "is deleted successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove the staff:\n" + ex);
            return false;
        }
    }

    public List<ManpowerEntity> getAllManpower() {
        Query q = em.createQuery("SELECT m FROM ManpowerEntity m");
        return q.getResultList();
    }

    @Override
    public ManpowerEntity mSetNoSPrice(Long mid, Integer price) {
        ManpowerEntity m = getManpowerById(mid);
        System.out.println("=======Bean" + price);
        m.setPrice(price);
        em.merge(m);
        em.flush();
        return m;
    }

    @Override
    public List<ManpowerEntity> getAllNonStandardManpowers() {
        Query q = em.createQuery("SELECT * FROM ManpowerEntity m WHERE m.standard = FALSE");
        return q.getResultList();
    }
}
