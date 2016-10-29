/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.BulletinEntity;
import entity.UserEntity;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Student-ID
 */
@Stateless
public class BulletinSession implements BulletinSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void broadcast(String message, String subject) {
        /*BulletinEntity b = new BulletinEntity();
         b.createBulletin(message, subject);
         entityManager.persist(b);*/

        BulletinEntity b = new BulletinEntity();
        b.createBulletin(message, subject);
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u");
        
        for(Object o: q.getResultList()) {
            UserEntity u = new UserEntity();
            u = (UserEntity) o;
            u.getBulletins().add(b);
            entityManager.merge(u);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> retrieveBulletin() {
        Query q = entityManager.createQuery("SELECT b FROM BulletinEntity b");
        ArrayList<ArrayList<String>> board = new ArrayList();

        for (Object o : q.getResultList()) {
            BulletinEntity b = (BulletinEntity) o;

            ArrayList<String> message = new ArrayList();
            message.add(Long.toString(b.getId()));
            message.add(b.getSubject());
            board.add(message);
        }
        return board;
    }

    @Override
    public ArrayList<String> getMessage(String id) {
        Query q = entityManager.createQuery("SELECT b FROM BulletinEntity b WHERE b.id =" + id);
        ArrayList<String> message = new ArrayList();

        for (Object o : q.getResultList()) {
            BulletinEntity b = (BulletinEntity) o;

            message.add(b.getSubject());
            message.add(b.getMessage());
        }

        return message;
    }

}
