/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import session.stateless.propertymanagement.FoodOutletBeanLocal;
import entity.FoodOutletEntity;
import java.util.List;

/**
 *
 * @author hyc528
 */
public class FoodOutletManager {
    
private final FoodOutletBeanLocal foodoutletBeanLocal;

    public FoodOutletManager(FoodOutletBeanLocal fboutletManagementBeanLocal) {
        this.foodoutletBeanLocal = fboutletManagementBeanLocal;
    }

    public List<FoodOutletEntity> getAllFoodOutlet() {
        return foodoutletBeanLocal.getAllFoodOutlet();
    }

    public FoodOutletEntity createNewFoodOutlet(String oname, String otype, String odes, Long propertyId) {
        //Integer eprice = Integer.getInteger("price");
        return foodoutletBeanLocal.addFoodOutlet(oname, otype, odes, propertyId);
    }
    
    public boolean editFoodOutlet(String oname, String otype, String odes, Long id ) {
        //Integer eprice = Integer.getInteger("price");
        return foodoutletBeanLocal.editFoodOutlet(oname,otype,odes, id );
    }
    
    public Boolean deleteFoodOutletById(Long id){
        return foodoutletBeanLocal.deleteFoodOutletById(id);
    }

}