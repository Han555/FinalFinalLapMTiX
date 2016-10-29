/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import java.util.Date;
import java.util.List;
import entity.MaintenanceScheduleEntity;
import javax.ejb.Local;


@Local
public interface MaintenanceBeanLocal {
    
    public Boolean addMaintenance(Long propertyId, Date start, Date end);
    public Boolean removeMaintenance(Long id);
    public Boolean updateMaintenance(Long mid, Long propertyId, Date start, Date end);
    public List<MaintenanceScheduleEntity> getMaintenanceInProperty(Long propertyId);
    
    public MaintenanceScheduleEntity getMaintenanceScheduleById(Long id);
    
}
