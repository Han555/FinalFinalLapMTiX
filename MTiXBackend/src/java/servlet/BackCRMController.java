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
import manager.BookingFeesManager;
import manager.CRMManager;
import manager.LicensePaymentManager;
import session.stateless.BookingFeesSessionLocal;
import session.stateless.LicensePaymentSessionLocal;
import session.stateless.customerelationshipmanagement.CRMSessionLocal;

/**
 *
 * @author Student-ID
 */
@WebServlet(name = "BackCRMController", urlPatterns = {"/BackCRMController", "/BackCRMController?*"})
public class BackCRMController extends HttpServlet {

    @EJB
    private LicensePaymentSessionLocal licensePaymentSession;
    @EJB
    private BookingFeesSessionLocal bookingFeesSession;
    @EJB
    private CRMSessionLocal crmSession;
    String currentUser;
    String role;

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
            BookingFeesManager bookingManager = new BookingFeesManager(bookingFeesSession);
            LicensePaymentManager licensePaymentManager = new LicensePaymentManager(licensePaymentSession);
            CRMManager crmm = new CRMManager(crmSession);
            

            action = request.getParameter("action");

            System.out.println("Action = " + action);
            
            if(action.equals("crmFirst")) {
                currentUser = request.getParameter("username");
                role = request.getParameter("role");
                action = "crmMain";
                System.out.println("Entered CRM first");
            }

            if (action.equals("crmMain")) {
                currentUser = request.getParameter("username");
                role = request.getParameter("role");
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                System.out.println("Current User: " + currentUser);
                System.out.println("Role: " + role);
                request.getRequestDispatcher("/crmMain_2.jsp").forward(request, response);
            } else if (action.equals("oCRMMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/oCRMMain.jsp").forward(request, response);
            } else if (action.equals("aCRMMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/aCRMMain.jsp").forward(request, response);
            } else if (action.equals("oCRMLinkCard")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                
                request.getRequestDispatcher("/oCRMLinkCard.jsp").forward(request, response);
            } else if (action.equals("oCRMEditCustomer")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
//                request.setAttribute("customer", crmm.getAllCustomers());
                request.getRequestDispatcher("/oCRMEditCustomer.jsp").forward(request, response);
            } else if (action.equals("oCRMcardLinked")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                //String price = request.getParameter("eprice");
                String cemail = request.getParameter("cemail");
                String cnumber = request.getParameter("cnumber");
                System.out.println("cemail:"+cemail);
                System.out.println("cnumber:"+cnumber);
                if(crmm.LinkCardToCustomer(cemail, cnumber)==null){
                    request.setAttribute("error","true");
                    request.getRequestDispatcher("/oCRMLinkCard.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/oCRMCardLinked.jsp").forward(request, response);
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
