/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.ShopCartRecordEntity;
import entity.TicketSales;
import entity.TicketTakingsEntity;
import entity.UserEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class PaymentSession implements PaymentSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createPayment(String payer, String receiver, String eventName, String ticketQuantity, String amount, String promotion) {
        ShopCartRecordEntity paymentRecord = new ShopCartRecordEntity();
        paymentRecord.createRecord(payer, receiver, eventName, ticketQuantity, amount, promotion);

        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + payer + "'");
        for (Object o : q.getResultList()) {
            UserEntity u = new UserEntity();
            u = (UserEntity) o;
            u.getPayments().add(paymentRecord);
            entityManager.merge(u);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> retrievePayments(String username) {
        System.out.println("retrievepayments username : " + username);
        Query q = entityManager.createQuery("SELECT i FROM ShopCartRecordEntity i WHERE i.payer = " + "'" + username + "'");
        ArrayList<ArrayList<String>> records = new ArrayList();

        if (q.getResultList().isEmpty()) {
            return records;
        } else {
            for (Object o : q.getResultList()) {
                ShopCartRecordEntity p = (ShopCartRecordEntity) o;
                System.out.println("Entered retrieve payments.");
                ArrayList<String> record = new ArrayList();
                record.add(Long.toString(p.getId()));
                System.out.println("Id: " + Long.toString(p.getId()));
                record.add(p.getEventName());
                System.out.println("Id: " + p.getEventName());
                record.add(p.getTicketQuantity());
                System.out.println("Id: " + p.getTicketQuantity());
                record.add(p.getAmount());
                System.out.println("Id: " + p.getAmount());
                record.add(p.getPaymentStatus());
                System.out.println("Id: " + p.getPaymentStatus());
                records.add(record);
            }
            return records;
        }

    }

    @Override
    public String retrieveEventName(String paymentId) {
        Query q = entityManager.createQuery("SELECT i FROM ShopCartRecordEntity i WHERE i.id = " + paymentId);
        String name = "";

        for (Object o : q.getResultList()) {
            ShopCartRecordEntity record = new ShopCartRecordEntity();
            record = (ShopCartRecordEntity) o;
            name = record.getEventName();
        }
        return name;
    }

    @Override
    public void updateRecord(String paymentId, String address, String country, String city, String zip) {
        Query query = entityManager.createQuery("UPDATE ShopCartRecordEntity u SET u.address = " + "'" + address + "'" + " WHERE u.id = " + paymentId);
        query.executeUpdate();
        Query query2 = entityManager.createQuery("UPDATE ShopCartRecordEntity u SET u.country = " + "'" + country + "'" + " WHERE u.id = " + paymentId);
        query2.executeUpdate();
        Query query3 = entityManager.createQuery("UPDATE ShopCartRecordEntity u SET u.city = " + "'" + city + "'" + " WHERE u.id = " + paymentId);
        query3.executeUpdate();
        Query query4 = entityManager.createQuery("UPDATE ShopCartRecordEntity u SET u.zip = " + "'" + zip + "'" + " WHERE u.id = " + paymentId);
        query4.executeUpdate();
    }

    @Override
    public void updateStatus(String paymentId) {
        Query query = entityManager.createQuery("UPDATE ShopCartRecordEntity u SET u.paymentStatus = 'paid'" + " WHERE u.id = " + paymentId);
        query.executeUpdate();
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
            Authenticator auth = new PaymentSession.SMTPAuthenticator();
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
    public ArrayList<String> retrieveAddress(String paymentId) {
        ArrayList<String> address = new ArrayList();
        Query q = entityManager.createQuery("SELECT i FROM ShopCartRecordEntity i WHERE i.id = " + paymentId);

        for (Object o : q.getResultList()) {
            ShopCartRecordEntity p = new ShopCartRecordEntity();
            p = (ShopCartRecordEntity) o;
            address.add(p.getAddress());
            address.add(p.getCountry());
            address.add(p.getCity());
            address.add(p.getZip());
            address.add(p.getEventName());
            address.add(p.getTicketQuantity());
            address.add(p.getAmount());
        }
        return address;
    }

    @Override
    public ArrayList<String> retrieveEvents(String receiver) {
        Query q = entityManager.createQuery("SELECT i FROM ShopCartRecordEntity i WHERE i.receiver = " + "'" + receiver + "'");
        ArrayList<String> events = new ArrayList();
        System.out.println("receiver: " + receiver);
        for (Object o : q.getResultList()) {
            ShopCartRecordEntity p = new ShopCartRecordEntity();
            p = (ShopCartRecordEntity) o;
            System.out.println("event name: " + p.getEventName());
            events.add(p.getEventName());
        }

        int limit = events.size() - 1;

        for (int i = 0; i < limit; i++) {
            String current = events.get(i);

            for (int j = i + 1; j < events.size(); j++) {
                String next = events.get(j);

                if (current.equals(next)) {
                    events.remove(j);

                    i--;
                    j--;
                }
            }

            if (i < 0) {
                break;
            }
        }

//        for (int i = 0; i < events.size(); i++) {
//            int size1 = events.size();
//            size1 -= 1;
//            System.out.println("size: " + events.size());
//            System.out.println("size1: " + size1);
//            for (int u = i + 1; u<events.size(); u++) {
//                System.out.println("i: " + i);
//                System.out.println("u: " + u);
//                if (events.get(i).equals(events.get(u))) {
//                    System.out.println("Removing replication event");
//                    events.remove(u);
//                }
//            }
//        }
        return events;
    }

    @Override
    public ArrayList<ArrayList<String>> retrieveRecords(String event, String receiver) {
        Query q = entityManager.createQuery("SELECT i FROM ShopCartRecordEntity i WHERE i.receiver = " + "'" + receiver + "'" + " AND i.eventName = " + "'" + event + "'");
        ArrayList<ArrayList<String>> records = new ArrayList();

        for (Object o : q.getResultList()) {
            ShopCartRecordEntity p = new ShopCartRecordEntity();
            p = (ShopCartRecordEntity) o;
            ArrayList<String> record = new ArrayList();
            record.add(p.getPayer());
            record.add(p.getTicketQuantity());
            record.add(p.getAmount());
            record.add(p.getPaymentStatus());
            records.add(record);
        }

        return records;
    }

    @Override
    public void requestRefund(String paymentId) {
        Query query = entityManager.createQuery("UPDATE ShopCartRecordEntity u SET u.paymentStatus = 'refunding'" + " WHERE u.id = " + paymentId);
        query.executeUpdate();
    }

    @Override
    public ArrayList<ArrayList<String>> retrieveRefundRecords() {
        Query q = entityManager.createQuery("SELECT i FROM ShopCartRecordEntity i WHERE i.paymentStatus = 'refunding' OR i.paymentStatus = 'refunded'");
        ArrayList<ArrayList<String>> refundRecords = new ArrayList();

        for (Object o : q.getResultList()) {
            ShopCartRecordEntity p = new ShopCartRecordEntity();
            p = (ShopCartRecordEntity) o;
            ArrayList<String> record = new ArrayList();

            record.add(Long.toString(p.getId()));
            record.add(p.getEventName());
            record.add(p.getReceiver());
            record.add(p.getPayer());
            record.add(p.getTicketQuantity());
            record.add(p.getAmount());
            record.add(p.getPaymentStatus());
            refundRecords.add(record);
        }

        return refundRecords;
    }

    @Override
    public void updateRefundStatus(String paymentId) {
        Query query = entityManager.createQuery("UPDATE ShopCartRecordEntity u SET u.paymentStatus = 'refunded'" + " WHERE u.id = " + paymentId);
        query.executeUpdate();
        
        Query q = entityManager.createQuery("SELECT i FROM ShopCartRecordEntity i WHERE i.id = " + paymentId);
        ShopCartRecordEntity s = new ShopCartRecordEntity();
        
        for(Object o: q.getResultList()) {
            s = (ShopCartRecordEntity) o;
            
        }
        String eventName = s.getEventName();
        String price = s.getAmount();
        double priceFinal = Double.parseDouble(price);
        
        Query q2 = entityManager.createQuery("SELECT i FROM TicketTakingsEntity i WHERE i.event = " + "'"+eventName+"'");
        TicketTakingsEntity t = new TicketTakingsEntity();
        
        
        for(Object i: q2.getResultList()) {
            t = (TicketTakingsEntity) i;
        }
        String takings = t.getTicketTakings();
        double takingsFinal = Double.parseDouble(takings);
        takingsFinal -= priceFinal;
        
        Query query2 = entityManager.createQuery("UPDATE TicketTakingsEntity u SET u.ticketTakings = "+"'"+String.valueOf(takingsFinal)+"'" + " WHERE u.event = " +"'"+eventName+"'");
        query2.executeUpdate();
    }

    @Override
    public void updateTicketTakings(String paymentId) {
        Query q = entityManager.createQuery("SELECT i FROM ShopCartRecordEntity i WHERE i.id = " + paymentId);
        ShopCartRecordEntity p = new ShopCartRecordEntity();
        String amount = "";
        String ticketQuantity = "";
        String organizer = "";
        String event = "";
        double amount1, ticketQuantity1;

        for (Object o : q.getResultList()) {
            p = (ShopCartRecordEntity) o;
            amount = p.getAmount();
            ticketQuantity = p.getTicketQuantity();
            organizer = p.getReceiver();
            event = p.getEventName();
            System.out.println("Amount: " + amount);
            System.out.println("organizer: " + organizer);
            System.out.println("Event: " + event);
        }

        amount1 = Double.parseDouble(amount);
        //amount1 -= 3;
        ticketQuantity1 = Integer.parseInt(ticketQuantity);
        double ticketTakings;
        ticketTakings = amount1;

        Query q2 = entityManager.createQuery("SELECT t FROM TicketTakingsEntity t WHERE t.organizer = " + "'" + organizer + "'" + " AND t.event = " + "'" + event + "'");
        TicketTakingsEntity t = new TicketTakingsEntity();
        String currentTicketTakings = "";
        double currentTicketTakings1;

        for (Object o : q2.getResultList()) {
            t = (TicketTakingsEntity) o;
            currentTicketTakings = t.getTicketTakings();
        }

        currentTicketTakings1 = Double.parseDouble(currentTicketTakings);
        currentTicketTakings1 += ticketTakings;
        System.out.println("Current ticket takings: " + currentTicketTakings);
        Query query = entityManager.createQuery("UPDATE TicketTakingsEntity t SET t.ticketTakings = " + "'" + currentTicketTakings1 + "'" + " WHERE t.organizer = " + "'" + organizer + "'" + " AND t.event = " + "'" + event + "'");
        query.executeUpdate();

        //inserting ticket sales
        TicketSales ticketSale = new TicketSales();
        ticketSale.setPurchaseDate(Calendar.getInstance().getTime());
        ticketSale.setPromotion(p.getPromotion());
        ticketSale.setTicketQuantity(Integer.parseInt(p.getTicketQuantity()));
        Query query2 = entityManager.createQuery("SELECT t FROM UserEntity t WHERE t.username = " +"'"+ p.getPayer()+"'");
        UserEntity u = new UserEntity();
        for(Object i : query2.getResultList()) {
            u = (UserEntity) i;
        }
        ticketSale.setUser(u);
        ticketSale.setSession(p.getSession());
        ticketSale.setSectionEntity(p.getSection());
        int finalQuantity = Integer.parseInt(p.getTicketQuantity());
        double bookingServiceFee = finalQuantity * 3.0;
        double finalPrice = Double.parseDouble(p.getAmount());
        finalPrice += bookingServiceFee;
        ticketSale.setFinalPrice(finalPrice);
        entityManager.merge(ticketSale);
        entityManager.flush();
        p.getSession().getTicketSales().add(ticketSale);
        entityManager.merge(p.getSession());
        u.getTicketSales().add(ticketSale);
        entityManager.merge(u);
    }

    @Override
    public String retrieveEventDate(String id) {
        Query q = entityManager.createQuery("SELECT i FROM ShopCartRecordEntity i WHERE i.id = " + id);
        ShopCartRecordEntity s = new ShopCartRecordEntity();

        for (Object o : q.getResultList()) {
            s = (ShopCartRecordEntity) o;
        }

        String eventDate = new SimpleDateFormat("yyyy/MM/dd").format(s.getSession().getTimeStart());

        return eventDate;
    }

}
