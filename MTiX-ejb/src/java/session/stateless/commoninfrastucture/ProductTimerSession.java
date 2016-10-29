/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import entity.SectionEntity;
import entity.SessionEntity;
import entity.SessionSeatsInventory;
import entity.SubEvent;
import entity.TicketSales;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JingYing
 */
@Stateless
public class ProductTimerSession implements ProductTimerSessionLocal {

    @PersistenceContext
    EntityManager em;

    //@Schedule(dayOfWeek = "Mon, Thu") ----------------------------- The Real Case 
    @Schedule(hour = "*", minute = "*/2")
    public void automaticInformativeTimer() {
        System.out.println("Timer(): Auto");
        int categoryNo;
        String eventType;
        double[] totalSales; //in the case where there are single digit, thats why double. Then When divide, do not have 0.
        double[] actualSales;
        boolean sendEmail = false;
        String emailCategory = "";
        String emailCurrentSales = "";
        String emailRequirement = "";

        Query q = em.createQuery("SELECT a FROM SessionEntity a");
        for (Object object : q.getResultList()) {
            SessionEntity session = (SessionEntity) object;

            em.refresh(session);

            Date today = Calendar.getInstance().getTime();

            if (session.getAlert() != null) {
                if (session.getAlert().getAlertType().equals("Informative Alert") && today.after(session.getAlert().getAlertStartDate())
                        && today.before(session.getAlert().getAlertEndDate())) {
                    if (session.getEvent() != null) {
                        categoryNo = session.getEvent().getProperty().getCategory().size();
                        totalSales = new double[categoryNo];
                        actualSales = new double[categoryNo];
                        for (int i = 0; i < categoryNo; i++) {
                            totalSales[i] = 0;
                            actualSales[i] = 0;
                        }

                        for (Object obj : session.getEvent().getProperty().getSections()) {
                            SectionEntity sectionEntity = (SectionEntity) obj;

                            boolean isClosed = false;

                            for (Object checking : session.getSeatsInventory()) {
                                SessionSeatsInventory sessionSeatsInventory = (SessionSeatsInventory) checking;
                                if (sessionSeatsInventory.getSectionEntity().getId() == sectionEntity.getId()
                                        && sessionSeatsInventory.getStopTicketsSales()) {
                                    System.out.println("This section is closed. Hence it will not be counted" + sessionSeatsInventory.getId());
                                    isClosed = true;
                                    break;
                                }
                            }
                            if (isClosed) {

                            } else {
                                totalSales[sectionEntity.getCategory().getCategoryNum() - 1] += sectionEntity.getCapacity();
                            }
                        }
                    } else {
                        categoryNo = session.getSubEvent().getProperty().getCategory().size();
                        totalSales = new double[categoryNo];
                        actualSales = new double[categoryNo];
                        for (int i = 0; i < categoryNo; i++) {
                            totalSales[i] = 0;
                            actualSales[i] = 0;
                        }

                        for (Object obj : session.getSubEvent().getProperty().getSections()) {
                            SectionEntity sectionEntity = (SectionEntity) obj;

                            boolean isClosed = false;

                            for (Object checking : session.getSeatsInventory()) {
                                SessionSeatsInventory sessionSeatsInventory = (SessionSeatsInventory) checking;
                                if (sessionSeatsInventory.getSectionEntity().getId() == sectionEntity.getId()
                                        && sessionSeatsInventory.getStopTicketsSales()) {
                                    System.out.println("This section is closed. Hence it will not be counted" + sessionSeatsInventory.getId());
                                    isClosed = true;
                                    break;
                                }
                            }
                            if (isClosed) {

                            } else {
                                totalSales[sectionEntity.getCategory().getCategoryNum() - 1] += sectionEntity.getCapacity();
                            }
                        }
                    }

                    boolean isFreeSeating = false;
                    double actualSalesForFreeSeating = 0;

                    for (Object o : session.getTicketSales()) {
                        TicketSales ticketSales = (TicketSales) o;
                        if (session.getSeatOption().equals("Free Seating")) {
                            isFreeSeating = true;
                            actualSalesForFreeSeating += ticketSales.getTicketQuantity();
                        } else {
                            actualSales[ticketSales.getSectionEntity().getCategory().getCategoryNum() - 1] += ticketSales.getTicketQuantity();
                        }
                    }

                    //Comparing the sales percentage
                    for (int i = 0; i < actualSales.length; i++) {
                        System.out.println("InformativeTimer");
                        if (isFreeSeating) {
                            double totalSalesNumber = 0;
                            for (int j = 0; j < totalSales.length; j++) {
                                totalSalesNumber += totalSales[j];
                            }
                             System.out.println("ALERT : isFreeSeating =" + isFreeSeating + " Sales :" + totalSalesNumber + "vs" + actualSalesForFreeSeating);
                               
                            if (totalSalesNumber * ((double) session.getAlert().getSales() / 100) > actualSalesForFreeSeating) {
                                emailCategory = String.valueOf(i + 1);
                                emailCurrentSales = String.valueOf((int) actualSalesForFreeSeating);
                                emailRequirement = String.valueOf(session.getAlert().getSales());
                                sendEmail = true;
                                break;
                            } else {

                                break;
                            }

                        } else {

                            System.out.println("ALERT : isFreeSeating =" + isFreeSeating + " Sales :" + totalSales[i] + "vs" + actualSales[i]);
                            if (totalSales[i] * ((double) session.getAlert().getSales() / 100) > actualSales[i]) {
                                emailCategory = String.valueOf(i + 1);
                                emailCurrentSales = String.valueOf((int) actualSales[i]);
                                emailRequirement = String.valueOf(session.getAlert().getSales());
                                sendEmail = true;
                                break;
                            }
                        }
                    }

                    if (sendEmail) {
                        this.sendMail(session.getAlert().getInChargePersonEmail(), "is3012mtix@gmail.com", "Dear Sir/Mdm\n\n"
                                + "Ticket sales percentage went below your requirement (" + emailRequirement + "%) for session no: " + session.getId() + ".\n\n"
                                + "For category no" + emailCategory + ", only " + emailCurrentSales + " sales tickets are sold.\n\n"
                                + "Please contact our Product Manager if you would like to associate any promotions to boast the sales."
                                + " For more details, please login to http://localhost:8080/MTiXBackend/ to view the sales report.\n\n"
                                + "With regards\nMTIX Sales Team", "[" + session.getAlert().getAlertType() + "] Sales Requirement not met", "smtp.gmail.com");
                    }

                }
            }
        }
    }

