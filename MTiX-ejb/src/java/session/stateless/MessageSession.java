/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.MessageEntity;
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
public class MessageSession implements MessageSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;

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
            Authenticator auth = new SMTPAuthenticator();
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

    @Override
    public boolean checkAccount(String username) {
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + username + "'");

        if (q.getResultList().isEmpty()) {
            return false;
        }

        return true;

    }

    @Override
    public void createMessage(String to, String from, String message, String subject) {
        /*MessageEntity m = new MessageEntity();
         m.createMessage(to, from, message, subject);
         entityManager.persist(m);*/

        MessageEntity m = new MessageEntity();
        m.createMessage(to, from, message, subject);
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + to + "'");
        UserEntity u = new UserEntity();
        
        for(Object o: q.getResultList()) {
            u = (UserEntity) o;
            u.getMessages().add(m);
            entityManager.merge(u);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> retrieveInbox(String username) {
        Query q = entityManager.createQuery("SELECT i FROM MessageEntity i WHERE i.receiver = " + "'" + username + "'");
        ArrayList<ArrayList<String>> inbox = new ArrayList();

        if (q.getResultList().isEmpty()) {
            return inbox;
        } else {
            for (Object o : q.getResultList()) {
                MessageEntity m = (MessageEntity) o;

                ArrayList<String> message = new ArrayList();
                message.add(Long.toString(m.getId()));
                message.add(m.getSender());
                message.add(m.getSubject());
                message.add(m.getStatus());
                inbox.add(message);
            }
            return inbox;
        }

    }

    @Override
    public ArrayList<String> retrieveMessage(String id) {
        ArrayList<String> message = new ArrayList();
        Query q = entityManager.createQuery("SELECT m FROM MessageEntity m WHERE m.id = " + id);

        Query q2 = entityManager.createQuery("UPDATE MessageEntity m SET m.status = " + "'read'" + " WHERE m.id = " + id);
        q2.executeUpdate();

        for (Object o : q.getResultList()) {
            MessageEntity m = (MessageEntity) o;
            message.add(m.getSender());
            message.add(m.getSubject());
            message.add(m.getContent());
            message.add(Long.toString(m.getId()));
        }

        return message;
    }

}
