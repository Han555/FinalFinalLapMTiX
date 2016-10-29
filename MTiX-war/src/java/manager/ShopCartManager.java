/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import entity.ShopCartRecordEntity;
import java.util.Collection;
import session.stateless.ticketing.ShoppingCartSessionLocal;

/**
 *
 * @author catherinexiong
 */
public class ShopCartManager {
    private ShoppingCartSessionLocal scsl;
    
    public ShopCartManager(ShoppingCartSessionLocal scsl) {
        this.scsl= scsl;
    }
    public Collection<ShopCartRecordEntity> getShopCartRecordByUsername(String username){
        return scsl.getShopCartRecordsByUsername(username);
    }
}
