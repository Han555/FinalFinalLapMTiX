/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.customerelationshipmanagement;

import entity.UserEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface CRMSessionLocal {
    public UserEntity getUserByEmail(String email);

    public Integer getLoyaltyPointsAmount(String email);

    public UserEntity linkCardToMember(String email, String loyaltyCardId);
//implement after 2nd release, get customer info by email 
    public List<UserEntity> getCustomerInformation(String email);

    public List<UserEntity> getAllCustomers();

    

    public boolean editCustomerInformation(Long userId, String phoneNumber, Integer loyaltyPoints, Double cumulativeSpend);


}
