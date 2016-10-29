/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import session.stateless.TicketTakingsSessionLocal;

/**
 *
 * @author Student-ID
 */
public class TicketTakingsManager {
    private TicketTakingsSessionLocal ticketTakingsSessionLocal;
    
    public TicketTakingsManager(TicketTakingsSessionLocal ticketTakingsSessionLocal) {
        this.ticketTakingsSessionLocal = ticketTakingsSessionLocal;
    }
    
    public void createTakings(String financeManager, String organizer, String event, String cost) {
        ticketTakingsSessionLocal.createTicketTakings(financeManager, organizer, event, cost);
    } 
    
    public ArrayList<ArrayList<String>> getTakings() {
        ArrayList<ArrayList<String>> takings = ticketTakingsSessionLocal.retrieveTicketTakings();
        ArrayList<ArrayList<String>> arrangedTakings = new ArrayList();
        int size = takings.size();
        size--;
        
        for(int i=size; i>=0; i--) {
            arrangedTakings.add(takings.get(i));
        }
        
        return arrangedTakings;
    }
    
     public ArrayList<ArrayList<String>> takingsPage(ArrayList<ArrayList<String>> ticketTakings, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> takingsPage = new ArrayList();
        int size = ticketTakings.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            ArrayList<String> ticketTaking = new ArrayList();
            ticketTaking.add(ticketTakings.get(i).get(0));
            ticketTaking.add(ticketTakings.get(i).get(1));
            ticketTaking.add(ticketTakings.get(i).get(2));
            ticketTaking.add(ticketTakings.get(i).get(3));
            ticketTaking.add(ticketTakings.get(i).get(4));
            takingsPage.add(ticketTaking);
        }
        
       
        return takingsPage;
    }
     
     public int sendMail(String to, String from, String message, String subject, String smtpServ) {
         return ticketTakingsSessionLocal.sendMail(to, from, message, subject, smtpServ);
     }
}
