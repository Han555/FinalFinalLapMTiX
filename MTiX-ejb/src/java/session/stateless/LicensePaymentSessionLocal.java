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
public interface LicensePaymentSessionLocal {

    void createLicensingPayment(String company, String email, String amount, String date, String user);

    ArrayList<ArrayList<String>> retrieveLicensingAccounts();

    void sendPasInvoice(String accountId);

    void updatePaymentStatus(String accountId);

    int sendMail(String to, String from, String message, String subject, String smtpServ);

    ArrayList<String> retrieveAccRecord(String accountId);

    
}
