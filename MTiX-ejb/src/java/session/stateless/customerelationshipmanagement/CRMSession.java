/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.customerelationshipmanagement;

import entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class CRMSession implements CRMSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    @Override
    public UserEntity getUserByEmail(String email) {
        UserEntity customer = em.find(UserEntity.class, email);
        if (customer == null) {
            return null;
        } else {
            return customer;
        }
    }

    @Override
    public Integer getLoyaltyPointsAmount(String email) {
        System.out.println("getLoyaltyPointsAmount() is called");
        UserEntity customer = getUserByEmail(email);
        if (customer == null) {
            return 0;
        } else {
            return customer.getLoyaltyPoints();
        }
    }

    //Link card to card Id
    @Override
    public UserEntity linkCardToMember(String email, String loyaltyCardId) {
        System.out.println("linkCardToMember() is called");
        try {
            Query q = em.createQuery("SELECT c FROM UserEntity c WHERE c.username=:email");
            q.setParameter("email", email);

            UserEntity customer = (UserEntity) q.getSingleResult();
            ArrayList<String> roles = customer.getRoles();
            for (String role : roles) {
                if (role.equals("customer")) {
                    customer.setLoyaltyCardId(loyaltyCardId);
                    em.merge(customer);
                    em.flush();
                    return customer;
                }
            }
            
            return null;
        } catch (Exception ex) {
            System.out.println("linkCardToMember(): Error");
            ex.printStackTrace();
            return null;
        }
    }

    //View the all the information of the customer 
    @Override
    public List<UserEntity> getCustomerInformation(String email) {

        Query q = em.createQuery("SELECT c FROM UserEntity c WHERE c.username = :email");
        q.setParameter("email", email);
        return q.getResultList();

    }

    //Update the customer information via id
    @Override
    public boolean editCustomerInformation(Long userId, String phoneNumber, Integer loyaltyPoints, Double cumulativeSpend) {
        System.out.println("edituser() called with user ID:" + userId);
        try {
            UserEntity customer = em.find(UserEntity.class, userId);
            if (customer == null) {
                System.out.println("Cannot find the customer");
                return false;
            }
            
//            String loyaltyPts = Integer.toString(loyaltyPoints);
//            String cumSpend = Double.toString(cumulativeSpend);

            customer.setMobileNumber(phoneNumber);
            customer.setLoyaltyPoints(loyaltyPoints);
            customer.setCumulativeSpending(cumulativeSpend);

            em.merge(customer);
            em.flush();
            System.out.println("Customer details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the Equipment\n" + ex);
            return false;
        }
    }

    @Override
    public List<UserEntity> getAllCustomers() {
        Query q = em.createQuery("SELECT u FROM UserEntity u");
        List<UserEntity> user1 = q.getResultList();
        List<UserEntity> user2 = new ArrayList();
        for(UserEntity u1: user1){
            ArrayList<String> roles =u1.getRoles();
            for(String role:roles){
                if(role.equals("customer")){
                    if(u1.getFirstName()!=null){
                    user2.add(u1);
                }
                }
            }
        }
        return user2;
    }

}
