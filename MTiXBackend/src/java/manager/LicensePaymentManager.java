/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import session.stateless.LicensePaymentSessionLocal;

/**
 *
 * @author Student-ID
 */
public class LicensePaymentManager {
    private LicensePaymentSessionLocal licensePaymentSession;
    
    public LicensePaymentManager(LicensePaymentSessionLocal licensePaymentSession) {
        this.licensePaymentSession = licensePaymentSession;
    }
    
    public void addLicensingPayment(String company, String email, String amount, String date, String user) {
        licensePaymentSession.createLicensingPayment(company, email, amount, date, user);
    }
    
    public ArrayList<ArrayList<String>> getLicensingAccounts() {
        ArrayList<ArrayList<String>> accounts = licensePaymentSession.retrieveLicensingAccounts();
        ArrayList<ArrayList<String>> arrangedAccounts = new ArrayList();
        int size = accounts.size();
        size--;
        
        for(int i=size; i>=0; i--) {
            arrangedAccounts.add(accounts.get(i));
        }
        
        return arrangedAccounts;
    }
    
    public ArrayList<ArrayList<String>> accountPage(ArrayList<ArrayList<String>> accounts, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> accountPage = new ArrayList();
        int size = accounts.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            ArrayList<String> account = new ArrayList();
            account.add(accounts.get(i).get(0));
            account.add(accounts.get(i).get(1));
            account.add(accounts.get(i).get(2));
            account.add(accounts.get(i).get(3));
            account.add(accounts.get(i).get(4));
            account.add(accounts.get(i).get(5));
            account.add(accounts.get(i).get(6));
            accountPage.add(account);
        }
        
       
        return accountPage;
    }
    
    public void updatePasInvoiceStatus(String accountId) {
        licensePaymentSession.sendPasInvoice(accountId);
    }
    
    public void markAsPaid(String accountId) {
        licensePaymentSession.updatePaymentStatus(accountId);
    }
    
    public void sendEmail(String to, String from, String message, String subject, String smtpServ) {
        licensePaymentSession.sendMail(to, from, message, subject, smtpServ);
    }
    
    public ArrayList<String> getAccRecord(String accountId) {
        return licensePaymentSession.retrieveAccRecord(accountId);
    }
}
