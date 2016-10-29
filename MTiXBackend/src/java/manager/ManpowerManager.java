/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import session.stateless.propertymanagement.ManpowerBeanLocal;
import entity.ManpowerEntity;
import java.util.List;

/**
 *
 * @author hyc528
 */
public class ManpowerManager {
    
private final ManpowerBeanLocal manpowerBeanLocal;

    public ManpowerManager(ManpowerBeanLocal manpowerManagementBeanLocal) {
        this.manpowerBeanLocal = manpowerManagementBeanLocal;
    }

    public List<ManpowerEntity> getAllManpower() {
        return manpowerBeanLocal.getAllManpower();
    }

    public ManpowerEntity createNewManpower(String mrole, Integer mintnumber, Boolean standard, Long propertyId) {
        //Integer eprice = Integer.getInteger("price");
        return manpowerBeanLocal.addManpower(mrole, mintnumber, standard, propertyId);
    }
    
    public boolean editManpower(Long id, String mrole, Integer number) {
        //Integer eprice = Integer.getInteger("price");
        return manpowerBeanLocal.editManpower(id, mrole,number);
    }

    public ManpowerEntity mSetNoSPrice(Long eId, String price) {
        System.out.println("=======BeforeManager" + price);
        Integer eprice = Integer.parseInt(price);
        System.out.println("=======Manager" + price);
        return manpowerBeanLocal.mSetNoSPrice(eId, eprice);
    }

    public List<ManpowerEntity> getAllNonStandardEquipments() {
        return manpowerBeanLocal.getAllNonStandardManpowers();
    }
    
    public Boolean deleteManpowerById(Long id){
        return manpowerBeanLocal.deleteManpowerById(id);
    }
    
    public List<ManpowerEntity> getNonSManpowerInProperty(Long propertyId){
        return manpowerBeanLocal.getNonSManpowerInProperty(propertyId);
    }

}
