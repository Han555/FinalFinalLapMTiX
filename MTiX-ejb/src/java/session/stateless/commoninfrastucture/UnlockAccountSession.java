/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Student-ID
 */
@Stateless
public class UnlockAccountSession implements UnlockAccountSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void unlockAccount(String username) {
        entityManager.createQuery("DELETE FROM AccountLock a WHERE a.username = " +"'"+ username +"'").executeUpdate();
    }
    
    
}
