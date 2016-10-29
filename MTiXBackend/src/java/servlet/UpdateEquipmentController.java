/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import session.stateless.propertymanagement.EquipmentBeanLocal;
import session.stateless.propertymanagement.MaintenanceBeanLocal;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.EquipmentManager;
import manager.MaintenanceScheduleManager;

/**
 *
 * @author catherinexiong
 */
@WebServlet(name = "UpdateEquipmentController", urlPatterns = {"/UpdateEquipment"})
public class UpdateEquipmentController extends HttpServlet {

    @EJB
    private EquipmentBeanLocal ebl;

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

        EquipmentManager em = new EquipmentManager(ebl);
        String eidStr = request.getParameter("eid");
        Long eid = Long.valueOf(eidStr);
        String propertyIdStr = request.getParameter("propertyId");
        String ename = request.getParameter("ename");
        String elocation = request.getParameter("elocation");
        System.out.println("=====================" + ename + elocation);
        String msg = new String();

        if (em.editEquipment(eid, ename, elocation)) {
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
