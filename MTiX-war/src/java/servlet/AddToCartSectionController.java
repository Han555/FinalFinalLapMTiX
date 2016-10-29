/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.SessionManager;
import session.stateless.ticketing.BookingSessionLocal;

/**
 *
 * @author catherinexiong
 */
@WebServlet(name = "AddToCartSectionController", urlPatterns = {"/AddToCartSectionController"})
public class AddToCartSectionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private BookingSessionLocal bookSession;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         SessionManager sm = new SessionManager(bookSession);
        String numOfTickets = request.getParameter("numT");
        String promotionIdStr = request.getParameter("pid");
        String sessionIdStr = request.getParameter("sid");
        String username = request.getParameter("username").toLowerCase();
        String price = request.getParameter("price");
        String section = request.getParameter("section");
        System.out.println("====AddToCartSectionController " + numOfTickets + "  " + promotionIdStr + "  " + sessionIdStr + username + price + section);
        Boolean result = sm.addToCartByUsernameFreeSection(username, Long.valueOf(sessionIdStr), Long.valueOf(promotionIdStr), numOfTickets, price,section);
        String msg;
        if (result) {
            msg = "success";

        } else {
            msg = "error";
        }
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(msg));
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
