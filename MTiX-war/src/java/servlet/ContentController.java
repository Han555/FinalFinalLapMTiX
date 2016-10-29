/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.stateless.contentmanagement.WebsiteManagementBeanLocal;

/**
 *
 * @author JingYing
 */
@WebServlet(name = "ContentController", urlPatterns = {"/ContentController", "/ContentController/*"})
public class ContentController extends HttpServlet {

    @EJB
    private WebsiteManagementBeanLocal webManagementBean;

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
        
        
        System.err.println("******** ContentController: " + request.getServletPath());
        System.err.println("******** ContentController: " + request.getContextPath());
        System.err.println("******** ContentController: " + request.getPathInfo());
        System.err.println("******** ContentController: " + request.getRequestURI());
        
        
        String action = request.getParameter("action");

        if (action.equals("doLogin")) {
            List<ArrayList> data = webManagementBean.getWebpageList();
            request.setAttribute("data", data);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else if (action.equals("viewEventWebpage")) {
            String id = request.getParameter("id");
            ArrayList data = webManagementBean.getEventWebpageInfo(id);
            List<ArrayList> sessions = webManagementBean.getEventSessionInfo(id);
            List<ArrayList> promotions = webManagementBean.getEventPromotionInfo(id);

            request.setAttribute("data", data);
            request.setAttribute("promotions", promotions);
            request.setAttribute("sessions", sessions);
            request.getRequestDispatcher("/viewEventWebpage.jsp").forward(request, response);
        } else if (action.equals("creditCardPromotion")) {
            List<ArrayList> data = webManagementBean.getCreditCardEvents();
            request.setAttribute("data", data);
            request.getRequestDispatcher("/creditCardPromotion.jsp").forward(request, response);
        } else if (action.equals("volumeDiscountPromotion")) {
            List<ArrayList> data = webManagementBean.getVolumeDiscountEvents();
            request.setAttribute("data", data);
            request.getRequestDispatcher("/volumeDiscountPromotion.jsp").forward(request, response);
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
