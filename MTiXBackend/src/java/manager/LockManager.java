/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import session.stateless.commoninfrastucture.LockAccountSessionLocal;

/**
 *
 * @author Student-ID
 */
public class LockManager {
    
    private LockAccountSessionLocal lock;
    
    
    public LockManager(LockAccountSessionLocal lock) {
        this.lock = lock;
    }
    
    public boolean checkLock(String username, String password) {
        if(lock.checkAccount(username, password)) {
            return true;
        }
        
        return false;
    }
    
    public boolean lockAccount(String username) {
        //lock.insertLockEntry("dummy");
        if(lock.checkLockExistence(username)) {
            return true;
        }
        return false;
    }
    
    public void sendEmail(String to, String from, String message, String subject, String smtpServ) {
        lock.sendMail(to, from, message, subject, smtpServ);
    }
    
    public boolean finalLock(String username) {
        String lockCount = lock.RetrieveLockCount(username);
        
        if(lockCount.equals("Account not locked.")) {
            return false;
        }
        
        String updatedCount = "";
        int count = Integer.parseInt(lockCount);
        count++;
        updatedCount = String.valueOf(count);
        
        if(count==5) {
            lock.updateLockCount(username, updatedCount);
            lock.sendMail(username, "is3102mtix@gmail.com", "http://localhost:8080/MTiXBackend/BackController?" + "action=unlock&" + "name=" + username, "Unlock MTiX Account", "smtp.gmail.com");
            
            return true;
        } else if(count>5) {
            return true;
        } else {
            lock.updateLockCount(username, updatedCount);
            return false;
        }
    }
    
    public void insertLock(String username) {
        lock.insertLockEntry(username);
    }
    
    public boolean passThrough(String username) {
        String lockCount = lock.RetrieveLockCount(username);
        
        if(lockCount.equals("Account not locked.")) {
            return true;
        } else {
            int count = Integer.parseInt(lockCount);
            if(count<5) {
                lock.unlockAccount(username);
                return true;
            }
        }
        
        return false;
    }
}