    //@Schedule(dayOfWeek = "Mon, Wed, Fri") ----------------------------- The Real Case
    @Schedule(hour = "*", minute = "*/10")
    public void automaticImportantTimer() {
        System.out.println("Timer(): Auto");
        int categoryNo;
        String eventType;
        double[] totalSales; //in the case where there are single digit, thats why double. Then When divide, do not have 0.
        double[] actualSales;
        boolean sendEmail = false;
        String emailCategory = "";
        String emailCurrentSales = "";
        String emailRequirement = "";

        Query q = em.createQuery("SELECT a FROM SessionEntity a");
        for (Object object : q.getResultList()) {
            SessionEntity session = (SessionEntity) object;

            em.refresh(session);

            Date today = Calendar.getInstance().getTime();

            if (session.getAlert() != null) {
                if (session.getAlert().getAlertType().equals("Important Alert") && today.after(session.getAlert().getAlertStartDate())
                        && today.before(session.getAlert().getAlertEndDate())) {
                    if (session.getEvent() != null) {
                        categoryNo = session.getEvent().getProperty().getCategory().size();
                        totalSales = new double[categoryNo];
                        actualSales = new double[categoryNo];
                        for (int i = 0; i < categoryNo; i++) {
                            totalSales[i] = 0;
                            actualSales[i] = 0;
                        }

                        for (Object obj : session.getEvent().getProperty().getSections()) {
                            SectionEntity sectionEntity = (SectionEntity) obj;

                            boolean isClosed = false;

                            for (Object checking : session.getSeatsInventory()) {
                                SessionSeatsInventory sessionSeatsInventory = (SessionSeatsInventory) checking;
                                if (sessionSeatsInventory.getSectionEntity().getId() == sectionEntity.getId()
                                        && sessionSeatsInventory.getStopTicketsSales()) {
                                    System.out.println("This section is closed. Hence it will not be counted" + sessionSeatsInventory.getId());
                                    isClosed = true;
                                    break;
                                }
                            }
                            if (isClosed) {

                            } else {
                                totalSales[sectionEntity.getCategory().getCategoryNum() - 1] += sectionEntity.getCapacity();
                            }
                        }
                    } else {
                        categoryNo = session.getSubEvent().getProperty().getCategory().size();
                        totalSales = new double[categoryNo];
                        actualSales = new double[categoryNo];
                        for (int i = 0; i < categoryNo; i++) {
                            totalSales[i] = 0;
                            actualSales[i] = 0;
                        }

                        for (Object obj : session.getSubEvent().getProperty().getSections()) {
                            SectionEntity sectionEntity = (SectionEntity) obj;

                            boolean isClosed = false;

                            for (Object checking : session.getSeatsInventory()) {
                                SessionSeatsInventory sessionSeatsInventory = (SessionSeatsInventory) checking;
                                if (sessionSeatsInventory.getSectionEntity().getId() == sectionEntity.getId()
                                        && sessionSeatsInventory.getStopTicketsSales()) {
                                    System.out.println("This section is closed. Hence it will not be counted" + sessionSeatsInventory.getId());
                                    isClosed = true;
                                    break;
                                }
                            }
                            if (isClosed) {

                            } else {
                                totalSales[sectionEntity.getCategory().getCategoryNum() - 1] += sectionEntity.getCapacity();
                            }
                        }
                    }

                    boolean isFreeSeating = false;
                    double actualSalesForFreeSeating = 0;

                    for (Object o : session.getTicketSales()) {
                        TicketSales ticketSales = (TicketSales) o;
                        if (session.getSeatOption().equals("Free Seating")) {
                            isFreeSeating = true;
                            actualSalesForFreeSeating += ticketSales.getTicketQuantity();
                        } else {
                            actualSales[ticketSales.getSectionEntity().getCategory().getCategoryNum() - 1] += ticketSales.getTicketQuantity();
                        }
                    }

                    //Comparing the sales percentage
                    for (int i = 0; i < actualSales.length; i++) {
                        System.out.println("Important Alert");
                        if (isFreeSeating) {
                            double totalSalesNumber = 0;
                            for (int j = 0; j < totalSales.length; j++) {
                                totalSalesNumber += totalSales[j];
                            }
                             System.out.println("ALERT : isFreeSeating =" + isFreeSeating + " Sales :" + totalSalesNumber + "vs" + actualSalesForFreeSeating);
                               
                            if (totalSalesNumber * ((double) session.getAlert().getSales() / 100) > actualSalesForFreeSeating) {
                                emailCategory = String.valueOf(i + 1);
                                emailCurrentSales = String.valueOf((int) actualSalesForFreeSeating);
                                emailRequirement = String.valueOf(session.getAlert().getSales());
                                sendEmail = true;
                                break;
                            } else {

                                break;
                            }

                        } else {

                            System.out.println("ALERT : isFreeSeating =" + isFreeSeating + " Sales :" + totalSales[i] + "vs" + actualSales[i]);
                            if (totalSales[i] * ((double) session.getAlert().getSales() / 100) > actualSales[i]) {
                                emailCategory = String.valueOf(i + 1);
                                emailCurrentSales = String.valueOf((int) actualSales[i]);
                                emailRequirement = String.valueOf(session.getAlert().getSales());
                                sendEmail = true;
                                break;
                            }
                        }
                    }

                    if (sendEmail) {
                        this.sendMail(session.getAlert().getInChargePersonEmail(), "is3012mtix@gmail.com", "Dear Sir/Mdm\n\n"
                                + "Ticket sales percentage went below your requirement (" + emailRequirement + "%) for session no: " + session.getId() + ".\n\n"
                                + "For category no" + emailCategory + ", only " + emailCurrentSales + " sales tickets are sold.\n\n"
                                + "Please contact our Product Manager if you would like to associate any promotions to boast the sales."
                                + " For more details, please login to http://localhost:8080/MTiXBackend/ to view the sales report.\n\n"
                                + "With regards\nMTIX Sales Team", "[" + session.getAlert().getAlertType() + "] Sales Requirement not met", "smtp.gmail.com");
                    }

                }
            }
        }
    }

