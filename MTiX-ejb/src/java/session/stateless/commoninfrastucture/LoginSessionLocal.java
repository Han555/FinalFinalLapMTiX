/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import java.util.List;
import java.util.Vector;
import javax.ejb.Local;

/**
 *
 * @author Student-ID
 */
@Local
public interface LoginSessionLocal {

    boolean identifyUser(String username, String password);

    boolean checkFirstLogin(String username);

    boolean checkUser(String username);

    List<Vector> retrieveSecureUser(String username);

    String retrieveRole(String username);

   
   
    
}
