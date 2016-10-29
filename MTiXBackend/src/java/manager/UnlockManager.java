/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import session.stateless.commoninfrastucture.UnlockAccountSessionLocal;

/**
 *
 * @author Student-ID
 */
public class UnlockManager {
    
    private UnlockAccountSessionLocal unlock;
    
    public UnlockManager(UnlockAccountSessionLocal unlock) {
        this.unlock = unlock;
    }
    
    public void unlock(String username) {
        unlock.unlockAccount(username);
    }
}
