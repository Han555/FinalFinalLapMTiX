/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import entity.AccountLock;
import entity.UserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Student-ID
 */
@Stateless
public class LockAccountSession implements LockAccountSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean checkAccount(String username, String password) {
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + username + "'");

        if (q.getResultList().isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean checkLockExistence(String username) {
        Query q = entityManager.createQuery("SELECT u FROM AccountLock u WHERE u.username = " + "'" + username + "'");

        if (q.getResultList().isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public String RetrieveLockCount(String username) {
        Query q = entityManager.createQuery("SELECT u FROM AccountLock u WHERE u.username = " + "'" + username + "'");
        String lockNumber = new String();

        List<Vector> users = new ArrayList();
        for (Object o : q.getResultList()) {
            AccountLock u = (AccountLock) o;

            lockNumber = u.getLockNumber();
        }

        if (q.getResultList().isEmpty()) {
            return "Account not locked.";
        }

        return lockNumber;
    }

    @Override
    public void updateLockCount(String username, String lockCount) {
        Query query = entityManager.createQuery("UPDATE AccountLock u SET u.lockNumber = " + "'" + lockCount + "'" + " WHERE u.username = " + "'" + username + "'");
        query.executeUpdate();
    }

    @Override
    public int sendMail(String to, String from, String message, String subject, String smtpServ) {
        try {
            Properties props = System.getProperties();
            // -- Attaching to default Session, or we could start a new one --
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpServ);
            props.put("mail.smtp.auth", "true");
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
    public void insertLockEntry(String username) {
        AccountLock a = new AccountLock();
        a.create(username, "1");
        entityManager.persist(a);
    }

    @Override
    public void unlockAccount(String username) {
        entityManager.createQuery("DELETE FROM AccountLock a WHERE a.username = " + "'" + username + "'").executeUpdate();
    }

}
