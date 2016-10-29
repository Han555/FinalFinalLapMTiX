/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import entity.Event;
import entity.SectionEntity;
import entity.SessionCategoryPrice;
import entity.SessionEntity;
import entity.SessionSeatsInventory;
import entity.SubEvent;
import entity.TicketSales;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JingYing
 */
@Stateless
public class GetAllProductDetails implements GetAllProductDetailsLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ArrayList> getAllSessions() {
        List<ArrayList> sessionsInventory = new ArrayList();
        Query q = em.createQuery("SELECT a FROM SessionEntity a");
        for (Object object : q.getResultList()) {
            ArrayList attributes = new ArrayList();
            SessionEntity sessionEntity = (SessionEntity) object;
            em.refresh(sessionEntity);

            if (sessionEntity.getEvent() != null) {
                attributes.add(sessionEntity.getEvent().getId()); //0
                attributes.add("event"); //1
                attributes.add(sessionEntity.getEvent().getProperty().getPropertyName());  //2
                attributes.add(sessionEntity.getEvent().getProperty().getCategory().size()); //3
            } else {
                attributes.add(sessionEntity.getSubEvent().getId());//0
                attributes.add("subevent");//1
                attributes.add(sessionEntity.getSubEvent().getProperty().getPropertyName());  //2
                attributes.add(sessionEntity.getSubEvent().getProperty().getCategory().size()); //3
            }

            attributes.add(sessionEntity.getName()); //4
            attributes.add(sessionEntity.getDescriptions()); //5

            String start = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(sessionEntity.getTimeStart());
            String end = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(sessionEntity.getTimeEnd());

            attributes.add(start); //6
            attributes.add(end);  //7
            attributes.add(sessionEntity.getId()); //8

            String prices = "";

            for (Object o : sessionEntity.getPrice()) {
                SessionCategoryPrice priceEntity = (SessionCategoryPrice) o;
                prices += priceEntity.getPrice() + " (" + priceEntity.getCategory().getCategoryNum() + "), ";
            }

            if (!prices.equals("")) {
                prices = prices.substring(0, prices.length() - 2);
                System.out.println(prices);
            }

            attributes.add(prices); //9

            if (sessionEntity.getAlert() != null) {
                attributes.add(sessionEntity.getAlert().getAlertType()); //10
                attributes.add(sessionEntity.getAlert().getSales()); //11
                attributes.add(sessionEntity.getAlert().getInChargePersonEmail()); //12

                String start1 = new SimpleDateFormat("dd/MM/yyyy").format(sessionEntity.getAlert().getAlertStartDate());
                String end1 = new SimpleDateFormat("dd/MM/yyyy").format(sessionEntity.getAlert().getAlertEndDate());

                attributes.add(start1); //13
                attributes.add(end1); //14
            }

            sessionsInventory.add(attributes);
        }

        return sessionsInventory;
    }

    @Override
    public List<ArrayList> getSalesDetails() {
        int categoryNo;
        String eventType;
        double[] totalSales; //in the case where there are single digit, thats why double. Then When divide, do not have 0.
        double[] actualSales;
        List<ArrayList> salesDetails = new ArrayList();

        Query q = em.createQuery("SELECT a FROM SessionEntity a");
        for (Object object : q.getResultList()) {
            ArrayList attributes = new ArrayList();
            SessionEntity session = (SessionEntity) object;

            em.refresh(session);

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
                System.out.println("GET ALL PRODUCT DETAILS ------ session : " + session.getSeatOption().toString() + "END");
                if (session.getSeatOption().equals("Free Seating")) {
                    System.out.println("INNNNNNNNNNNNNNNNNNNNNNNNNN");
                    isFreeSeating = true;
                    actualSalesForFreeSeating += ticketSales.getTicketQuantity();

                } else {
                    actualSales[ticketSales.getSectionEntity().getCategory().getCategoryNum() - 1] += ticketSales.getTicketQuantity();
                }
            }
            if (session.getEvent() != null) {
                attributes.add(session.getEvent().getId()); //0
                attributes.add("event"); //1
            } else {
                attributes.add(session.getSubEvent().getId());//0
                attributes.add("subevent");//1
            }
            attributes.add(session.getId()); //2
            attributes.add(session.getName()); //3
            
            System.out.println("GET ALL PRODUCT DETAILS ------ " + isFreeSeating);
            

            //Comparing the sales percentage
            for (int i = 0; i < actualSales.length; i++) {
                if (isFreeSeating || session.getSeatOption().equals("Free Seating")) {
                    double totalSalesNumber = 0;
                    for (int j = 0; j < totalSales.length; j++){
                        totalSalesNumber += totalSales[j];
                    }
                    attributes.add((int)actualSalesForFreeSeating);//4
                    attributes.add((int)totalSalesNumber);
                    attributes.add(actualSalesForFreeSeating/totalSalesNumber * 100);
                    attributes.add(i+1);
                    break;
                } else {
                    attributes.add((int) actualSales[i]); //4
                    attributes.add((int) totalSales[i]); //5
                    attributes.add(actualSales[i] / totalSales[i] * 100); //6
                    attributes.add(i + 1); // 7 The category number 
                }
            }
            salesDetails.add(attributes);
        }
        return salesDetails;
    }

}
