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
public interface PaymentSessionLocal {

    void createPayment(String payer, String receiver, String eventName, String ticketQuantity, String amount, String promotion);

    ArrayList<ArrayList<String>> retrievePayments(String username);

    String retrieveEventName(String paymentId);

    void updateRecord(String paymentId, String address, String country, String city, String zip);

    void updateStatus(String paymentId);

    int sendMail(String to, String from, String message, String subject, String smtpServ);

    ArrayList<String> retrieveAddress(String paymentId);

    ArrayList<String> retrieveEvents(String receiver);

    ArrayList<ArrayList<String>> retrieveRecords(String event, String receiver);

    void requestRefund(String paymentId);

    ArrayList<ArrayList<String>> retrieveRefundRecords();

    void updateRefundStatus(String paymentId);

    void updateTicketTakings(String paymentId);

    String retrieveEventDate(String id);
}
