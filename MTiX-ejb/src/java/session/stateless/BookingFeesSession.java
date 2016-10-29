/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.BookingFees;
import entity.UserEntity;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Student-ID
 */
@Stateless
public class BookingFeesSession implements BookingFeesSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createBookingFee(String event, String organizer, String startDate, String endDate, String fees, String status, String venue) {
        BookingFees bookingFee = new BookingFees();
        bookingFee.createFees(event, organizer, startDate, endDate, fees, status, venue);

        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + organizer + "'");
        for (Object o : q.getResultList()) {
            UserEntity u = new UserEntity();
            u = (UserEntity) o;
            u.getBookingfees().add(bookingFee);
            entityManager.merge(u);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> retrieveBookingFees() {
        Query q = entityManager.createQuery("SELECT b FROM BookingFees b ");
        ArrayList<ArrayList<String>> bookingFees = new ArrayList();
        
        for(Object o: q.getResultList()) {
            BookingFees bookingFee = new BookingFees();
            bookingFee = (BookingFees) o;
            ArrayList<String> fees = new ArrayList();
            fees.add(Long.toString(bookingFee.getId()));
            fees.add(bookingFee.getEvent());
            fees.add(bookingFee.getOrganizer());
            fees.add(bookingFee.getStartDate());
            fees.add(bookingFee.getEndDate());
            fees.add(bookingFee.getFees());
            fees.add(bookingFee.getStatus());
            fees.add(bookingFee.getVenue());
            bookingFees.add(fees);
        }
        return bookingFees;
    }

    @Override
    public void updateFeesStatus(String parameter) {
        Query query = entityManager.createQuery("UPDATE BookingFees b SET b.status = 'paid' WHERE b.id = " + parameter);
        query.executeUpdate();
    }

    @Override
    public void updateBookingStatus(String feesId, String status) {
        Query query = entityManager.createQuery("UPDATE BookingFees b SET b.status = " +"'"+status+"'"+" WHERE b.id = " + feesId);
        query.executeUpdate();
    }
    
    
    
    

}
