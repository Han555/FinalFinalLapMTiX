/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.persistence.Query;
import session.stateless.commoninfrastucture.LoginSessionLocal;

/**
 *
 * @author Student-ID
 */
public class LoginManager {
    private LoginSessionLocal loginSessionLocal;
    
    public LoginManager(LoginSessionLocal loginSessionLocal) {
        this.loginSessionLocal = loginSessionLocal;
    }
    
    public boolean identify(String username, String password) {
        String salt =""; 
        String hashedPassword = "";
        String hashedPassword2 = "";

        if (!(loginSessionLocal.checkUser(username))) {
            return false;
        } else {
            List<Vector> u = loginSessionLocal.retrieveSecureUser(username);
            

                salt = (String)u.get(0).get(0);
                hashedPassword = (String) u.get(0).get(1);
            
            SecurityManager secure = new SecurityManager();
            
            String toBeHashed = salt + password;
            hashedPassword2 = secure.doMD5Hashing(toBeHashed);
            return hashedPassword.equals(hashedPassword2);
        }
    }
    
    public boolean checkUser(String username){
        return loginSessionLocal.checkUser(username);
    }
    
    public boolean checkVerification(String username) {
        return loginSessionLocal.checkFirstLogin(username);
    }
    
    public String getRoles(String username) {
        return loginSessionLocal.retrieveRole(username);
    }
}
