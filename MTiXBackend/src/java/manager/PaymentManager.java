/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import java.util.Random;
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
        ArrayList<ArrayList<String>> records = paymentSessionLocal.retrievePayments(username);
        ArrayList<ArrayList<String>> arrangedRecords = new ArrayList();
        int size = records.size();
        size--;
        
        for(int i=size; i>=0; i--) {
            arrangedRecords.add(records.get(i));
        }
        
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
        
    public ArrayList<String> eventPage(ArrayList<String> events, int offset, int noOfRecords) {
        ArrayList<String> eventPage = new ArrayList();
        int size = events.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            
            eventPage.add(events.get(i));
        }
        
       
        return eventPage;
    }
    
    public ArrayList<ArrayList<String>> getEventRecords(String event, String receiver) {
        return paymentSessionLocal.retrieveRecords(event, receiver);
    }
    
     public ArrayList<ArrayList<String>> getEventRecordsPage(ArrayList<ArrayList<String>> eventRecords, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> eventRecordsPage = new ArrayList();
        int size = eventRecords.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            
            ArrayList<String> eventRecord = new ArrayList();
            eventRecord.add(eventRecords.get(i).get(0));
            eventRecord.add(eventRecords.get(i).get(1));
            eventRecord.add(eventRecords.get(i).get(2));
            eventRecord.add(eventRecords.get(i).get(3));
            eventRecordsPage.add(eventRecord);
        }
        
       
        return eventRecordsPage;
    }
     
     public ArrayList<ArrayList<String>> getRefundRecords() {
        ArrayList<ArrayList<String>> refundRecords = paymentSessionLocal.retrieveRefundRecords();
        ArrayList<ArrayList<String>> arrangedRecords = new ArrayList();
        int size = refundRecords.size();
        size--;
        
        for(int i=size; i>=0; i--) {
            arrangedRecords.add(refundRecords.get(i));
        }
        
        return arrangedRecords;
    }
     
     public ArrayList<ArrayList<String>> refundRecordsPage(ArrayList<ArrayList<String>> refundRecords, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> refundRecordsPage = new ArrayList();
        int size = refundRecords.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            
            ArrayList<String> refundRecord = new ArrayList();
            refundRecord.add(refundRecords.get(i).get(0));
            refundRecord.add(refundRecords.get(i).get(1));
            refundRecord.add(refundRecords.get(i).get(2));
            refundRecord.add(refundRecords.get(i).get(3));
            refundRecord.add(refundRecords.get(i).get(4));
            refundRecord.add(refundRecords.get(i).get(5));
            refundRecord.add(refundRecords.get(i).get(6));
            refundRecordsPage.add(refundRecord);
        }
        
       
        return refundRecordsPage;
    }
     
     public void changeRefundStatus(String paymentId) {
         paymentSessionLocal.updateRefundStatus(paymentId);
     }
     
     public String getEventdate(String id) {
        return paymentSessionLocal.retrieveEventDate(id);
    }
     
     public String analyzeValue() {
         StringBuffer sb = new StringBuffer();
        char[] chars = "012345".toCharArray();
        Random random = new Random();
        for (int i = 1; i <= 1; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        String value = new String(sb);

        return value;
     }
     
     public ArrayList<ArrayList<String>> analyzeUsers() {
         ArrayList<ArrayList<String>> userTable = new ArrayList();
         
         
         return userTable;
     }
     
     public ArrayList<ArrayList<String>> getUserRecordsPage(ArrayList<ArrayList<String>> eventRecords, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> eventRecordsPage = new ArrayList();
        int size = eventRecords.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            
            ArrayList<String> eventRecord = new ArrayList();
            eventRecord.add(eventRecords.get(i).get(0));
            eventRecord.add(eventRecords.get(i).get(1));
            eventRecord.add(eventRecords.get(i).get(2));
            eventRecord.add(eventRecords.get(i).get(3));
            eventRecord.add(eventRecords.get(i).get(4));
            eventRecordsPage.add(eventRecord);
        }
        
       
        return eventRecordsPage;
    }
}
