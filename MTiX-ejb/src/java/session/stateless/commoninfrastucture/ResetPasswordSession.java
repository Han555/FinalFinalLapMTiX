/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import entity.AccountLock;
import entity.UserEntity;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;
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
public class ResetPasswordSession implements ResetPasswordSessionLocal {

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
        } else {
            return true;
        }
    }

    @Override
    public String resetPassword(String username) {
        StringBuffer sb = new StringBuffer();
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789/?<>}{.,$#!%&*".toCharArray();
        Random random = new Random();
        for (int i = 1; i <= 8; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        String newPassword = new String(sb);

        return newPassword;
    }
    
    
    
    @Override
    public boolean checkLock(String username) {
        Query q = entityManager.createQuery("SELECT a FROM AccountLock a WHERE a.username = " + "'" + username + "'");
        String lockCount = "";

        if (q.getResultList().isEmpty()) {
            return false;
        }

        for (Object o : q.getResultList()) {
            AccountLock a = (AccountLock) o;

            lockCount = a.getLockNumber();
        }

        int count = Integer.parseInt(lockCount);

        if (count < 5) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean checkReset(String username) {
        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + username + "'");
        
        for (Object o : q.getResultList()) {
            UserEntity u = (UserEntity) o;

            return u.isResetPassword();
        }
        
        return false;
    }

    @Override
    public void resetNewPassword(String username, String password) {
        System.out.println("username: "+username+"   password: "+password);
        Query q = entityManager.createQuery("UPDATE UserEntity u SET u.password = " + "'" + password + "'" + " WHERE u.username = " + "'" + username + "'");
        q.executeUpdate();

        Query q2 = entityManager.createQuery("UPDATE UserEntity u SET u.resetPassword = 0" + " WHERE u.username = " + "'" + username + "'");
        q2.executeUpdate();
    }

    @Override
    public void resetSecure(String username, String password, String salt) {
         Query q = entityManager.createQuery("UPDATE UserEntity u SET u.password = " + "'" + password + "'" + " WHERE u.username = " + "'" + username + "'");
        q.executeUpdate();

        Query q2 = entityManager.createQuery("UPDATE UserEntity u SET u.resetPassword = 1" + " WHERE u.username = " + "'" + username + "'");
        q2.executeUpdate();
        
        Query q3 = entityManager.createQuery("UPDATE UserEntity u SET u.salt = " + "'" + salt + "'" + " WHERE u.username = " + "'" + username + "'");
        q3.executeUpdate();
    }

    @Override
    public void resetNewSecurePassword(String username, String password, String salt) {
        Query q = entityManager.createQuery("UPDATE UserEntity u SET u.password = " + "'" + password + "'" + " WHERE u.username = " + "'" + username + "'");
        q.executeUpdate();

        Query q2 = entityManager.createQuery("UPDATE UserEntity u SET u.resetPassword = 0" + " WHERE u.username = " + "'" + username + "'");
        q2.executeUpdate();
        
        Query q3 = entityManager.createQuery("UPDATE UserEntity u SET u.salt = " + "'" + salt + "'" + " WHERE u.username = " + "'" + username + "'");
        q3.executeUpdate();
    }
    
    
    
}
