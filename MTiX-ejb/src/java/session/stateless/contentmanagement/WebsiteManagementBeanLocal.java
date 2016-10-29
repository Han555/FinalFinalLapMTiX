/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.contentmanagement;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JingYing
 */
@Local
public interface WebsiteManagementBeanLocal {

    public List<ArrayList> getWebpageList();

    public ArrayList getEventWebpageInfo(String id);

    public List<ArrayList> getEventSessionInfo(String id);

    public List<ArrayList> getEventPromotionInfo(String id);

    public List<ArrayList> getCreditCardEvents();

    public List<ArrayList> getVolumeDiscountEvents();

    public List<ArrayList> getWebpageListByType(String type);
    
}
