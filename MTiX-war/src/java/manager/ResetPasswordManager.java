/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import session.stateless.commoninfrastucture.ResetPasswordSessionLocal;

/**
 *
 * @author Student-ID
 */
public class ResetPasswordManager {
    private ResetPasswordSessionLocal resetPasswordLocal;
    
    public ResetPasswordManager(ResetPasswordSessionLocal resetPasswordLocal) {
        this.resetPasswordLocal = resetPasswordLocal;
    }
    
    public void sendEmail(String to, String from, String message, String subject, String smtpServ) {
        resetPasswordLocal.sendMail(to, from, message, subject, smtpServ);
    }
    
    public boolean checkUserAccount(String username) {
        return resetPasswordLocal.checkAccount(username);
    }
    
    public boolean checkLockPresence(String username) {
        return resetPasswordLocal.checkLock(username);
    }
    
    public void reset(String username) {
        String newPassword = resetPasswordLocal.resetPassword(username);
        
        String salt;
        SecurityManager secure = new SecurityManager();
        byte[] secureSalt = secure.getNextSalt();
        salt = secure.byteArrayToHexString(secureSalt);
        String toBeHashed = salt + newPassword;
        String hashedPassword = secure.doMD5Hashing(toBeHashed);
        
        resetPasswordLocal.resetSecure(username, hashedPassword, salt);
        
        resetPasswordLocal.sendMail(username, "is3102mtix@gmail.com", "New Password: " +newPassword, "MTiX Account Reset Password", "smtp.gmail.com");
        
    }
    
    public boolean resetted(String username) {
        return resetPasswordLocal.checkReset(username);
    }
    
    public void resetPassword(String username, String password) {
        String salt;
        SecurityManager secure = new SecurityManager();
        byte[] secureSalt = secure.getNextSalt();
        salt = secure.byteArrayToHexString(secureSalt);
        String toBeHashed = salt + password;
        String hashedPassword = secure.doMD5Hashing(toBeHashed);
        
        resetPasswordLocal.resetNewSecurePassword(username, hashedPassword, salt);
    }
}
