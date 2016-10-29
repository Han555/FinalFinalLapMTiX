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
public interface MessageSessionLocal {

    int sendMail(String to, String from, String message, String subject, String smtpServ);

    boolean checkAccount(String username);

    void createMessage(String to, String from, String message, String subject);

    ArrayList<ArrayList<String>> retrieveInbox(String username);

    ArrayList<String> retrieveMessage(String id);
    
}
