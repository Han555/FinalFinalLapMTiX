/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Student-ID
 */
@Local
public interface BookingFeesSessionLocal {

    void createBookingFee(String event, String organizer, String startDate, String endDate, String fees, String status, String venue);

    ArrayList<ArrayList<String>> retrieveBookingFees();

    void updateFeesStatus(String parameter);

    void updateBookingStatus(String feesId, String status);
    
}
