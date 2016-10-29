/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import session.stateless.propertymanagement.MaintenanceBeanLocal;
import java.util.List;
import entity.MaintenanceScheduleEntity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author catherinexiong
 */
public class MaintenanceScheduleManager {
    
    private final MaintenanceBeanLocal msb;
    
    public MaintenanceScheduleManager (MaintenanceBeanLocal msb) {
        this.msb = msb;
    }
    
    public Boolean addMaintenance(String propertyId, String daterange) throws ParseException{
        String[] parts = daterange.split(" - ");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = df.parse(parts[0].trim());
        Date endDate = df.parse(parts[1].trim());
        System.out.println(parts[0]+parts[1]);
        return msb.addMaintenance(Long.valueOf(propertyId), startDate, endDate);
    }
    
    public Boolean removeMaintenance(String idStr) {
        Long id = Long.valueOf(idStr);
        return msb.removeMaintenance(id);
    }
    
    public Boolean updateMaintenance(String midStr, String propertyIdStr, String daterange) throws ParseException{
        String[] parts = daterange.split(" - ");
        Long mid = Long.valueOf(midStr);
        Long propertyId = Long.valueOf(propertyIdStr);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = df.parse(parts[0].trim());
        Date endDate = df.parse(parts[1].trim());
        System.out.println(parts[0]+parts[1]);
        return msb.updateMaintenance(mid, propertyId, startDate, endDate);
    }
    
    public List<MaintenanceScheduleEntity> getAllScheduleInProperty(long id) {
        return null;
    }
    
}
