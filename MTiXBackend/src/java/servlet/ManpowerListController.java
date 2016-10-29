/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import session.stateless.propertymanagement.EquipmentBeanLocal;
import session.stateless.propertymanagement.ManpowerBeanLocal;
import com.google.gson.Gson;
import entity.EquipmentEntity;
import entity.ManpowerEntity;
import java.io.IOException;
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
import model.EquipmentModel;
import model.ManpowerModel;

/**
 *
 * @author hyc528
 */
@WebServlet(name = "ManpowerListController", urlPatterns = {"/manpowerList"})
public class ManpowerListController extends HttpServlet {
    
    @EJB
    private ManpowerBeanLocal mb;

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
        
        List<ManpowerEntity> mp = mb.getManpowerInProperty(id);
        List<ManpowerModel> mList = new ArrayList<ManpowerModel>();
                
        for (int i =0; i< mp.size();i++) {
            ManpowerEntity m = mp.get(i);
             System.out.println("ID: " + m.getId());
            ManpowerModel mpm = new ManpowerModel();
            mpm.setId(m.getId());
            mpm.setPrice(m.getPrice());
            mpm.setStaffRole(m.getStaffRole());
            mpm.setNumber(Integer.toString(m.getNumber()));
            mpm.setStandard(m.getStandard());
            mList.add(mpm);
        }
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(mList));
        
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
