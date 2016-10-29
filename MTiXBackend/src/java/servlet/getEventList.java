/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
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
import model.EventModel;
import session.stateless.commoninfrastucture.ProductSessionLocal;

/**
 *
 * @author JingYing
 */
@WebServlet(name = "getEventList", urlPatterns = {"/getEventList"})
public class getEventList extends HttpServlet {

    @EJB
    private ProductSessionLocal productSession;

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
        List<ArrayList> data = productSession.getEventList();
        String tableData = "[";
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                tableData += "[" + data.get(i).get(1) + ", " + data.get(i).get(4) + ", " + data.get(i).get(2)
                        + ", " + data.get(i).get(3) + "]]";
            } else {
                tableData += "[" + data.get(i).get(1) + ", " + data.get(i).get(4) + ", " + data.get(i).get(2)
                        + ", " + data.get(i).get(3) + "], ";
            }

        }
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(tableData));

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
