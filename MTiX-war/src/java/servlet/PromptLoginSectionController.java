/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.LoginManager;
import manager.SessionManager;
import session.stateless.commoninfrastucture.LoginSessionLocal;
import session.stateless.ticketing.BookingSessionLocal;

/**
 *
 * @author catherinexiong
 */
@WebServlet(name = "PromptLoginSectionController", urlPatterns = {"/PromptLoginSectionController"})
public class PromptLoginSectionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @EJB
    private LoginSessionLocal loginSession;
    @EJB
    private BookingSessionLocal bookSession;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       LoginManager loginManager = new LoginManager(loginSession);
        SessionManager sm = new SessionManager(bookSession);
        String numOfTickets = request.getParameter("numTicket-pop");
        String promotionIdStr = request.getParameter("promotion-pop");
        String username = request.getParameter("userName").toLowerCase();
        String password = request.getParameter("password");
        String price = request.getParameter("pricePop");
        String sessionIdStr = request.getParameter("sessionPop");
        String sectionIdStr = request.getParameter("sectionPop");
        System.out.println("====promptLoginCheckController" + numOfTickets + promotionIdStr + username + password +"===price: " +price +"===sessionId: " + sessionIdStr+"===sectionId: " + sectionIdStr);
        String msg = new String();

        if (loginManager.checkUser(username)) {
            if (loginManager.identify(username, password)) {
                msg = "success";
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("username", username);
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                //response.sendRedirect("/home.jsp");
                Boolean result =sm.addToCartByUsernameFreeSection(username, Long.valueOf(sessionIdStr), Long.valueOf(promotionIdStr), numOfTickets, price,sectionIdStr);
                if(result){
                    msg = "successadd";
                } else {
                    msg = "failtoadd";
                }
            } else {
                msg = "mismatch";

            }

        } else {
            msg = "nouser";

        }
        System.out.println("====checkUser result" + msg);

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
