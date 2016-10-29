/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.ManpowerEntity;
import entity.PropertyEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hyc528
 */
@Local
public interface ManpowerBeanLocal {

    public ManpowerEntity getManpowerById(Long manpowerId);

    public PropertyEntity getPropertyById(Long id);

    public Boolean deleteManpowerById(Long manpowerId);

    public List<ManpowerEntity> getAllManpower();

    public List<ManpowerEntity> getAllNonStandardManpowers();

    public List<ManpowerEntity> getManpowerInProperty(Long propertyId);

    public List<ManpowerEntity> getNonSManpowerInProperty(Long propertyId);

    public ManpowerEntity addManpower(String role, Integer number, Boolean standard, Long propertyId);

    public Boolean editManpower(Long manpowerId, String role, Integer number);

    public ManpowerEntity mSetNoSPrice(Long mid, Integer price);

}
