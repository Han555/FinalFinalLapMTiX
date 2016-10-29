/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.contentmanagement;

import entity.CompanyProfile;
import entity.Event;
import entity.Promotion;
import entity.SessionEntity;
import entity.SubEvent;
import entity.UserEntity;
import entity.WebContentEntity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author JingYing
 */
@Stateless
public class WebContentBean implements WebContentBeanLocal {

    @PersistenceContext
    EntityManager em;
    UserEntity user;

    @Override
    public boolean signIn(String name) {
        try {
            Query q = em.createQuery("SELECT a FROM UserEntity a WHERE a.username=:name");
            q.setParameter("name", name);
            user = (UserEntity) q.getSingleResult(); //The user will be point to the real user here

            em.refresh(user);
            for (int i = 0; i < user.getRoles().size(); i++) {
                if (user.getRoles().get(i).equals("event organizer")) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<ArrayList> getEventList() {
        Query q = em.createQuery("SELECT a FROM Event a WHERE a.user.userId=:id");
        q.setParameter("id", user.getUserId());

        List<ArrayList> eventList = new ArrayList();
        ArrayList list;

        for (Object o : q.getResultList()) {
            Event eventEntity = (Event) o;
            list = new ArrayList();
            em.refresh(eventEntity);

            if (!eventEntity.getHasSubEvent() && eventEntity.getContent() == null) { //no subEvent & no created webpage
                list.add(eventEntity.getId());
                list.add(eventEntity.getName());
                list.add(eventEntity.getStart());
                list.add(eventEntity.getEnd());
                list.add("event");
                eventList.add(list);
            }
        }

        q = em.createQuery("SELECT a FROM SubEvent a WHERE a.user.userId=:id");
        q.setParameter("id", user.getUserId());

        for (Object o : q.getResultList()) {
            SubEvent subEventEntity = (SubEvent) o;
            em.refresh(subEventEntity);

            if (subEventEntity.getContent() == null) { //No created webpage
                list = new ArrayList();
                list.add(subEventEntity.getId());
                list.add(subEventEntity.getName());
                list.add(subEventEntity.getStart());
                list.add(subEventEntity.getEnd());
                list.add("subevent");
                eventList.add(list);
            }

        }
        return eventList;
    }

    @Override
    public ArrayList createWebpageInfo(long id, String type) {
        ArrayList data = new ArrayList();
        if (type.equals("event")) {
            Event event = em.find(Event.class, id);
            em.refresh(event);
            data.add(id); //0
            data.add(type); //1
            Date startDate = event.getStart();
            Date endDate = event.getEnd();
            String startDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            data.add(startDateString); //2
            data.add(endDateString); //3
        } else {
            SubEvent subevent = em.find(SubEvent.class, id);
            em.refresh(subevent);
            data.add(id);
            data.add(type);
            Date startDate = subevent.getStart();
            Date endDate = subevent.getEnd();
            String startDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            data.add(startDateString); //2
            data.add(endDateString); //3
        }

        return data;
    }

    @Override
    public void createEventWebpage(Part filePart, String eventTitle, String synopsis, String programDetails, String rules,
            String details, String start, String end, String id, String type, String ext) {

        try {
            WebContentEntity webContentEntity = new WebContentEntity();

            String fileName = "";
            if (type.equals("event")) {
                Event event = em.find(Event.class, Long.valueOf(id));
                em.refresh(event);
                fileName = event.getId() + "_event";
                webContentEntity.setEvent(event);
                webContentEntity.setSubevent(null);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = formatter.parse(start);
                Date endDate = formatter.parse(end);
                webContentEntity.create(eventTitle, synopsis, fileName + "." + ext, details, rules, programDetails, startDate, endDate);
                em.persist(webContentEntity);

                event.setContent(webContentEntity);
            } else {
                SubEvent subevent = em.find(SubEvent.class, Long.valueOf(id));
                em.refresh(subevent);
                fileName = subevent.getId() + "_subevent";
                webContentEntity.setEvent(null);
                webContentEntity.setSubevent(subevent);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = formatter.parse(start);
                Date endDate = formatter.parse(end);
                webContentEntity.create(eventTitle, synopsis, fileName + "." + ext, details, rules, programDetails, startDate, endDate);
                em.persist(webContentEntity);

                subevent.setContent(webContentEntity);
            }

            //Store the file into system
            OutputStream out = null;
            InputStream filecontent = null;

            out = new FileOutputStream(new File("C:/Users/Student-ID/Desktop/Project/contentManagement" + File.separator
                    + fileName + "." + ext));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.close();
            filecontent.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<ArrayList> getEditedWebEventList() {
        Query q = em.createQuery("SELECT a FROM Event a WHERE a.user.userId=:id");
        q.setParameter("id", user.getUserId());

        List<ArrayList> eventList = new ArrayList();
        ArrayList list;

        for (Object o : q.getResultList()) {
            Event eventEntity = (Event) o;
            list = new ArrayList();
            em.refresh(eventEntity);

            if (!eventEntity.getHasSubEvent() && eventEntity.getContent() != null) { //no subEvent & no created webpage
                list.add(eventEntity.getId());
                list.add(eventEntity.getName());
                list.add(eventEntity.getStart());
                list.add(eventEntity.getEnd());
                list.add("event");
                eventList.add(list);
            }
        }

        q = em.createQuery("SELECT a FROM SubEvent a WHERE a.user.userId=:id");
        q.setParameter("id", user.getUserId());

        for (Object o : q.getResultList()) {
            SubEvent subEventEntity = (SubEvent) o;
            em.refresh(subEventEntity);

            if (subEventEntity.getContent() != null) { //No created webpage
                list = new ArrayList();
                list.add(subEventEntity.getId());
                list.add(subEventEntity.getName());
                list.add(subEventEntity.getStart());
                list.add(subEventEntity.getEnd());
                list.add("subevent");
                eventList.add(list);
            }

        }
        return eventList;
    }

    @Override
    public ArrayList editWebpageInfo(long id, String type) {
        ArrayList data = new ArrayList();
        if (type.equals("event")) {
            Event event = em.find(Event.class, id);
            em.refresh(event);
            data.add(id); //0
            data.add(type); //1
            Date startDate = event.getStart();
            Date endDate = event.getEnd();
            String startDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            data.add(startDateString); //2
            data.add(endDateString); //3

            data.add(event.getContent().getEventTitle()); //4
            data.add(event.getContent().getSynopsis()); //5
            data.add(event.getContent().getFileName()); //6
            data.add(event.getContent().getOtherDetails()); //7
            data.add(event.getContent().getAdmissionRules()); //8
            data.add(event.getContent().getProgramDetails()); //9
            data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(event.getContent().getStart())); //10
            data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(event.getContent().getEnd())); //11
        } else {
            SubEvent subevent = em.find(SubEvent.class, id);
            em.refresh(subevent);
            data.add(id); //0
            data.add(type); //1
            Date startDate = subevent.getStart();
            Date endDate = subevent.getEnd();
            String startDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            data.add(startDateString); //2
            data.add(endDateString); //3

            data.add(subevent.getContent().getEventTitle()); //4
            data.add(subevent.getContent().getSynopsis()); //5
            data.add(subevent.getContent().getFileName()); //6
            data.add(subevent.getContent().getOtherDetails()); //7
            data.add(subevent.getContent().getAdmissionRules()); //8
            data.add(subevent.getContent().getProgramDetails()); //9
            data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(subevent.getContent().getStart())); //10
            data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(subevent.getContent().getEnd())); //11
        }

        return data;
    }

    public void editEventWebpage(Part filePart, String eventTitle, String synopsis, String programDetails, String rules,
            String details, String start, String end, String id, String type, String ext) {

        try {
            WebContentEntity webContentEntity;

            String fileName = "";
            if (type.equals("event")) {
                Event event = em.find(Event.class, Long.valueOf(id));
                em.refresh(event);
                fileName = event.getId() + "_event";

                webContentEntity = event.getContent();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = formatter.parse(start);
                Date endDate = formatter.parse(end);
                webContentEntity.create(eventTitle, synopsis, fileName + "." + ext, details, rules, programDetails, startDate, endDate);
            } else {
                SubEvent subevent = em.find(SubEvent.class, Long.valueOf(id));
                em.refresh(subevent);
                fileName = subevent.getId() + "_subevent";

                webContentEntity = subevent.getContent();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = formatter.parse(start);
                Date endDate = formatter.parse(end);
                webContentEntity.create(eventTitle, synopsis, fileName + "." + ext, details, rules, programDetails, startDate, endDate);
            }

            Path path = Paths.get("C:/Users/Student-ID/Desktop/Project/contentManagement" + File.separator
                    + fileName + "." + ext);
            Files.delete(path);
            Thread.sleep(1000);

            //Store the file into system
            OutputStream out = null;
            InputStream filecontent = null;

            out = new FileOutputStream(new File("C:/Users/Student-ID/Desktop/Project/contentManagement" + File.separator
                    + fileName + "." + ext));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.close();
            filecontent.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void deleteEventWebpage(String id, String type) {

        try {
            WebContentEntity webContentEntity;

            String fileName = "";
            if (type.equals("event")) {
                Event event = em.find(Event.class, Long.valueOf(id));
                em.refresh(event);

                webContentEntity = event.getContent();

            } else {
                SubEvent subevent = em.find(SubEvent.class, Long.valueOf(id));
                em.refresh(subevent);

                webContentEntity = subevent.getContent();
            }

            Path path = Paths.get("C:/Users/Student-ID/Desktop/Project/contentManagement" + File.separator
                    + webContentEntity.getFileName());
            Files.delete(path);
            Thread.sleep(1000);

            em.remove(webContentEntity);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<ArrayList> geWebpageList() {
        Query q = em.createQuery("SELECT a FROM WebContentEntity a");

        List<ArrayList> webPageList = new ArrayList();
        ArrayList list;

        for (Object o : q.getResultList()) {
            WebContentEntity webpage = (WebContentEntity) o;
            list = new ArrayList();
            em.refresh(webpage);

            if (!webpage.getReviewWebsite()) { //webpage that has yet to reivewed by manager 
                list.add(webpage.getId()); //0
                if (webpage.getEvent() != null) {
                    list.add(webpage.getEvent().getName()); //1
                } else {
                    list.add(webpage.getSubevent().getName()); //2
                }

                list.add(webpage.getEventTitle()); //3
                list.add((String) new SimpleDateFormat("yyyy-MM-dd").format(webpage.getStart())); //4

                webPageList.add(list);
            }
        }

        return webPageList;
    }

    @Override
    public ArrayList reviewWebpageInfo(String id) {
        ArrayList data = new ArrayList();
        WebContentEntity content = em.find(WebContentEntity.class, Long.valueOf(id));
        em.refresh(content);

        data.add(content.getId());//0

        data.add(content.getEventTitle()); //1
        data.add(content.getSynopsis()); //2
        data.add(content.getFileName()); //3
        data.add(content.getOtherDetails()); //4
        data.add(content.getAdmissionRules()); //5
        data.add(content.getProgramDetails()); //6
        data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(content.getStart())); //7
        data.add((String) new SimpleDateFormat("yyyy-MM-dd").format(content.getEnd())); //8

        return data;
    }

    @Override
    public void webpageReviewed(String id, String review, String apply) {
        ArrayList data = new ArrayList();
        WebContentEntity content = em.find(WebContentEntity.class, Long.valueOf(id));
        em.refresh(content);
        String result = "Rejected";

        content.setReviewComment(review);
        content.setReviewWebsite(true);
        if (apply.equals("yes")) {
            content.setApproved(true);
            result = "Approved";
        }

        String email;
        if (content.getEvent() != null) {
            email = content.getEvent().getUser().getUsername();
        } else {
            email = content.getSubevent().getUser().getUsername();
        }

        this.sendMail(email, "is3012mtix@gmail.com", "Dear Sir/Mdm\n\n"
                + "Your webpage has been " + result + ".\n\n"
                + "Website Title : " + content.getEventTitle() + ".\n"
                + "Website Expected Published Date : " + content.getStart() + ".\n"
                + "Content Manager Comment : " + review + ".\n\n"
                + " To edit your Event Webpage, Please login to http://localhost:8080/MTiXBackend/. \n\n"
                + "With regards\nMTIX Content Management Team", "Your Webpage Has Been " + result, "smtp.gmail.com");
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
            Authenticator auth = new WebContentBean.SMTPAuthenticator();
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

    @Override
    public void createCompanyWebpage(Part filePart, String mission, String vision, String aboutUs, String contactDetails, String career, String otherDetails, String ext) {
        try {

            //Link it to super admin 
            Query q = em.createQuery("SELECT a FROM UserEntity a");
            UserEntity companySuperAdmin = new UserEntity();

            for (Object o : q.getResultList()) {
                companySuperAdmin = (UserEntity) o;
                em.refresh(companySuperAdmin);
                for (int i = 0; i < companySuperAdmin.getRoles().size(); i++) {
                    if (companySuperAdmin.getRoles().get(i).equals("super administrator")) {
                        break;
                    }
                }
            }

            CompanyProfile profile = new CompanyProfile();

            String fileName = "company_" + companySuperAdmin.getUserId() + "." + ext; //Need to plus user*

            //Store the file into system
            OutputStream out = null;
            InputStream filecontent = null;

            out = new FileOutputStream(new File("C:/Users/Student-ID/Desktop/Project/contentManagement" + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.close();
            filecontent.close();

            profile.setAboutUs(aboutUs);
            profile.setCareer(career);
            profile.setContactDetails(contactDetails);
            profile.setFileName(fileName);
            profile.setMission(mission);
            profile.setOtherDetails(otherDetails);
            profile.setVision(vision);
            profile.setUser(companySuperAdmin);
            em.persist(profile);

            companySuperAdmin.setCompanyProfile(profile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList getCompanyInfo() {//Link it to super admin 
        Query q = em.createQuery("SELECT a FROM UserEntity a");
        UserEntity companySuperAdmin = new UserEntity();

        //Get the company super admin and find out the company content link to it. 
        for (Object o : q.getResultList()) {
            companySuperAdmin = (UserEntity) o;
            em.refresh(companySuperAdmin);
            for (int i = 0; i < companySuperAdmin.getRoles().size(); i++) {
                if (companySuperAdmin.getRoles().get(i).equals("super administrator")) {
                    break;
                }
            }
        }

        ArrayList data = new ArrayList();
        CompanyProfile company = companySuperAdmin.getCompanyProfile();

        data.add(company.getId()); //0
        data.add(company.getAboutUs()); //1
        data.add(company.getCareer()); //2
        data.add(company.getContactDetails()); //3
        data.add(company.getFileName()); //4
        data.add(company.getMission()); //5
        data.add(company.getOtherDetails()); //6
        data.add(company.getVision()); //7
        return data;
    }

    @Override
    public void editCompanyWebpage(long id, Part filePart, String mission, String vision, String aboutUs, String contactDetails, String career, String otherDetails, String ext) {
        try {
            CompanyProfile companyProfile = em.find(CompanyProfile.class, id);
            em.refresh(companyProfile);

            String fileName = companyProfile.getFileName(); 
            
            Path path = Paths.get("C:/Users/Student-ID/Desktop/Project/contentManagement" + File.separator
                    + fileName);
            Files.delete(path);
            Thread.sleep(1000);

            //Store the file into system
            OutputStream out = null;
            InputStream filecontent = null;

            out = new FileOutputStream(new File("C:/Users/Student-ID/Desktop/Project/contentManagement" + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.close();
            filecontent.close();
            
            companyProfile.setAboutUs(aboutUs);
            companyProfile.setCareer(career);
            companyProfile.setContactDetails(contactDetails);
            companyProfile.setFileName(fileName);
            companyProfile.setMission(mission);
            companyProfile.setOtherDetails(otherDetails);
            companyProfile.setVision(vision);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

}
