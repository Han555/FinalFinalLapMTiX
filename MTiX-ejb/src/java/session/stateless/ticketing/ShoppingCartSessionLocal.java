/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.ticketing;

import entity.ShopCartRecordEntity;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface ShoppingCartSessionLocal {

    public Collection<ShopCartRecordEntity> getShopCartRecordsByUsername(String username);
    
}
