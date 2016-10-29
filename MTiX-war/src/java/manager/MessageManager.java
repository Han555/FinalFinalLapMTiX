/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import javax.persistence.Query;
import session.stateless.MessageSessionLocal;



/**
 *
 * @author Student-ID
 */
public class MessageManager {
    private MessageSessionLocal messageSessionLocal;
    
    public MessageManager(MessageSessionLocal messageSessionLocal) {
        this.messageSessionLocal = messageSessionLocal;
    }
    
    public boolean checkUser(String username) {
        return messageSessionLocal.checkAccount(username);
    }
    
    public boolean sendMessage(String from, String to, String subject, String content) {
        if(this.checkUser(to)) {
            messageSessionLocal.createMessage(to, from, content, subject);
            messageSessionLocal.sendMail(to, "is3102mtix@gmail.com", from +" sent you a message on MTiX with regards to " + subject, "MTiX Messaging", "smtp.gmail.com");
            
            return true;
        } else {
            return false;
        }
    }
    
    public ArrayList<ArrayList<String>> getInbox(String username) {
        ArrayList<ArrayList<String>> inbox = messageSessionLocal.retrieveInbox(username);
        ArrayList<ArrayList<String>> arrangedInbox = new ArrayList();
        int size = inbox.size();
        size--;
        
        for(int i=size; i>=0; i--) {
            arrangedInbox.add(inbox.get(i));
        }
        
        return arrangedInbox;
    }
    
    public ArrayList<String> getMessage(String id) {
        return messageSessionLocal.retrieveMessage(id);
    }
    
    public ArrayList<ArrayList<String>> inboxPage(ArrayList<ArrayList<String>> inbox, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> inboxPage = new ArrayList();
        int size = inbox.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            ArrayList<String> page = new ArrayList();
            page.add(inbox.get(i).get(0));
            page.add(inbox.get(i).get(1));
            page.add(inbox.get(i).get(2));
            page.add(inbox.get(i).get(3));
            inboxPage.add(page);
        }
        
       
        return inboxPage;
    }
}
