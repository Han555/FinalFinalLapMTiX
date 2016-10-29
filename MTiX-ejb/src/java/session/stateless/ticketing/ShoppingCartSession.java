/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.ticketing;

import entity.ShopCartRecordEntity;
import entity.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class ShoppingCartSession implements ShoppingCartSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method
    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<ShopCartRecordEntity> getShopCartRecordsByUsername(String username) {
        Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.username=:username");
        q.setParameter("username", username);
        UserEntity user = (UserEntity) q.getSingleResult();
        Collection <ShopCartRecordEntity> records = new ArrayList();
        for(ShopCartRecordEntity sc: user.getPayments()){
            System.out.println(sc.getEventName());
            if(!(sc.getPaymentStatus().equals("paid"))){
                records.add(sc);
            }
        }
        
        return records;
    }
}
