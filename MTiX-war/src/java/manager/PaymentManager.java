/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import session.stateless.PaymentSessionLocal;

/**
 *
 * @author Student-ID
 */
public class PaymentManager {
    private PaymentSessionLocal paymentSessionLocal;
    
    public PaymentManager(PaymentSessionLocal paymentSessionLocal) {
        this.paymentSessionLocal = paymentSessionLocal;
    }
    
    public void createRecord(String payer, String receiver, String eventName, String ticketQuantity, String amount, String promotion) {
        paymentSessionLocal.createPayment(payer, receiver, eventName, ticketQuantity, amount, promotion);
    }
    
    public ArrayList<ArrayList<String>> getRecords(String username) {
        System.out.println("Entered payment manager getRecords");
        ArrayList<ArrayList<String>> records = paymentSessionLocal.retrievePayments(username);
        System.out.println("Entered payment manager retrieve payments");
        ArrayList<ArrayList<String>> arrangedRecords = new ArrayList();
        int size = records.size();
        size--;
        
        for(int i=size; i>=0; i--) {
            arrangedRecords.add(records.get(i));
        }
        System.out.println("leaving payment manager");
        return arrangedRecords;
    }
    
    public ArrayList<ArrayList<String>> recordPage(ArrayList<ArrayList<String>> records, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> recordPage = new ArrayList();
        int size = records.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            ArrayList<String> record = new ArrayList();
            record.add(records.get(i).get(0));
            record.add(records.get(i).get(1));
            record.add(records.get(i).get(2));
            record.add(records.get(i).get(3));
            record.add(records.get(i).get(4));
            recordPage.add(record);
        }
        
       
        return recordPage;
    }
    
    public String getEventName(String paymentId) {
        return paymentSessionLocal.retrieveEventName(paymentId);
    }
    
    public void addAddress(String paymentId, String address, String country, String city, String zip) {
        paymentSessionLocal.updateRecord(paymentId, address, country, city, zip);
    }
    
    public void updatePaymentStatus(String paymentId) {
        paymentSessionLocal.updateStatus(paymentId);
    }
    
    public void sendEmail(String to, String from, String message, String subject, String smtpServ) {
        paymentSessionLocal.sendMail(to, from, message, subject, smtpServ);
    }
    
    public ArrayList<String> getRecordDetails(String paymentId) {
        return paymentSessionLocal.retrieveAddress(paymentId);
    }
    
    
    public ArrayList<String> getEvents(String receiver) {
        return paymentSessionLocal.retrieveEvents(receiver);
    }
    
    public void getRefund(String paymentId) {
        paymentSessionLocal.requestRefund(paymentId);
    }
    
    public void updateTicketTakings(String paymentId) {
        paymentSessionLocal.updateTicketTakings(paymentId);
    }
    
    public String convertPrices(String quantity, String price) {
        double realPrice = Double.parseDouble(price);
        int realQuantity = Integer.parseInt(quantity);
        
        double finalPrice = realPrice / realQuantity;
        finalPrice += 3;
        
        return String.valueOf(finalPrice);
    }
    
    
    
    public boolean checkRefundValidity(String date1, String date2) {
        String[] dateOne = date1.split("/");
        String[] dateTwo = date2.split("/");

        /*Calendar calendar1 = Calendar.getInstance(); 
         Calendar calendar2 = Calendar.getInstance();
        
         calendar1.set(Integer.parseInt(dateOne[0]), Integer.parseInt(dateOne[1]), Integer.parseInt(dateOne[2])); 
         calendar2.set(Integer.parseInt(dateTwo[0]), Integer.parseInt(dateTwo[1]), Integer.parseInt(dateTwo[2]));
        
         long milis1 = calendar1.getTimeInMillis(); 
         long milis2 = calendar2.getTimeInMillis();
         long day = milis2 - milis1;
         long diffDays = day / (24 * 60 * 60 * 1000);
         System.out.println("DiffDays = " + diffDays);
         if(diffDays >= 8) {
         System.out.println("reach true");
         return true;
         } else {
         System.out.println("reach false");
         return false;
         }*/
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        cal1.set(Integer.parseInt(dateOne[0]), Integer.parseInt(dateOne[1]), Integer.parseInt(dateOne[2]));
        cal2.set(Integer.parseInt(dateTwo[0]), Integer.parseInt(dateTwo[1]), Integer.parseInt(dateTwo[2]));
        System.out.println("cal2 : " + cal2.getTimeInMillis());
        System.out.println("cal1 : " + cal1.getTimeInMillis());
        int diff = (int) ((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        if(Integer.parseInt(dateTwo[1]) == 11) {
            ++diff;
        }
        
        if(Integer.parseInt(dateTwo[1]) == 2 && Integer.parseInt(dateTwo[0]) == 2017) {
            diff = diff +3;
        }
        
        if(Integer.parseInt(dateTwo[1]) == 4 && Integer.parseInt(dateTwo[0]) == 2017) {
            ++diff;
        }
        
        if(Integer.parseInt(dateTwo[1]) == 6 && Integer.parseInt(dateTwo[0]) == 2017) {
            ++diff;
        }
        
        if(Integer.parseInt(dateTwo[1]) == 7 && Integer.parseInt(dateTwo[0]) == 2017) {
            ++diff;
        }
        if(Integer.parseInt(dateTwo[1]) == 9 && Integer.parseInt(dateTwo[0]) == 2017) {
            diff = diff + 2;
        }
        System.out.println("diff : " +diff);
        if (diff >= 8) {
            System.out.println("reach true");
            return true;
        } else {
            System.out.println("reach false");
            return false;
        }  
    }
    
    
}
