/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JingYing
 */
@Local
public interface GetAllProductDetailsLocal {

    public List<ArrayList> getAllSessions();

    public List<ArrayList> getSalesDetails();
    
}
