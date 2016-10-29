/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Student-ID
 */
@Local
public interface BulletinSessionLocal {

    void broadcast(String message, String subject);

    ArrayList<ArrayList<String>> retrieveBulletin();

    ArrayList<String> getMessage(String id);

}
