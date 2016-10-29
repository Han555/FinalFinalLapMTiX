/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.PaymentManager;
import session.stateless.PaymentSessionLocal;

/**
 *
 * @author Student-ID
 */
@WebServlet(name = "FinanceController", urlPatterns = {"/FinanceController", "/FinanceController?*"})
public class FinanceController extends HttpServlet {

    @EJB
    private PaymentSessionLocal paymentSession;
    String currentUser = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action;
            String paymentNo;

            int page = 1;
            int recordsPerPage = 8;
            PaymentManager paymentManager = new PaymentManager(paymentSession);

            action = request.getParameter("action");
            paymentNo = request.getParameter("id");
            String name = request.getParameter("name");
            System.out.println("here special");

            System.out.println("Action = " + action);
            System.out.println("payment Id = " + paymentNo);

            if (action.equals("getUser")) {
                String username = (String) request.getSession(false).getAttribute("username");
                currentUser = username;
                System.out.println("getUser: " + currentUser);
                action = "viewPayment";
                System.out.println("action in getUser: " + action);
            }

            if (action.equals("testBuy")) {
                currentUser = request.getParameter("username");
                String receiver = request.getParameter("receiver");
                String eventName = request.getParameter("event");
                String ticketQuantity = request.getParameter("quantity");
                String amount = request.getParameter("price");
                String promotion = request.getParameter("promotion");
                paymentManager.createRecord(currentUser, receiver, eventName, ticketQuantity, amount, promotion);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/testBuy.jsp").forward(request, response);
            } else if (action.equals("viewPayment")) {

                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                if (paymentNo != null) {
                    System.out.println("Entered view payment no not null");
                    paymentManager.updateTicketTakings(paymentNo);
                    
                    //currentUser = name;
                    System.out.println("username: " + currentUser);
                    paymentManager.updatePaymentStatus(paymentNo);
                    request.setAttribute("payment", "true");
                    request.setAttribute("invoice", "true");
                    request.setAttribute("username", name);
                    ArrayList<ArrayList<String>> records = paymentManager.getRecords(name);
                    int noOfRecords = records.size();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                    ArrayList<ArrayList<String>> recordPage = paymentManager.recordPage(records, (page - 1) * recordsPerPage, recordsPerPage);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("recordSize", String.valueOf(recordPage.size()));
                    request.setAttribute("currentPage", page);
                    request.setAttribute("inbox", recordPage);
                    System.out.println("Name: " + name);
                    ArrayList<String> paymentRecord = paymentManager.getRecordDetails(paymentNo);
                    String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
                    String amount = String.valueOf(Double.parseDouble(paymentRecord.get(5)) * Double.parseDouble(paymentRecord.get(6)));
                    paymentManager.sendEmail(name, "is3102mtix@gmail.com", "Invoice From MTiX\nInvoice date: " + timeStamp + "\nInvoiced to: " + name + "\nAddress: " + paymentRecord.get(0) + "\nCountry: " + paymentRecord.get(1) + "\nZip Code: " + paymentRecord.get(2) + " " + paymentRecord.get(3) + "\nEvent: " + paymentRecord.get(4) + "\nTicket Qunatity: " + paymentRecord.get(5) + "\nTotal Amount($): " + amount + "\nTransaction Date: " + timeStamp + "\n\nNote: This is a Computer generated invoice and thus requires no signature.", "MTiX Invoice", "smtp.gmail.com");
                    request.getRequestDispatcher("/paymentRecords.jsp").forward(request, response);

                } else {
                    /*if (request.getParameter("page") == null) {
                     currentUser = request.getParameter("username");
                     }*/
                    System.out.println("Entered view payment no null");
                    System.out.println("username: " + currentUser);
                    request.setAttribute("username", currentUser);

                    ArrayList<ArrayList<String>> records = paymentManager.getRecords(currentUser);
                    System.out.println("went through payment manager");
                    int noOfRecords = records.size();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                    ArrayList<ArrayList<String>> recordPage = paymentManager.recordPage(records, (page - 1) * recordsPerPage, recordsPerPage);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("recordSize", String.valueOf(recordPage.size()));
                    request.setAttribute("currentPage", page);
                    request.setAttribute("inbox", recordPage);
                    request.getRequestDispatcher("/paymentRecords.jsp").forward(request, response);
                }

            } else if (action.equals("makePayment")) {
                System.out.println("payment Id: " + request.getParameter("paymentid"));
                String eventName = paymentManager.getEventName(request.getParameter("paymentid"));
                System.out.println("Event Name: " + eventName);
                request.setAttribute("username", currentUser);
                request.setAttribute("event", eventName);

                request.setAttribute("quantity", request.getParameter("ticket"));
                request.setAttribute("price", paymentManager.convertPrices(request.getParameter("ticket"), request.getParameter("amount")));

                request.setAttribute("paymentid", request.getParameter("paymentid"));
                request.getRequestDispatcher("/makePayment.jsp").forward(request, response);
            } else if (action.equals("addAddress")) {
                String address = request.getParameter("address");
                String country = request.getParameter("country");
                String city = request.getParameter("city");
                String zip = request.getParameter("zip");
                String id = request.getParameter("paymentid");
                request.setAttribute("username", currentUser);
                request.setAttribute("event", request.getParameter("event"));
                request.setAttribute("quantity", request.getParameter("ticket"));
                request.setAttribute("price", request.getParameter("price"));
                request.setAttribute("paymentid", request.getParameter("paymentid"));
                request.setAttribute("address", address);
                request.setAttribute("country", country);
                request.setAttribute("city", city);
                request.setAttribute("zip", zip);
                paymentManager.addAddress(id, address, country, city, zip);
                request.getRequestDispatcher("/paymentConfirmation.jsp").forward(request, response);
            } else if (action.equals("requestRefund")) {
                String id = request.getParameter("paymentid");
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
              
                String eventDate = paymentSession.retrieveEventDate(id);
                System.out.println("event date : "+ eventDate);
                if (paymentManager.checkRefundValidity(timeStamp, eventDate)) {
                    System.out.println("Allow refund!");
                    paymentManager.getRefund(id);
                    if (request.getParameter("page") != null) {
                        page = Integer.parseInt(request.getParameter("page"));
                    }
                    ArrayList<ArrayList<String>> records = paymentManager.getRecords(currentUser);
                    int noOfRecords = records.size();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                    ArrayList<ArrayList<String>> recordPage = paymentManager.recordPage(records, (page - 1) * recordsPerPage, recordsPerPage);
                    request.setAttribute("username", currentUser);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("recordSize", String.valueOf(recordPage.size()));
                    request.setAttribute("currentPage", page);
                    request.setAttribute("inbox", recordPage);
                    request.getRequestDispatcher("/paymentRecords.jsp").forward(request, response);
                } else {

                    System.out.println("7 days or less disallow refund!");
                    if (request.getParameter("page") != null) {
                        page = Integer.parseInt(request.getParameter("page"));
                    }
                    ArrayList<ArrayList<String>> records = paymentManager.getRecords(currentUser);
                    int noOfRecords = records.size();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                    ArrayList<ArrayList<String>> recordPage = paymentManager.recordPage(records, (page - 1) * recordsPerPage, recordsPerPage);
                    request.setAttribute("username", currentUser);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("cannot", "true");
                    request.setAttribute("recordSize", String.valueOf(recordPage.size()));
                    request.setAttribute("currentPage", page);
                    request.setAttribute("inbox", recordPage);
                    request.getRequestDispatcher("/paymentRecords.jsp").forward(request, response);
                }

                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
