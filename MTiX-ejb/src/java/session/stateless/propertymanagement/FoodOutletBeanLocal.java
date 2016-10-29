/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.FoodOutletEntity;
import entity.ManpowerEntity;
import entity.PropertyEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface FoodOutletBeanLocal {

    public List<FoodOutletEntity> getFoodOutletInProperty(Long propertyId);

    public FoodOutletEntity getFoodOutletById(Long id);

    public PropertyEntity getPropertyById(Long id);

    public FoodOutletEntity addFoodOutlet(String name, String type, String description, Long propertyId);

    public boolean editFoodOutlet(String name, String type, String description, Long outletId);

    public Boolean deleteFoodOutletById(Long outletId);

    public List<FoodOutletEntity> getAllFoodOutlet();

}
