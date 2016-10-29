/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import session.stateless.BookingFeesSessionLocal;

/**
 *
 * @author Student-ID
 */
public class BookingFeesManager {
    private BookingFeesSessionLocal bookingFeesSession;
    
    public BookingFeesManager(BookingFeesSessionLocal bookingFeesSession) {
        this.bookingFeesSession = bookingFeesSession;
    }
    
    public void createFee(String event, String organizer, String startDate, String endDate, String fees, String status, String venue) {
        bookingFeesSession.createBookingFee(event, organizer, startDate, endDate, fees, status, venue);
    }
    
    public ArrayList<ArrayList<String>> getBookingFees() {
        ArrayList<ArrayList<String>> bookingFees = bookingFeesSession.retrieveBookingFees();
        ArrayList<ArrayList<String>> arrangedFees = new ArrayList();
        int size = bookingFees.size();
        size--;
        
        for(int i=size; i>=0; i--) {
            arrangedFees.add(bookingFees.get(i));
        }
        
        return arrangedFees;
    }
    
    public ArrayList<ArrayList<String>> feesPage(ArrayList<ArrayList<String>> fees, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> feesPage = new ArrayList();
        int size = fees.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            ArrayList<String> page = new ArrayList();
            page.add(fees.get(i).get(0));
            page.add(fees.get(i).get(1));
            page.add(fees.get(i).get(2));
            page.add(fees.get(i).get(3));
            page.add(fees.get(i).get(4));
            page.add(fees.get(i).get(5));
            page.add(fees.get(i).get(6));
            page.add(fees.get(i).get(7));
            feesPage.add(page);
        }

        return feesPage;
    }
    
    public void editFeesStatus(String id) {
        bookingFeesSession.updateFeesStatus(id);
    }
    
    public void editBookingStatus(String feesIds, String status) {
        bookingFeesSession.updateBookingStatus(feesIds, status);
    }
}
