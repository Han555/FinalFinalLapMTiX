/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.EquipmentEntity;
import entity.PropertyEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface EquipmentBeanLocal {

    public List<EquipmentEntity> getEquipmentInProperty(Long propertyId);

    public List<EquipmentEntity> getNonSEquipmentInProperty(Long propertyId);

    public PropertyEntity getPropertyById(Long id);

    public EquipmentEntity getEquipmentById(Long equipmentId);

   

    public Boolean deleteEquipmentById(Long equipmentId);

    public List<EquipmentEntity> getAllEquipments();

 //   public Boolean addEquipment(String name, String location, Integer price, Boolean standard, Long propertyId);

    public boolean editEquipment(Long equipmentId, String name, String location);

    public EquipmentEntity addEquipment(String name, String location, Boolean standard, Long propertyId);

    public EquipmentEntity setNoSPrice(Long eid, Integer price);

    public List<EquipmentEntity> getAllNonStandardEquipments();

  //  public Equipment editEquipment(Long id, String name, String location);
    
}
