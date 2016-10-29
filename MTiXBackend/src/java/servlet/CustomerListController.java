/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import entity.MaintenanceScheduleEntity;
import entity.UserEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CustomerModel;
import model.Maintenance;
import session.stateless.customerelationshipmanagement.CRMSessionLocal;

/**
 *
 * @author catherinexiong
 */
@WebServlet(name = "CustomerListController", urlPatterns = {"/CustomerListController"})
public class CustomerListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    
    @EJB
    CRMSessionLocal crmsl;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
  
        List<UserEntity> ms = crmsl.getAllCustomers();
        List<CustomerModel> mlist = new ArrayList<CustomerModel>();
        
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < ms.size(); i++) {
            UserEntity u = ms.get(i);
            System.out.println(u.getUserId());
            CustomerModel cm = new CustomerModel();
            cm.setUsername(u.getUsername());
            cm.setFirstName(u.getFirstName());
            cm.setLastName(u.getLastName());
            cm.setAge(u.getAge());
            cm.setMobileNumber(u.getMobileNumber());
            cm.setLoyaltyCardId(u.getLoyaltyCardId());
            cm.setLoyaltyPoints(u.getLoyaltyPoints());
            cm.setJoinDate(format1.format(u.getJoinDate()));
            mlist.add(cm);
        }
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(mlist));
        
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