    //@Schedule(hour = "*/23") ----------------------------- The Real Case
    @Schedule(hour = "*", minute = "*/30")
    public void automaticUrgentTimer() {
       System.out.println("Timer(): Auto");
        int categoryNo;
        String eventType;
        double[] totalSales; //in the case where there are single digit, thats why double. Then When divide, do not have 0.
        double[] actualSales;
        boolean sendEmail = false;
        String emailCategory = "";
        String emailCurrentSales = "";
        String emailRequirement = "";

        Query q = em.createQuery("SELECT a FROM SessionEntity a");
        for (Object object : q.getResultList()) {
            SessionEntity session = (SessionEntity) object;

            em.refresh(session);

            Date today = Calendar.getInstance().getTime();

            if (session.getAlert() != null) {
                if (session.getAlert().getAlertType().equals("Urgent Alert") && today.after(session.getAlert().getAlertStartDate())
                        && today.before(session.getAlert().getAlertEndDate())) {
                    if (session.getEvent() != null) {
                        categoryNo = session.getEvent().getProperty().getCategory().size();
                        totalSales = new double[categoryNo];
                        actualSales = new double[categoryNo];
                        for (int i = 0; i < categoryNo; i++) {
                            totalSales[i] = 0;
                            actualSales[i] = 0;
                        }

                        for (Object obj : session.getEvent().getProperty().getSections()) {
                            SectionEntity sectionEntity = (SectionEntity) obj;

                            boolean isClosed = false;

                            for (Object checking : session.getSeatsInventory()) {
                                SessionSeatsInventory sessionSeatsInventory = (SessionSeatsInventory) checking;
                                if (sessionSeatsInventory.getSectionEntity().getId() == sectionEntity.getId()
                                        && sessionSeatsInventory.getStopTicketsSales()) {
                                    System.out.println("This section is closed. Hence it will not be counted" + sessionSeatsInventory.getId());
                                    isClosed = true;
                                    break;
                                }
                            }
                            if (isClosed) {

                            } else {
                                totalSales[sectionEntity.getCategory().getCategoryNum() - 1] += sectionEntity.getCapacity();
                            }
                        }
                    } else {
                        categoryNo = session.getSubEvent().getProperty().getCategory().size();
                        totalSales = new double[categoryNo];
                        actualSales = new double[categoryNo];
                        for (int i = 0; i < categoryNo; i++) {
                            totalSales[i] = 0;
                            actualSales[i] = 0;
                        }

                        for (Object obj : session.getSubEvent().getProperty().getSections()) {
                            SectionEntity sectionEntity = (SectionEntity) obj;

                            boolean isClosed = false;

                            for (Object checking : session.getSeatsInventory()) {
                                SessionSeatsInventory sessionSeatsInventory = (SessionSeatsInventory) checking;
                                if (sessionSeatsInventory.getSectionEntity().getId() == sectionEntity.getId()
                                        && sessionSeatsInventory.getStopTicketsSales()) {
                                    System.out.println("This section is closed. Hence it will not be counted" + sessionSeatsInventory.getId());
                                    isClosed = true;
                                    break;
                                }
                            }
                            if (isClosed) {

                            } else {
                                totalSales[sectionEntity.getCategory().getCategoryNum() - 1] += sectionEntity.getCapacity();
                            }
                        }
                    }

                    boolean isFreeSeating = false;
                    double actualSalesForFreeSeating = 0;

                    for (Object o : session.getTicketSales()) {
                        TicketSales ticketSales = (TicketSales) o;
                        if (session.getSeatOption().equals("Free Seating")) {
                            isFreeSeating = true;
                            actualSalesForFreeSeating += ticketSales.getTicketQuantity();
                        } else {
                            actualSales[ticketSales.getSectionEntity().getCategory().getCategoryNum() - 1] += ticketSales.getTicketQuantity();
                        }
                    }

                    //Comparing the sales percentage
                    for (int i = 0; i < actualSales.length; i++) {
                        System.out.println("Urgent Alert");
                        if (isFreeSeating) {
                            double totalSalesNumber = 0;
                            for (int j = 0; j < totalSales.length; j++) {
                                totalSalesNumber += totalSales[j];
                            }
                             System.out.println("ALERT : isFreeSeating =" + isFreeSeating + " Sales :" + totalSalesNumber + "vs" + actualSalesForFreeSeating);
                               
                            if (totalSalesNumber * ((double) session.getAlert().getSales() / 100) > actualSalesForFreeSeating) {
                                emailCategory = String.valueOf(i + 1);
                                emailCurrentSales = String.valueOf((int) actualSalesForFreeSeating);
                                emailRequirement = String.valueOf(session.getAlert().getSales());
                                sendEmail = true;
                                break;
                            } else {

                                break;
                            }

                        } else {

                            System.out.println("ALERT : isFreeSeating =" + isFreeSeating + " Sales :" + totalSales[i] + "vs" + actualSales[i]);
                            if (totalSales[i] * ((double) session.getAlert().getSales() / 100) > actualSales[i]) {
                                emailCategory = String.valueOf(i + 1);
                                emailCurrentSales = String.valueOf((int) actualSales[i]);
                                emailRequirement = String.valueOf(session.getAlert().getSales());
                                sendEmail = true;
                                break;
                            }
                        }
                    }

                    if (sendEmail) {
                        this.sendMail(session.getAlert().getInChargePersonEmail(), "is3012mtix@gmail.com", "Dear Sir/Mdm\n\n"
                                + "Ticket sales percentage went below your requirement (" + emailRequirement + "%) for session no: " + session.getId() + ".\n\n"
                                + "For category no" + emailCategory + ", only " + emailCurrentSales + " sales tickets are sold.\n\n"
                                + "Please contact our Product Manager if you would like to associate any promotions to boast the sales."
                                + " For more details, please login to http://localhost:8080/MTiXBackend/ to view the sales report.\n\n"
                                + "With regards\nMTIX Sales Team", "[" + session.getAlert().getAlertType() + "] Sales Requirement not met", "smtp.gmail.com");
                    }

                }
            }
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

    public int sendMail(String to, String from, String message, String subject, String smtpServ) {
        try {
            Properties props = System.getProperties();
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

}
