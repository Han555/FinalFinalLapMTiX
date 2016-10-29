/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import session.stateless.propertymanagement.ReservePropertyBeanLocal;
import com.google.gson.Gson;
import entity.EquipmentEntity;
import entity.Event;
import entity.SectionEntity;
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
import manager.ProductManager;
import manager.SessionManager;
import model.ReservedClosedModel;
import session.stateless.commoninfrastucture.ProductSessionLocal;
import session.stateless.ticketing.BookingSessionLocal;

/**
 *
 * @author catherinexiong
 */
@WebServlet(name = "ReservedClosedSectionsController", urlPatterns = {"/sectionListSpecial"})
public class ReservedClosedSectionsController extends HttpServlet {
@EJB 
   private ProductSessionLocal psbl;
@EJB 
   private BookingSessionLocal bsl;
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
        
        ProductManager pm = new ProductManager(psbl);
        SessionManager bm = new SessionManager (bsl);
        String idStr = request.getParameter("id");
        Long sessionId = Long.valueOf(idStr);
        
        String type = request.getParameter("type");
        
        List<SectionEntity> resultList = new ArrayList();
      
        List<ReservedClosedModel> specialList = new ArrayList<ReservedClosedModel>();
        if (type.equals("reserved")) {
            resultList = bm.getReservedSectionsBySessionId(sessionId);
        } else {
            resultList = bm.getClosedSectionsBySessionId(sessionId);
        }
        
        for (SectionEntity s: resultList) {
            ReservedClosedModel special = new ReservedClosedModel();
            special.setId(s.getId());
            special.setCapacity(s.getCapacity());
            special.setNumberInProperty(s.getNumberInProperty());
            specialList.add(special);
        }
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(specialList));
        
        
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
