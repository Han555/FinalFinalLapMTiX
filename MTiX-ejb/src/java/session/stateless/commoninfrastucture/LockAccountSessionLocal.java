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
public interface LockAccountSessionLocal {

    boolean checkAccount(String username, String password);

    boolean checkLockExistence(String username);

    String RetrieveLockCount(String username);

    void updateLockCount(String username, String lockCount);

    int sendMail(String to, String from, String message, String subject, String smtpServ);

    void insertLockEntry(String username);

    void unlockAccount(String username);

    
}
