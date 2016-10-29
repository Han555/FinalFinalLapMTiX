/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.List;
import java.util.Random;
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
    
    public String resetPassword(String username) {
        StringBuffer sb = new StringBuffer();
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789/?<>}{.,$#!%&*".toCharArray();
        Random random = new Random();
        for (int i = 1; i <= 8; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        String newPassword = new String(sb);

        return newPassword;
    }
    
    public void adminCreate(String username, String role, String mobileNumber) {
        String password = resetPassword(username);
        
        registerSessionLocal.sendMail(username, "is3012mtix@gmail.com", "http://localhost:8080/MTiXBackend/BackController?" + "name=" + username+"     username: "+username+"    password: "+password, "MTiX backend account created", "smtp.gmail.com");
        System.out.println("Entered here checking registration 2");
        String salt;
        SecurityManager secure = new SecurityManager();
        byte[] secureSalt = secure.getNextSalt();
        salt = secure.byteArrayToHexString(secureSalt);
        String toBeHashed = salt + password;
        String hashedPassword = secure.doMD5Hashing(toBeHashed);
        registerSessionLocal.adminCreateUser(username, hashedPassword, mobileNumber, salt, role);
    }
    
   
    
    
    
}
