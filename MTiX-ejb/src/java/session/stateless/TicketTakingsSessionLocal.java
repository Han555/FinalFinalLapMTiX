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
public interface TicketTakingsSessionLocal {

    void createTicketTakings(String financeManager, String organizer, String event, String cost);

    ArrayList<ArrayList<String>> retrieveTicketTakings();

    int sendMail(String to, String from, String message, String subject, String smtpServ);
    
}
