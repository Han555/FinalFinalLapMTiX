/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.List;
import java.util.Vector;
import session.stateless.commoninfrastucture.RegisterSessionLocal;

/**
 *
 * @author Student-ID
 */
public class RegisterManager {
     private RegisterSessionLocal registerSessionLocal;
    
    public RegisterManager(RegisterSessionLocal registerSessionLocal) {
        this.registerSessionLocal = registerSessionLocal;
    }
    
    public void register(String username, String password, String mobileNumber) {
        String salt;
        SecurityManager secure = new SecurityManager();
        byte[] secureSalt = secure.getNextSalt();
        salt = secure.byteArrayToHexString(secureSalt);
        String toBeHashed = salt + password;
        String hashedPassword = secure.doMD5Hashing(toBeHashed);
        
        registerSessionLocal.createUser(username, hashedPassword, mobileNumber, salt);
    }
    
    public boolean checkConflict(String username) {
        return registerSessionLocal.checkUserConflict(username);
    }
    
    public void sendEmail(String to, String from, String message, String subject, String smtpServ) {
        registerSessionLocal.sendMail(to, from, message, subject, smtpServ);
    }
    
    public boolean checkOldPassword(String username, String oldPassword) {
        List<Vector> user = registerSessionLocal.retrieveUser(username);
        
        if(user.isEmpty()) {
            return false;
        }
        SecurityManager secure = new SecurityManager();
        String toBeHashed = (String) (user.get(0).get(2)) + oldPassword;
        String hashedPassword2 = secure.doMD5Hashing(toBeHashed);
        if(hashedPassword2.equals(user.get(0).get(1))) {
            return true;
        } else {
            return false;
        }
    }
    
    public void verify(String username) {
        registerSessionLocal.verifyAccount(username);
    }
    
    public void changePassword(String username, String newPassword) {
        String salt;
        SecurityManager secure = new SecurityManager();
        byte[] secureSalt = secure.getNextSalt();
        salt = secure.byteArrayToHexString(secureSalt);
        String toBeHashed = salt + newPassword;
        String hashedPassword = secure.doMD5Hashing(toBeHashed);
        
        registerSessionLocal.changeSecureFirstPassword(username, hashedPassword, salt);
    }
    
    public void createAdministrator() {
        registerSessionLocal.createAdmin();
    }
    
     public void regisCustomer(String username, String password, String mobileNumber,String first, String last, String day, String month, String year) {
        String salt;
        SecurityManager secure = new SecurityManager();
        byte[] secureSalt = secure.getNextSalt();
        salt = secure.byteArrayToHexString(secureSalt);
        String toBeHashed = salt + password;
        String hashedPassword = secure.doMD5Hashing(toBeHashed);
        String birth = day;
        birth = birth.concat("/"+month);
        birth = birth.concat("/"+year);
        registerSessionLocal.createCustomer(username, hashedPassword, mobileNumber, salt,first,last,birth);
    }
}
