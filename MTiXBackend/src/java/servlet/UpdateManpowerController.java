/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import session.stateless.propertymanagement.ManpowerBeanLocal;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.ManpowerManager;

/**
 *
 * @author hyc528
 */
@WebServlet(name = "UpdateManpowerController", urlPatterns = {"/UpdateManpower"})
public class UpdateManpowerController extends HttpServlet {

    @EJB
    private ManpowerBeanLocal mbl;

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

        ManpowerManager mm = new ManpowerManager(mbl);
        String midStr = request.getParameter("mid");
        Long mid = Long.valueOf(midStr);
        String propertyIdStr = request.getParameter("propertyId");
        String mrole = request.getParameter("mrole");
        String number = request.getParameter("mnumber");
        Integer mnumber = Integer.parseInt(number);
        System.out.println("=====================" + mrole + mnumber);
        String msg = new String();

        if (mm.editManpower(mid, mrole, mnumber)) {
            msg = "success";
        } else {
            msg = "conflict";
        }

        Gson gson = new Gson();
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