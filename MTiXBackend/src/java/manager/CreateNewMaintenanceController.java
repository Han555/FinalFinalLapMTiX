/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import servlet.*;
import session.stateless.propertymanagement.MaintenanceBeanLocal;
import session.stateless.propertymanagement.ReservePropertyBeanLocal;
import session.stateless.propertymanagement.SeatingPlanManagementBeanLocal;
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
import manager.MaintenanceScheduleManager;
import manager.ReservationManager;
import manager.SeatingPlanManager;

/**
 *
 * @author catherinexiong
 */
@WebServlet(name = "CreateNewMaintenanceController", urlPatterns = {"/CreateNewMaintenance"})
public class CreateNewMaintenanceController extends HttpServlet {

    @EJB
    SeatingPlanManagementBeanLocal seatingPlanManagementBeanLocal;

    @EJB
    ReservePropertyBeanLocal reservePropertyBeanLocal;
    
    @EJB
    MaintenanceBeanLocal maintenanceBeanLocal;

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
            throws ServletException, IOException{

        try {
            SeatingPlanManager spm = new SeatingPlanManager(seatingPlanManagementBeanLocal);
            ReservationManager rm = new ReservationManager(reservePropertyBeanLocal);
            MaintenanceScheduleManager msm = new MaintenanceScheduleManager(maintenanceBeanLocal);
            String idStr = request.getParameter("propertyList");
            String mDaterange = request.getParameter("mdaterange");
            String msg = new String();
            if (msm.addMaintenance(idStr,mDaterange)){
                msg = "success";
            } else msg = "conflict";
                  
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(msg));
       
        } catch (ParseException ex) {
            Logger.getLogger(CreateNewMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
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
