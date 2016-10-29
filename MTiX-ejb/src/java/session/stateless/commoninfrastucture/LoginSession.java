/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Student-ID
 */
@Stateless
public class LoginSession implements LoginSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean identifyUser(String username, String password) {

        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + username + "'" + " AND u.password = " + "'" + password + "'");

        if (q.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean checkFirstLogin(String username) {
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + username + "'");

        for (Object o : q.getResultList()) {
            UserEntity u = (UserEntity) o;

            if (u.isFirstLogin()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean checkUser(String username) {
        Query q1 = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + username + "'");

        if (q1.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Vector> retrieveSecureUser(String username) {
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username=" + "'" + username + "'");
        List<Vector> user = new ArrayList();
        for (Object o : q.getResultList()) {
            UserEntity u = (UserEntity) o;

            Vector im = new Vector();
            im.add(u.getSalt());
            im.add(u.getPassword());
            user.add(im);
        }
        return user;
    }

    @Override
    public String retrieveRole(String username) {
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username=" + "'" + username + "'");
        String role = "";
        for (Object o : q.getResultList()) {
            UserEntity u = (UserEntity) o;

            role = u.getRoles().get(0);
        }
        
        return role;
    }

}
