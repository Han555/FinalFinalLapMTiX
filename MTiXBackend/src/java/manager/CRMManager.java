/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import entity.UserEntity;
import java.util.List;
import session.stateless.customerelationshipmanagement.CRMSessionLocal;

/**
 *
 * @author catherinexiong
 */
public class CRMManager {
    private final CRMSessionLocal crmSessionLocal;

    public CRMManager(CRMSessionLocal crmSessionLocal) {
        this.crmSessionLocal = crmSessionLocal;
    }
    

   

    public List<UserEntity> getAllCustomers() {
        return crmSessionLocal.getAllCustomers();
    }

    public boolean editCustomer(Long id, String phoneNumber, Integer loyaltyPoints, Double cumulativeSpend) {
        //Integer eprice = Integer.getInteger("price");
        return crmSessionLocal.editCustomerInformation(id, phoneNumber, loyaltyPoints, cumulativeSpend);
    }
    
    public UserEntity LinkCardToCustomer (String username, String LoyaltyCardId){
    
    return crmSessionLocal.linkCardToMember(username, LoyaltyCardId);
    }

}
