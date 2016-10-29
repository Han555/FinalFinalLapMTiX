/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import javax.ejb.Local;

/**
 *
 * @author Student-ID
 */
@Local
public interface ResetPasswordSessionLocal {

    int sendMail(String to, String from, String message, String subject, String smtpServ);

    boolean checkAccount(String username);

    String resetPassword(String username);

    boolean checkLock(String username);

    boolean checkReset(String username);

    void resetNewPassword(String username, String password);

    void resetSecure(String username, String password, String salt);

    void resetNewSecurePassword(String username, String password, String salt);
    
}
