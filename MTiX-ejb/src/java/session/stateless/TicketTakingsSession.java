/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.TicketTakingsEntity;
import entity.UserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Student-ID
 */
@Stateless
public class TicketTakingsSession implements TicketTakingsSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createTicketTakings(String financeManager, String organizer, String event, String cost) {
        TicketTakingsEntity t = new TicketTakingsEntity();
        t.createTakings(organizer, event, cost);
        
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + financeManager + "'");
        for (Object o : q.getResultList()) {
            UserEntity u = new UserEntity();
            u = (UserEntity) o;
            u.getTicketTakings().add(t);
            entityManager.merge(u);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> retrieveTicketTakings() {
        ArrayList<ArrayList<String>> ticketTakings = new ArrayList();
        Query q = entityManager.createQuery("SELECT t FROM TicketTakingsEntity t ");
        
        for(Object o: q.getResultList()) {
            TicketTakingsEntity t = new TicketTakingsEntity();
            t = (TicketTakingsEntity) o;
            ArrayList<String> ticketTaking = new ArrayList();
            
            ticketTaking.add(Long.toString(t.getId()));
            ticketTaking.add(t.getOrganizer());
            ticketTaking.add(t.getEvent());
            ticketTaking.add(t.getCost());
            ticketTaking.add(t.getTicketTakings());
            ticketTakings.add(ticketTaking);
            
        }
        
        return ticketTakings;
    }

    @Override
    public int sendMail(String to, String from, String message, String subject, String smtpServ) {
        try {
            Properties props = System.getProperties();
            /*
             Properties props = new Properties();
             props.put("mail.transport.protocol", "smtp");
             props.put("mail.smtp.host", "smtp.gmail.com");
             props.put("mail.smtp.port", "587");
             props.put("mail.smtp.auth", "true");
             props.put("mail.smtp.starttls.enable", "true");
             props.put("mail.smtp.debug", "true");           
             javax.mail.Authenticator auth = new SMTPAuthenticator();
             Session session = Session.getInstance(props, auth);
             session.setDebug(true);           
             Message msg = new MimeMessage(session);
             msg.setFrom(InternetAddress.parse("xxx <xxx@gmail.com>", false)[0]);*/
            // -- Attaching to default Session, or we could start a new one --
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpServ);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            //new
            props.put("mail.smtp.debug", "true");
            Authenticator auth = new TicketTakingsSession.SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            //new
            session.setDebug(true);
            // -- Create a new message --
            Message msg = new MimeMessage(session);
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(message);
            // -- Set some other header information --
            msg.setHeader("MyMail", "Mr. XYZ");
            msg.setSentDate(new Date());
            // -- Send the message --
            Transport.send(msg);
            System.out.println("Message sent to" + to + " OK.");
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception " + ex);
            return -1;
        }
    }
    
    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "is3102mtix@gmail.com";           // specify your email id here (sender's email id)
            String password = "integrated555";                                      // specify your password here
            return new PasswordAuthentication(username, password);
        }
    }
    
}
