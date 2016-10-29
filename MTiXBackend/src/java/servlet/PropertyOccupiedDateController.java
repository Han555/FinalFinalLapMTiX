/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import session.stateless.propertymanagement.ReservePropertyBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.stateless.propertymanagement.SeatingPlanManagementBeanLocal;
import manager.SeatingPlanManager;
import com.google.gson.*;
import entity.Event;
import entity.SubEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.ReservationManager;
import model.DateRange;

/**
 *
 * @author Student-ID
 */
@WebServlet(name="PropertyOccupiedDateController", urlPatterns={"/propertyOccupiedDates"})
public class PropertyOccupiedDateController extends HttpServlet {
    
    @EJB
    SeatingPlanManagementBeanLocal seatingPlanManagementBeanLocal;
    
    @EJB
    ReservePropertyBeanLocal reservePropertyBeanLocal;
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
            throws ServletException, IOException, ParseException {
       
        String idStr = request.getParameter("id");
        //int propertyId = Integer.parseInt(idStr);
        Long propertyId = Long.valueOf(idStr);
        ReservationManager rm = new ReservationManager(reservePropertyBeanLocal);
        List<Event> events = rm.getEventReservationByProperty(propertyId);
        List<SubEvent> subevents =rm.getSubEventReservationByProperty(propertyId);
        List<DateRange> drs = new ArrayList<DateRange>();
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        for(Event e:events){
            DateRange dr = new DateRange();
            dr.setStartDate(format.format(e.getStart()));
            dr.setEndDate(format.format(e.getEnd()));
            drs.add(dr);
        }
        
        for(SubEvent se:subevents){
            DateRange dr = new DateRange();
            dr.setStartDate(format.format(se.getStart()));
            dr.setEndDate(format.format(se.getEnd()));
            drs.add(dr);
        }
       
     /*   
        dr.setStartDate(format.format(df.parse("21/09/2016")));
        dr.setEndDate(format.format(df.parse("25/09/2016")));
        drs.add(dr);
        
        DateRange dr2 = new DateRange();
        dr2.setStartDate(format.format(df.parse("04/10/2016")));
        dr2.setEndDate(format.format(df.parse("04/10/2016")));
        drs.add(dr2);
        
        DateRange dr3 = new DateRange();
        dr3.setStartDate(format.format(df.parse("11/10/2016")));
        dr3.setEndDate(format.format(df.parse("15/10/2016")));
        drs.add(dr3);
      */  
        Gson gson = new Gson();
        
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(drs));

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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PropertyOccupiedDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PropertyOccupiedDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

