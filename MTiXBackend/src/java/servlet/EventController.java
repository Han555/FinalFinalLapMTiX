/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "EventController", urlPatterns = {"/EventController", "/EventController?*"})
public class EventController extends HttpServlet {

    @EJB
    private PaymentSessionLocal paymentSession;

    String currentUser;

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
            PaymentManager paymentManager = new PaymentManager(paymentSession);
            String action;

            int page = 1;
            int recordsPerPage = 8;

            action = request.getParameter("action");
            System.out.println("here special");

            System.out.println("Action = " + action);

            if (action.equals("viewEvents")) {
                request.setAttribute("username", currentUser);

                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                if (request.getParameter("page") == null) {
                    currentUser = request.getParameter("username");
                }
                request.setAttribute("username", currentUser);
                ArrayList<String> events = paymentManager.getEvents(currentUser);
                int noOfRecords = events.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<String> eventPage = paymentManager.eventPage(events, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(eventPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("inbox", eventPage);
                request.getRequestDispatcher("/viewEvents.jsp").forward(request, response);
            } else if (action.equals("eventDetails")) {
                System.out.println("Entered event details.");
                String event = request.getParameter("event");
                System.out.println("event name details: "+ event);
                System.out.println("organizer name: "+currentUser);
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }                                                           
                ArrayList<ArrayList<String>> eventRecords = paymentManager.getEventRecords(event, currentUser);
                int noOfRecords = eventRecords.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> recordPage = paymentManager.getEventRecordsPage(eventRecords, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(recordPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("username", currentUser);
                request.setAttribute("inbox", recordPage);
                request.getRequestDispatcher("/eventRecords.jsp").forward(request, response);
            } else if (action.equals("analyze")) {
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }                                                           
                ArrayList<ArrayList<String>> eventRecords = paymentManager.analyzeUsers();
                int noOfRecords = eventRecords.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> recordPage = paymentManager.getUserRecordsPage(eventRecords, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(recordPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("username", currentUser);
                request.setAttribute("inbox", recordPage);
                request.getRequestDispatcher("/analyticalTable.jsp").forward(request, response);
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
