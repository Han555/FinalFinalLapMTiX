/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import session.stateless.propertymanagement.ReservePropertyBeanLocal;
import com.google.gson.Gson;
import entity.SubEvent;
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
import model.SubEventModel;

/**
 *
 * @author catherinexiong
 */
@WebServlet(name = "SubEventList", urlPatterns = {"/subEventList"})
public class SubEventList extends HttpServlet {

    @EJB 
   private ReservePropertyBeanLocal rpbl;
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
        String idStr = request.getParameter("id");
        Long id = Long.valueOf(idStr);
        System.out.println("Controller ==="+id);
        
        List<SubEvent> eList = rpbl.getSubEventReservationByProperty(id);
        List<SubEventModel> emList = new ArrayList<SubEventModel>();
        
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        
        for (int i =0; i< eList.size();i++) {
            SubEvent e = eList.get(i);
             System.out.println("ID: " + e.getId());
            SubEventModel eqm = new SubEventModel();
            eqm.setId(e.getId());
            eqm.setStartDate(format.format(e.getStart()));
            eqm.setEndDate(format.format(e.getEnd()));
            eqm.setName(e.getName());
            eqm.setEname(e.getEvent().getName());
            //eqm.setDes(e.getDescription());
            eqm.setEmail(e.getUser().getUsername());
            emList.add(eqm);
        }
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(emList));
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
