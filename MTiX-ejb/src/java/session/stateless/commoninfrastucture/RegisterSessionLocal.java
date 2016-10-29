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
public interface RegisterSessionLocal {

    

    boolean checkUserConflict(String username);

    int sendMail(String to, String from, String message, String subject, String smtpServ);

    List<Vector> retrieveUser(String username);

    void verifyAccount(String username);

    void changeFirstPassword(String username, String newPassword);

    void createUser(String username, String password, String mobileNumber, String salt);

    void changeSecureFirstPassword(String username, String password, String salt);

    void createAdmin();

    void adminCreateUser(String username, String password, String mobileNumber, String salt, String role);

    public void createCustomer(String username, String password, String mobileNumber, String salt, String first, String last, String birth);

  
    
}
