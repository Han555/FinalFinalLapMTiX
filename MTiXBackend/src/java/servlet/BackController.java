
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import session.stateless.propertymanagement.EquipmentBeanLocal;
import session.stateless.propertymanagement.FoodOutletBeanLocal;
import session.stateless.propertymanagement.ManpowerBeanLocal;
import session.stateless.propertymanagement.ReservePropertyBeanLocal;
import session.stateless.propertymanagement.SeatingPlanManagementBeanLocal;
import entity.EquipmentEntity;
import entity.Event;
import entity.FoodOutletEntity;
import entity.ManpowerEntity;
import entity.PropertyEntity;
import entity.SubEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.BulletinManager;
import manager.EquipmentManager;
import manager.FoodOutletManager;
import manager.LockManager;
import manager.LogManager;
import manager.LoginManager;
import manager.ManpowerManager;
import manager.MessageManager;
import manager.ProductManager;
import manager.RegisterManager;
import manager.ReservationManager;
import manager.ResetPasswordManager;
import manager.SeatingPlanManager;
import manager.UnlockManager;
import session.stateless.BulletinSessionLocal;
import session.stateless.commoninfrastucture.GetAllProductDetailsLocal;
import session.stateless.commoninfrastucture.LockAccountSessionLocal;
import session.stateless.commoninfrastucture.LoginSessionLocal;
import session.stateless.MessageSessionLocal;
import session.stateless.commoninfrastucture.ProductSessionLocal;
import session.stateless.commoninfrastucture.RegisterSessionLocal;
import session.stateless.commoninfrastucture.ResetPasswordSessionLocal;
import session.stateless.commoninfrastucture.UnlockAccountSessionLocal;

/**
 *
 * @author Student-ID
 */
@WebServlet(name = "BackController", urlPatterns = {"/BackController", "/BackController?*"})
public class BackController extends HttpServlet {

    @EJB
    SeatingPlanManagementBeanLocal seatingPlanManagementBeanLocal;

    @EJB
    private ReservePropertyBeanLocal reservePropertyBeanLocal;
    @EJB
    private ProductSessionLocal productSession;

    @EJB
    private BulletinSessionLocal bulletinSession;
    @EJB
    private MessageSessionLocal messageSession;

    @EJB
    private RegisterSessionLocal registerSession;
    @EJB
    private UnlockAccountSessionLocal unlockAccountSession;
    @EJB
    private ResetPasswordSessionLocal resetPasswordSession;
    @EJB
    private LockAccountSessionLocal lockAccountSession;
    @EJB
    private LoginSessionLocal loginSession;

    @EJB
    private EquipmentBeanLocal equipmentBeanLocal;
    @EJB
    private ManpowerBeanLocal manpowerBeanLocal;
    @EJB
    private FoodOutletBeanLocal foodoutletBeanLocal;

    @EJB
    private GetAllProductDetailsLocal getAllProductDetailsLocal;

    public String currentUser;
    public String subject = "";
    public String role = "";

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */
            LoginManager loginManager = new LoginManager(loginSession);
            LockManager lockManager = new LockManager(lockAccountSession);
            ResetPasswordManager resetManager = new ResetPasswordManager(resetPasswordSession);
            LogManager logManager = new LogManager();
            UnlockManager unlockManager = new UnlockManager(unlockAccountSession);
            RegisterManager registerManager = new RegisterManager(registerSession);
            MessageManager messageManager = new MessageManager(messageSession);
            BulletinManager bulletinManager = new BulletinManager(bulletinSession);
            ProductManager productManager = new ProductManager(productSession);
            SeatingPlanManager spm = new SeatingPlanManager(seatingPlanManagementBeanLocal);
            ReservationManager rm = new ReservationManager(reservePropertyBeanLocal);
            EquipmentManager em = new EquipmentManager(equipmentBeanLocal);
            ManpowerManager mm = new ManpowerManager(manpowerBeanLocal);
            FoodOutletManager fom = new FoodOutletManager(foodoutletBeanLocal);

            int page = 1;
            int recordsPerPage = 8;
            String action;
            action = request.getParameter("action");
            System.out.println("Action = " + action);

            String name = request.getParameter("name");
            System.out.println("here special");
            if (name != null) {
                System.out.println("here special 2");
                if (action == null) {
                    System.out.println("here special 3");
                    action = "verify2";
                }
            }

            if (action.equals("resetPassword")) {
                System.out.println("reset here 2");
                request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
            } else if (action.equals("doLogin")) {
                System.out.println(request.getParameter("userName") + "      " + request.getParameter("password"));
                String username = request.getParameter("userName");
                String password = request.getParameter("password");

                if (loginManager.checkVerification(username)) {
                    System.out.println("here 1");
                    request.setAttribute("verification", "true");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    if (loginManager.identify(username, password)) {
                        System.out.println("here 2");
                        if (resetManager.resetted(username)) {
                            System.out.println("reset here 1");
                            request.setAttribute("username", username);
                            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                        }
                        if (lockManager.passThrough(username)) {
                            role = loginManager.getRoles(username);
                            System.out.println("role: " + role);
                            if (loginManager.getRoles(username).equals("super administrator") || loginManager.getRoles(username).equals("property manager") || loginManager.getRoles(username).equals("product manager") || loginManager.getRoles(username).equals("event organizer") || loginManager.getRoles(username).equals("content manager") ||  loginManager.getRoles(username).equals("finance manager") || loginManager.getRoles(username).equals("crm manager")) {
                                System.out.println("here new 1");
                                logManager.logMessage(username + " logged in.");
                                currentUser = username;
                                request.setAttribute("username", username);
                                request.setAttribute("role", role);
                                request.getRequestDispatcher("/home.jsp").forward(request, response);
                            } else {
                                request.setAttribute("role", "true");
                                request.getRequestDispatcher("/login.jsp").forward(request, response);
                            }
                        } else if (lockManager.finalLock(username)) {
                            System.out.println("here 3");
                            request.setAttribute("locked", "true");
                            request.setAttribute("account", username);
                            request.getRequestDispatcher("/login.jsp").forward(request, response);
                        } else {
                            System.out.println("here 4");
                            role = loginManager.getRoles(username);
                            System.out.println("role: " + role);
                            if (loginManager.getRoles(username).equals("super administrator") || loginManager.getRoles(username).equals("property manager") || loginManager.getRoles(username).equals("product manager") || loginManager.getRoles(username).equals("event organizer") || loginManager.getRoles(username).equals("content manager") || loginManager.getRoles(username).equals("crm manager") || loginManager.getRoles(username).equals("finance manager")) {
                                System.out.println("here new 1");
                                logManager.logMessage(username + " logged in.");
                                currentUser = username;
                                request.setAttribute("username", username);
                                request.setAttribute("role", role);
                                request.getRequestDispatcher("/home.jsp").forward(request, response);
                            } else {
                                request.setAttribute("role", "true");
                                request.getRequestDispatcher("/login.jsp").forward(request, response);
                            }
                            //logManager.logMessage(username + " logged in.");

                        }
                    } else if (lockManager.checkLock(username, password)) {
                        System.out.println("here 5");
                        if (lockManager.lockAccount(username)) {
                            System.out.println("here 6");
                            if (lockManager.finalLock(username)) {
                                System.out.println("here 7");
                                request.setAttribute("locked", "true");
                                request.setAttribute("account", username);
                                request.getRequestDispatcher("/login.jsp").forward(request, response);
                            } else {
                                System.out.println("here 8");
                                request.setAttribute("halflock", "true");
                                request.getRequestDispatcher("/login.jsp").forward(request, response);
                            }
                        } else {
                            System.out.println("here 9");
                            lockManager.insertLock(username);
                            request.setAttribute("halflock", "true");
                            request.getRequestDispatcher("/login.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("here 10");
                        request.setAttribute("wronguser", "true");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }
            } else if (action.equals("verify")) {
                System.out.println("userName in verify: " + request.getParameter("userName"));
                String verifyUser = request.getParameter("userName");

                if (registerManager.checkOldPassword(request.getParameter("userName"), request.getParameter("oldPass"))) {
                    if (request.getParameter("newPass").equals(request.getParameter("newPass2"))) {
                        registerManager.verify(request.getParameter("userName"));
                        registerManager.changePassword(request.getParameter("userName"), request.getParameter("newPass"));
                        request.setAttribute("accountverified", "true");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    } else {
                        request.setAttribute("matchpass", "true");
                        request.setAttribute("verifyUser", verifyUser);
                        request.getRequestDispatcher("/verification.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("oldpass", "true");
                    request.setAttribute("verifyUser", verifyUser);
                    request.getRequestDispatcher("/verification.jsp").forward(request, response);
                }
            } else if (action.equals("verify2")) {
                request.getRequestDispatcher("/verification.jsp").forward(request, response);
            } else if (action.equals("unlock")) {
                System.out.println("here special 4");
                request.setAttribute("username", name);
                request.getRequestDispatcher("/unlockAccount.jsp").forward(request, response);
            } else if (action.equals("unlocking")) {
                String username = request.getParameter("userName");
                System.out.println("unlocking " + username);
                unlockManager.unlock(username);
                request.setAttribute("unlock", "true");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else if (action.equals("createAccount")) {
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/createAccount.jsp").forward(request, response);
            } else if (action.equals("creating")) {
                System.out.println("creating: " + request.getParameter("username") + "    " + request.getParameter("role") + "    " + request.getParameter("mobileNumber"));
                if (registerManager.checkConflict(request.getParameter("username"))) {
                    request.setAttribute("username", currentUser);
                    request.setAttribute("conflict", "true");
                    request.getRequestDispatcher("/createAccount.jsp").forward(request, response);
                } else {
                    registerManager.adminCreate(request.getParameter("username"), request.getParameter("role"), request.getParameter("mobileNumber"));
                    request.setAttribute("username", currentUser);
                    request.setAttribute("created", "true");
                    request.getRequestDispatcher("/createAccount.jsp").forward(request, response);
                }
            } else if (action.equals("home")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/home.jsp").forward(request, response);
            } else if (action.equals("sendResetPassword")) {
                System.out.println("reset here 3");
                String username = request.getParameter("userName");
                if (resetManager.checkUserAccount(username)) {
                    System.out.println("reset here 4");
                    if (resetManager.checkLockPresence(username)) {
                        System.out.println("reset here 5");
                        request.setAttribute("lockreset", "true");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    } else {
                        System.out.println("reset here 6");
                        resetManager.reset(username);
                        request.setAttribute("reset", "true");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                } else {
                    System.out.println("reset here 7");
                    request.setAttribute("absent", "true");
                    request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
                }
            } else if (action.equals("change")) {
                System.out.println("reset here 8");
                if (!(request.getParameter("newPass").equals(request.getParameter("newPass2")))) {
                    System.out.println("reset here 9");
                    request.setAttribute("unmatch", "true");
                    request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                } else {
                    System.out.println("reset here 10");
                    resetManager.resetPassword(request.getParameter("userName"), request.getParameter("newPass"));
                    request.setAttribute("change", "true");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else if (action.equals("message")) {
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<ArrayList<String>> inbox = messageManager.getInbox(currentUser);
                int noOfRecords = inbox.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> inboxPage = messageManager.inboxPage(inbox, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(inboxPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("username", currentUser);
                request.setAttribute("inbox", inboxPage);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
            } else if (action.equals("compose")) {
                System.out.println("username: " + currentUser);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/compose.jsp").forward(request, response);
            } else if (action.equals("createMessage")) {
                System.out.println("Entered message");
                System.out.println("From: " + currentUser);

                System.out.println("To: " + request.getParameter("to"));
                if (messageManager.sendMessage(currentUser, request.getParameter("to"), request.getParameter("subject"), request.getParameter("message"))) {
                    System.out.println("Entered message 2");
                    request.setAttribute("sent", "true");
                    request.setAttribute("role", role);
                    request.setAttribute("username", currentUser);
                    request.getRequestDispatcher("/compose.jsp").forward(request, response);
                } else {
                    System.out.println("Entered message 3");
                    request.setAttribute("missend", "true");
                    request.getRequestDispatcher("/compose.jsp").forward(request, response);
                }
            } else if (action.equals("readMessage")) {
                System.out.println("Message ID: " + request.getParameter("messageid"));
                request.setAttribute("role", role);
                request.setAttribute("message", messageManager.getMessage(request.getParameter("messageid")));
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/readMessage.jsp").forward(request, response);
            } else if (action.equals("replyMessage")) {
                request.setAttribute("role", role);
                request.setAttribute("receiver", request.getParameter("receiver"));
                request.setAttribute("username", request.getParameter("username"));
                subject = "Re: " + messageManager.getMessage(request.getParameter("messageid")).get(1);
                System.out.println("subject: " + "Re: " + messageManager.getMessage(request.getParameter("messageid")).get(1));
                request.setAttribute("subject", "Re: " + messageManager.getMessage(request.getParameter("messageid")).get(1));
                request.getRequestDispatcher("/replyMessage.jsp").forward(request, response);
            } else if (action.equals("replyResult")) {
                messageManager.sendMessage(request.getParameter("username"), request.getParameter("receiver"), subject, request.getParameter("reply"));
                request.setAttribute("reply", "true");
                request.setAttribute("role", role);

                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<ArrayList<String>> inbox = messageManager.getInbox(currentUser);
                int noOfRecords = inbox.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> inboxPage = messageManager.inboxPage(inbox, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(inboxPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("username", currentUser);
                request.setAttribute("inbox", inboxPage);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
            } else if (action.equals("bulletinBoard")) {
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<ArrayList<String>> board = bulletinManager.getBoard();
                int noOfRecords = board.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> boardPage = bulletinManager.boardPage(board, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(boardPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("board", boardPage);
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                request.getRequestDispatcher("/bulletinBoard.jsp").forward(request, response);
            } else if (action.equals("composeBulletin")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/composeBulletin.jsp").forward(request, response);
            } else if (action.equals("createBulletin")) {
                request.setAttribute("role", role);
                bulletinManager.releaseMessage(request.getParameter("message"), request.getParameter("subject"));
                request.setAttribute("created", "true");

                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<ArrayList<String>> board = bulletinManager.getBoard();
                int noOfRecords = board.size();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                ArrayList<ArrayList<String>> boardPage = bulletinManager.boardPage(board, (page - 1) * recordsPerPage, recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("recordSize", String.valueOf(boardPage.size()));
                request.setAttribute("currentPage", page);
                request.setAttribute("board", boardPage);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/bulletinBoard.jsp").forward(request, response);
            } else if (action.equals("readBulletin")) {
                request.setAttribute("role", role);
                ArrayList<String> message = bulletinManager.retrieveMessage(request.getParameter("messageid"));
                request.setAttribute("username", currentUser);
                request.setAttribute("message", message);
                request.getRequestDispatcher("/readBulletin.jsp").forward(request, response);
            } /*
             Product Management System
            
            
            
            
            
            
            
             */ else if (action.equals("productMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/productMain.jsp").forward(request, response);

            } else if (action.equals("propertyMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/propertyMain.jsp").forward(request, response);
            } else if (action.equals("sessionMain")) {
                String email = request.getParameter("email");
                boolean userFound = productSession.signIn(email);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/sessionMain.jsp").forward(request, response);
            } else if (action.equals("promotionMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/promotionMain.jsp").forward(request, response);
            } else if (action.equals("editPromo")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("data", data);
                request.getRequestDispatcher("/editPromotionMain.jsp").forward(request, response);
            } else if (action.equals("configuration")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/configuration.jsp").forward(request, response);
            } else if (action.equals("createSessionMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/createSessionMain.jsp").forward(request, response);
            } else if (action.equals("testCreateEvent")) {
                request.getRequestDispatcher("/testCreateEvent.jsp").forward(request, response);
            } else if (action.equals("createEvent")) {
                productManager.createEvent(request);
                request.setAttribute("created", "true");
                request.getRequestDispatcher("/testCreateEvent.jsp").forward(request, response);
            } else if (action.equals("createSession")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                String info = request.getParameter("id");
                System.out.println(info);
                String[] idType = info.split(" ");
                Long id = Long.valueOf(idType[0]);
                int no = Integer.valueOf(request.getParameter("no"));
                ArrayList data = productSession.getSessionEvent(idType[1], id, no);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/createSession.jsp").forward(request, response);
            } else if (action.equals("sessionCreated")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                int errorChecking = productManager.createSession(request);

                if (errorChecking == 0) {
                    request.setAttribute("error", "true");
                    request.getRequestDispatcher("/createSessionMain.jsp").forward(request, response);
                } else {
                    request.setAttribute("success", "true");
                    request.getRequestDispatcher("/createSessionMain.jsp").forward(request, response);
                }
            } else if (action.equals("deleteMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/deleteMain.jsp").forward(request, response);
            } else if (action.equals("deleteSelectSession")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                String info = request.getParameter("id");
                System.out.println(info);
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventSessions(i, idType[1]);
                request.setAttribute("eventType", idType[1]);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/deleteSelectSession.jsp").forward(request, response);
            } else if (action.equals("sessionDeleted")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                String[] id = request.getParameterValues("id");
                productSession.deleteSessions(id);
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("data", data);
                request.setAttribute("deleteSuccess", "true");
                request.getRequestDispatcher("/deleteMain.jsp").forward(request, response);
            } else if (action.equals("editMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/editMain.jsp").forward(request, response);
            } else if (action.equals("editSelectSessions")) {
                String info = request.getParameter("id");
                System.out.println(info);
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventSessions(i, idType[1]);
                request.setAttribute("eventType", idType[1]);
                request.setAttribute("data", data);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/editSelectSessions.jsp").forward(request, response);
            } else if (action.equals("editSessions")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                Long i = Long.valueOf(request.getParameter("id"));
                String type = request.getParameter("type");
                ArrayList data = productSession.editSessions(i, type);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/editSessions.jsp").forward(request, response);
            } else if (action.equals("sessionEdited")) {
                //ArrayList data = this.editSession(request);
                ArrayList data = productManager.editSession(request);
                int errorcode = productSession.writeSession(data);

                List<ArrayList> data2 = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data2);
                if (errorcode == 0) {
                    request.setAttribute("error", "true");
                    request.getRequestDispatcher("/editMain.jsp").forward(request, response);
                } else {
                    request.setAttribute("success", "true");
                    request.getRequestDispatcher("/editMain.jsp").forward(request, response);
                }
            } else if (action.equals("promotionCreated")) {
                String[] id;
                String type = request.getParameter("type");
                if (type.equals("3")) {
                    String info = request.getParameter("id");
                    id = new String[1];
                    id[0] = info;
                } else {
                    id = request.getParameterValues("id");
                }
                String name2 = request.getParameter("name");
                double discount = Double.valueOf(request.getParameter("discount"));
                String requirement = request.getParameter("requirement");
                String desc = request.getParameter("description");
                productSession.setPromotion_1(id, type, name2, discount, requirement, desc);
                request.setAttribute("promotionCreated1245", "true");
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/promotionMain.jsp").forward(request, response);
            } else if (action.equals("setPromotion")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/promotionMain.jsp").forward(request, response);
            } else if (action.equals("promotion")) {
                System.out.println("Entered promotion");
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                String type = request.getParameter("type");
                request.setAttribute("type", type);
                System.out.println("Type: " + type);
                if (type.equals("3")) {
                    request.getRequestDispatcher("/promotion_ticketMain.jsp").forward(request, response);
                } else if (type.equals("6")) {
                    request.getRequestDispatcher("/promotion_Customization.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/promotion.jsp").forward(request, response);
                }
            } else if (action.equals("promotionCreated")) {
                String[] id;
                String type = request.getParameter("type");
                if (type.equals("3")) {
                    String info = request.getParameter("id");
                    id = new String[1];
                    id[0] = info;
                } else {
                    id = request.getParameterValues("id");
                }
                String name2 = request.getParameter("name");
                double discount = Double.valueOf(request.getParameter("discount"));
                String requirement = request.getParameter("requirement");
                String desc = request.getParameter("description");
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("promoteCreated", "true");
                productSession.setPromotion_1(id, type, name2, discount, requirement, desc);
                request.getRequestDispatcher("/promotionMain.jsp").forward(request, response);
            } else if (action.equals("promotion_ticket")) {
                String info = request.getParameter("id");
                System.out.println(info);
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                String promotionType = request.getParameter("type");
                int category = productSession.getCategory(i, idType[1]);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("type", promotionType);
                request.setAttribute("info", info);
                request.setAttribute("category", category);
                request.getRequestDispatcher("/promotion_ticket.jsp").forward(request, response);
            } else if (action.equals("promotionCreated_Customization")) {
                String[] type = request.getParameterValues("type");
                String[] id = request.getParameterValues("id");
                String name2 = request.getParameter("name");
                double discount = Double.valueOf(request.getParameter("discount"));
                String requirement = request.getParameter("requirement");
                String desc = request.getParameter("description");
                productSession.setPromotion_3(type, id, name2, discount, requirement, desc);

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("customized", "true");
                request.getRequestDispatcher("/promotionMain.jsp").forward(request, response);

            } else if (action.equals("editSelectPromotion")) {
                String info = request.getParameter("id");
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventPromotion(i, idType[1]);
                request.setAttribute("eventType", idType[1]);
                request.setAttribute("data", data);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/editSelectPromotion.jsp").forward(request, response);
            } else if (action.equals("editPromotion")) {
                Long i = Long.valueOf(request.getParameter("id"));
                String type = request.getParameter("type");
                ArrayList data = productSession.editPromotion(i, type);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/editPromotion.jsp").forward(request, response);
            } else if (action.equals("promotionEdited")) {
                productManager.editPromotion(request);
                String id = request.getParameter("eventId").toString() + " " + request.getParameter("eventType");
                String[] idType = id.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventPromotion(i, idType[1]);
                request.setAttribute("eventType", idType[1]);
                request.setAttribute("data", data);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("edited", "true");
                request.getRequestDispatcher("/editSelectPromotion.jsp").forward(request, response);
            } else if (action.equals("deletePromotionMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/deletePromotionMain.jsp").forward(request, response);
            } else if (action.equals("deleteSelectPromotion")) {
                String info = request.getParameter("id");
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventPromotion(i, idType[1]);
                request.setAttribute("data", data);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/deleteSelectPromotion.jsp").forward(request, response);
            } else if (action.equals("promotionDeleted")) {
                String[] id = request.getParameterValues("id");
                productSession.deletePromotion(id);

                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.setAttribute("deleted", true);
                request.getRequestDispatcher("/deletePromotionMain.jsp").forward(request, response);
            } /*
             Property Management System
             */ else if (action.equals("viewAllProperty")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/viewAllProperty.jsp").forward(request, response);
            } else if (action.equals("concertHallLayout")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("sections", spm.getAllSectionsInOneProperty(spm.getPropertyByName("Merlion Concert Hall")));
                request.getRequestDispatcher("/concertHallLayout.jsp").forward(request, response);

            } else if (action.equals("theaterLayout")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("sections", spm.getAllSectionsInOneProperty(spm.getPropertyByName("Merlion Star Theater")));
                request.getRequestDispatcher("/theaterLayout.jsp").forward(request, response);

            } else if (action.equals("reservationMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/reservationMain.jsp").forward(request, response);
            } else if (action.equals("reservationSearch")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/reservationSearch.jsp").forward(request, response);
            } else if (action.equals("subReservationSearch")) {

                HttpSession session = request.getSession();
                Long eventid = (Long) session.getAttribute("eventid");
                System.out.println("=======session get eventid" + eventid);
                request.setAttribute("eventid", eventid);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/subReservationSearch.jsp").forward(request, response);
            } else if (action.equals("reservationSearchResult")) {

                try {
//                    HttpSession session = request.getSession();
//                    Long eventid = (Long) session.getAttribute("eventid");
                    String type = request.getParameter("eventcate");
//                    System.out.println("=======session get eventid" + eventid);
//                    request.setAttribute("eventid", eventid);
                    List<PropertyEntity> aProperties = rm.getAvailableProperties(request);
                    if (aProperties.isEmpty()) {
                        request.setAttribute("errormsg", " Please note: The date range you entered conflicts with an exsiting reservation or a maintenance shedule  ");
                        request.setAttribute("role", role);
                        request.setAttribute("username", currentUser);
                        request.getRequestDispatcher("/reservationSearch.jsp").forward(request, response);
                    } else {
                        List<PropertyEntity> properties = rm.getReservationSearchResult(aProperties, request);
                        List<PropertyEntity> pRList = rm.checkRecommendation(properties, request);
                        String daterange = request.getParameter("daterange");
                        if (properties.isEmpty()) {
                            request.setAttribute("errormsg", " Please note: There are no suitable venues matching the number of your expected auidence and type of event  ");
                            request.setAttribute("role", role);
                            request.setAttribute("username", currentUser);
                            request.getRequestDispatcher("/reservationSearch.jsp").forward(request, response);
                        } else {
                            request.setAttribute("pList", properties);
                            request.setAttribute("pRList", pRList);
                            request.setAttribute("daterange", daterange);
                            request.setAttribute("type", type);
                            request.setAttribute("role", role);
                            request.setAttribute("username", currentUser);
                            request.getRequestDispatcher("/reservationSearchResult.jsp").forward(request, response);
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (action.equals("subReservationSearchResult")) {

                try {
                    HttpSession session = request.getSession();
                    Long eventid = (Long) session.getAttribute("eventid");
                    String type = request.getParameter("eventcate");
                    System.out.println("=======session get eventid" + eventid);
                    request.setAttribute("eventid", eventid);
                    List<PropertyEntity> aProperties = rm.getAvailableProperties(request);
                    if (aProperties.isEmpty()) {
                        request.setAttribute("errormsg", " Please note: The date range you entered conflicts with an exsiting reservation or a maintenance shedule  ");
                        request.setAttribute("role", role);
                        request.setAttribute("username", currentUser);
                        request.getRequestDispatcher("/subReservationSearch.jsp").forward(request, response);
                    } else {
                        List<PropertyEntity> properties = rm.getReservationSearchResult(aProperties, request);
                        List<PropertyEntity> pRList = rm.checkRecommendation(properties, request);
                        String daterange = request.getParameter("daterange");
                        if (properties.isEmpty()) {
                            request.setAttribute("errormsg", " Please note: There are no suitable venues matching the number of your expected auidence and type of event  ");
                            request.setAttribute("role", role);
                            request.setAttribute("username", currentUser);
                            request.getRequestDispatcher("/subReservationSearch.jsp").forward(request, response);
                        } else {
                            request.setAttribute("pList", properties);
                            request.setAttribute("pRList", pRList);
                            request.setAttribute("daterange", daterange);
                            request.setAttribute("type",type);
                            request.setAttribute("role", role);
                            request.setAttribute("username", currentUser);
                            request.getRequestDispatcher("/subReservationSearchResult.jsp").forward(request, response);
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (action.equals("concertHallSelected")) {
                HttpSession session = request.getSession();
                String daterange = (String) session.getAttribute("daterange");
                String type = (String) session.getAttribute("type");
                request.setAttribute("type", type);
                request.setAttribute("daterange", daterange);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/concertHallSelected.jsp").forward(request, response);
            } else if (action.equals("theaterSelected")) {
                HttpSession session = request.getSession();
                String daterange = (String) session.getAttribute("daterange");
                String type = (String) session.getAttribute("type");
                request.setAttribute("type", type);
                request.setAttribute("daterange", daterange);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/theaterSelected.jsp").forward(request, response);
            } else if (action.equals("subConcertHallSelected")) {
                HttpSession session = request.getSession();
                String daterange = (String) session.getAttribute("daterange");
                Long eventid = (Long) session.getAttribute("eventid");
                String type = (String) session.getAttribute("type");
                request.setAttribute("type", type);
                System.out.println("=======session get eventid" + eventid);
                request.setAttribute("eventid", eventid);
                request.setAttribute("daterange", daterange);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/subConcertHallSelected.jsp").forward(request, response);
            } else if (action.equals("subTheaterSelected")) {
                HttpSession session = request.getSession();
                String daterange = (String) session.getAttribute("daterange");
                Long eventid = (Long) session.getAttribute("eventid");
                System.out.println("=======session get eventid" + eventid);
                String type = (String) session.getAttribute("type");
                request.setAttribute("type", type);
                request.setAttribute("eventid", eventid);
                request.setAttribute("daterange", daterange);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/subTheaterSelected.jsp").forward(request, response);
            } else if (action.equals("saveNewEvent")) {
                String daterange = request.getParameter("daterange");
                String pname = request.getParameter("pname");
                //String idStr = request.getParameter("propertyId");
                String ename = request.getParameter("eventname");
                String eDes = request.getParameter("eventdes");
                String email = request.getParameter("eoemail");
                String type = request.getParameter("type");
                System.out.println("Entered save new event 1.");
                System.out.println("email save new event: " + email);
                boolean checkUser = rm.checkUser(email);
                Long pid = spm.getPropertyByName(pname);

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                if (checkUser) {
                    System.out.println("Entered save new event 2.");
                    Event event = rm.addNewEvent(ename, eDes, daterange, pid, email,type);
                    if (event != null) {
                        System.out.println("Entered save new event 3.");
                        request.setAttribute("event", event);
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        request.setAttribute("start", format.format(event.getStart()));
                        request.setAttribute("end", format.format(event.getEnd()));
                        request.getRequestDispatcher("/saveNewEvent.jsp").forward(request, response);
                    } else {
                        System.out.println("Entered save new event 4.");
                        request.setAttribute("msg", "error when creating the reservation");
                        if (pname.equals("Merlion Concert Hall")) {
                            System.out.println("Entered save new event 5.");
                            request.getRequestDispatcher("/concertHallSelected.jsp").forward(request, response);
                        } else {
                            System.out.println("Entered save new event 6.");
                            request.getRequestDispatcher("/theaterSelected.jsp").forward(request, response);
                        }
                    }
                } else {
                    request.setAttribute("userResult", "Please note: The email you entered is not a valid user. Please enter again.");
                    if (pname.equals("Merlion Concert Hall")) {
                        request.setAttribute("eventname", ename);
                        request.setAttribute("eventdes", eDes);

                        request.setAttribute("eventdes", eDes);
                        request.getRequestDispatcher("/concertHallSelected.jsp").forward(request, response);
                    } else {
                        request.setAttribute("eventname", ename);
                        request.setAttribute("eventdes", eDes);
                        request.getRequestDispatcher("/theaterSelected.jsp").forward(request, response);
                    }

                }

                // 
            } else if (action.equals("saveNewSubEvent")) {
                String daterange = request.getParameter("daterange");
                String pname = request.getParameter("pname");
                // String idStr = request.getParameter("propertyId");
                String eidStr = request.getParameter("eventid");
                String ename = request.getParameter("eventname");
                //String eDes = request.getParameter("eventdes");
                String email = request.getParameter("eoemail");
                String type = request.getParameter("type");
                System.out.println("========Add New Sub Event" + daterange + pname + eidStr + ename + email+type);
                boolean checkUser = rm.checkUser(email);
                Long pid = spm.getPropertyByName(pname);

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);

                if (checkUser) {
                    SubEvent subevent = rm.addNewSubEvent(ename, daterange, pid, Long.valueOf(eidStr), email,type);
                    if (subevent != null) {
                        List<SubEvent> subevents = rm.getListOfSubEvent(subevent.getEvent());
                        request.setAttribute("subevents", subevents);
                        for (SubEvent e : subevents) {
                            System.out.println(e.getName());
                        }
                        request.setAttribute("eventid", eidStr);
                        // DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        //request.setAttribute("start", format.format(subevent.getStart()));
                        //request.setAttribute("end", format.format(subevent.getEnd()));
                        request.getRequestDispatcher("/saveNewSubEvent.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msg", "error when creating the reservation");
                        if ((spm.getPropertyById(pid).getPropertyName()).equals("Merlion Concert Hall")) {
                            request.getRequestDispatcher("/subConcertHallSelected.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("/subTheaterSelected.jsp").forward(request, response);
                        }
                    }
                } else {
                    request.setAttribute("userResult", "Please note: The email you entered is not a valid user. Please enter again.");
                    if ((spm.getPropertyById(pid).getPropertyName()).equals("Merlion Concert Hall")) {
                        request.getRequestDispatcher("/subConcertHallSelected.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/subTheaterSelected.jsp").forward(request, response);
                    }

                }
                // 
            } else if (action.equals("createEventWithSub")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("userResult", "");
                request.getRequestDispatcher("/createEventWithSub.jsp").forward(request, response);
            } else if (action.equals("saveEventWithSub")) {
                String eventName = request.getParameter("eventname");
                String eventDes = request.getParameter("eventdes");
                String eoemail = request.getParameter("eoemail");
                System.out.println(eventName + "  " + eventDes + "== == " + eoemail);
                boolean checkUser = rm.checkUser(eoemail);
                System.out.println("++++++" + checkUser);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                if (checkUser) {
                    request.setAttribute("event", rm.addNewEventWithSub(eventName, eventDes, eoemail));

                    request.getRequestDispatcher("/saveEventWithSub.jsp").forward(request, response);

                } else {
                    request.setAttribute("userResult", "Please note: The email you entered is not a valid user. Please enter again.");

                    request.getRequestDispatcher("/createEventWithSub.jsp").forward(request, response);
                }
            } else if (action.equals("addExtraEquipment")) {
                HttpSession session = request.getSession();
                Long pid = (Long) session.getAttribute("pid");
                Long seid = (Long) session.getAttribute("seid");

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("pid", pid);
                request.setAttribute("seid", seid);
                request.setAttribute("equipments", em.getNonSEquipmentInProperty(pid));
                request.getRequestDispatcher("/addExtraEquipment.jsp").forward(request, response);
            } else if (action.equals("addExtra")) {

                String eidStr = request.getParameter("eventid");
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("event", rm.getEventById(Long.valueOf(eidStr)));
                //request.setAttribute("eventid",rm.getSubEventById(Long.valueOf(seid)).getEvent().getId());
                //request.setAttribute("manpower", mm.getNonSManpowerInProperty(Long.valueOf(pid)));
                request.getRequestDispatcher("/addExtra.jsp").forward(request, response);
            } else if (action.equals("addExtraManpower")) {
                HttpSession session = request.getSession();
                String pid = (String) session.getAttribute("pid");
                String seid = (String) session.getAttribute("seid");

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("pid", pid);
                request.setAttribute("seid", seid);
                request.setAttribute("eventid", rm.getSubEventById(Long.valueOf(seid)).getEvent().getId());
                request.setAttribute("manpower", mm.getNonSManpowerInProperty(Long.valueOf(pid)));
                request.getRequestDispatcher("/addExtraManpower.jsp").forward(request, response);
            } else if (action.equals("saveExtraEquipment")) {
                String pidStr = request.getParameter("pid");
                String seidStr = request.getParameter("seid");
                String eventStr = request.getParameter("eid");
                String[] eValues = request.getParameterValues("evalue");

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("pid", pidStr);
                request.setAttribute("seid", seidStr);
                request.setAttribute("eventid", eventStr);
                request.setAttribute("equipment", rm.saveEquipmentSub(eValues, pidStr, seidStr));
                //  request.setAttribute("equipments", em.getNonSEquipmentInProperty(pid));
                request.getRequestDispatcher("/saveExtraEquipment.jsp").forward(request, response);
            } else if (action.equals("saveExtraManpower")) {
                String pidStr = request.getParameter("pid");
                String seidStr = request.getParameter("seid");
                String eventStr = request.getParameter("eventid");
                String[] eValues = request.getParameterValues("evalue");

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("pid", pidStr);
                request.setAttribute("seid", seidStr);
                request.setAttribute("eventid", eventStr);

                request.setAttribute("manpower", rm.saveManpowerSub(eValues, pidStr, seidStr));
                //  request.setAttribute("equipments", em.getNonSEquipmentInProperty(pid));

                request.getRequestDispatcher("/saveExtraManpower.jsp").forward(request, response);
            } else if (action.equals("addExtraEquipmentEvent")) {
                String pid = request.getParameter("pid");
                String eventid = request.getParameter("eventid");

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("pid", pid);
                request.setAttribute("seid", eventid);
                request.setAttribute("equipments", em.getNonSEquipmentInProperty(Long.valueOf(pid)));
                request.getRequestDispatcher("/addExtraEquipmentEvent.jsp").forward(request, response);
            } else if (action.equals("addExtraManpowerEvent")) {
                String pid = request.getParameter("pid");
                String eventid = request.getParameter("eventid");

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("pid", pid);
                request.setAttribute("eventid", eventid);
                request.setAttribute("manpower", mm.getNonSManpowerInProperty(Long.valueOf(pid)));
                request.getRequestDispatcher("/addExtraManpowerEvent.jsp").forward(request, response);
            } else if (action.equals("saveExtraEquipmentEvent")) {
                String pidStr = request.getParameter("pid");
                String eventidStr = request.getParameter("eventid");
                String[] eValues = request.getParameterValues("evalue");
                Event event = rm.getEventById(Long.valueOf(eventidStr));
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("pid", pidStr);
                request.setAttribute("eventid", eventidStr);
                request.setAttribute("event", event);
                request.setAttribute("equipment", rm.saveEquipmentEvent(eValues, pidStr, eventidStr));
                //  request.setAttribute("equipments", em.getNonSEquipmentInProperty(pid));
                request.getRequestDispatcher("/saveExtraEquipmentEvent.jsp").forward(request, response);
            } else if (action.equals("saveExtraManpowerEvent")) {
                String pidStr = request.getParameter("pid");
                String eventidStr = request.getParameter("eventid");
                String[] eValues = request.getParameterValues("evalue");

                Event event = rm.getEventById(Long.valueOf(eventidStr));
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("pid", pidStr);
                request.setAttribute("eventid", eventidStr);
                request.setAttribute("event", event);
                request.setAttribute("manpower", rm.saveManpowerEvent(eValues, pidStr, eventidStr));
                //  request.setAttribute("equipments", em.getNonSEquipmentInProperty(pid));

                request.getRequestDispatcher("/saveExtraManpowerEvent.jsp").forward(request, response);
            } else if (action.equals("maintenance")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());
                request.getRequestDispatcher("/maintenance.jsp").forward(request, response);
            } else if (action.equals("eventReservation")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());
                request.getRequestDispatcher("/eventReservation.jsp").forward(request, response);
            } else if (action.equals("subEventReservation")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());
                request.getRequestDispatcher("/subEventReservation.jsp").forward(request, response);
            } else if (action.equals("equipmentMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/equipmentMain.jsp").forward(request, response);
            } else if (action.equals("createEquipment")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());
                request.getRequestDispatcher("/createEquipment.jsp").forward(request, response);
            } else if (action.equals("editEquipment")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());

                request.getRequestDispatcher("/editEquipment.jsp").forward(request, response);
            } /*else if (action.equals("saveEditedEquipment")) {
             
                
             request.setAttribute("role", role);
             request.setAttribute("username", currentUser);
             request.setAttribute("properties",spm.getAllProperties());
             request.getRequestDispatcher("/editEquipment.jsp").forward(request, response);
             
             request.getRequestDispatcher("/deleteEquipment.jsp").forward(request, response);
             } */ else if (action.equals("viewAllEquipment")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("eList", em.getAllEquipments());
                request.getRequestDispatcher("/viewAllEquipment.jsp").forward(request, response);
            } else if (action.equals("setPriceEquipment")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/setPriceEquipment.jsp").forward(request, response);
            } else if (action.equals("outletMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/outletMain.jsp").forward(request, response);
            } else if (action.equals("viewAllOutlet")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/viewAllOutlet.jsp").forward(request, response);
            } else if (action.equals("equipmentCreated")) {

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                //String price = request.getParameter("eprice");
                String location = request.getParameter("elocation");
                String ename = request.getParameter("ename");
                String estandard = request.getParameter("estandard");
                Long ePropertyId = Long.valueOf(request.getParameter("epname"));
                Boolean standard = Boolean.TRUE;
                //  System.out.println(price);
                System.out.println(location);
                System.out.println(ename);
                System.out.println(estandard);
                System.out.println(ePropertyId);
                if (estandard.equals("ns")) {
                    standard = Boolean.FALSE;
                } else {
                    standard = Boolean.TRUE;
                }
                System.out.print(standard);
                EquipmentEntity equipment = em.createNewEquipment(ename, location, standard, ePropertyId);
                if (equipment != null) {
                    request.setAttribute("equipment", equipment);
                    if (!standard) {
                        request.setAttribute("standard", standard);
                    }
                    request.getRequestDispatcher("/setPriceJustCreated.jsp").forward(request, response);
                } else {
                    request.setAttribute("errormsg", "Please note:The equipment creation is failed");
                    request.getRequestDispatcher("/createEquipment.jsp").forward(request, response);
                }
            } else if (action.equals("setPriceJustCreated")) {
                String idStr = request.getParameter("eid");
                String eprice = request.getParameter("eprice");
                Long eid = Long.valueOf(idStr);
                System.out.println("==========test price" + eid + "  " + eprice);

                EquipmentEntity e = em.setNoSPrice(eid, eprice);

                request.setAttribute("equipment", e);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("standard", e.getStandard());
                request.setAttribute("successmsg", "Price has been set for this Non-Standard Equipment");
                request.getRequestDispatcher("/setPriceSaved.jsp").forward(request, response);
                // } else {
                //     request.setAttribute("errormsg", "Please note:The price setting is failed");
                //     request.getRequestDispatcher("/setPriceJustCreated.jsp").forward(request, response);
                // }
            } else if (action.equals("setPriceEquipment")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                // request.setAttribute("ensList", em.getAllNonStandardEquipments());
                request.getRequestDispatcher("/viewAllEquipment.jsp").forward(request, response);
            } else if (action.equals("manpowerMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/manpowerMain.jsp").forward(request, response);
            } else if (action.equals("viewAllManpower")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("mList", mm.getAllManpower());
                request.getRequestDispatcher("/viewAllManpower.jsp").forward(request, response);
            } else if (action.equals("createManpower")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());
                request.getRequestDispatcher("/createManpower.jsp").forward(request, response);
            } /* End of Property Management System Part 1
            
             
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
             Start Product Management System Part 2
             */ else if (action.equals("ticketReservation")) {
                String email = request.getParameter("email");
                boolean userFound = productSession.signIn(email);

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);

                request.getRequestDispatcher("/ticketReservation.jsp").forward(request, response);
            } else if (action.equals("setTickets")) {
                String info = request.getParameter("id");
                System.out.println(info);
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventSessions(i, idType[1]);
                List<ArrayList> sectionData = productSession.getReservedSections(i, idType[1]);
                Date startDate = productSession.getEventStartDate(i, idType[1]);
                String date = (String) new SimpleDateFormat("yyyy-MM-dd").format(startDate);
                request.setAttribute("date", date);
                request.setAttribute("data", data);
                request.setAttribute("sectionData", sectionData);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/setTickets.jsp").forward(request, response);
            } else if (action.equals("addTickets")) {
                String sectionData = request.getParameter("sectionData");
                System.out.println(sectionData + "****************************");
                sectionData = sectionData.substring(1, sectionData.length() - 1);
                System.out.println(sectionData + "****************************");

                String apply = request.getParameter("apply");
                Long i = Long.valueOf(request.getParameter("id"));
                String purpose = request.getParameter("purpose");
                String endDate = request.getParameter("date");
                request.setAttribute("success", "true");
                productSession.setReserveSection(apply, i, purpose, endDate, sectionData);

                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/reserveTicketsMain.jsp").forward(request, response);
            } else if (action.equals("deleteTickets")) {
                Long i = Long.valueOf(request.getParameter("id"));
                List<ArrayList> data = productSession.getSessionReservedSections(i);
                long propertyID = productSession.getPropertyID(i);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.setAttribute("propertyID", propertyID);
                request.getRequestDispatcher("/deleteTickets.jsp").forward(request, response);
            } else if (action.equals("deletedTickets")) {
                String[] id = request.getParameterValues("id");
                productSession.deleteSessionReservedSections(id);
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("success", "true");
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/deleteTicketsMain.jsp").forward(request, response);
            } else if (action.equals("logout")) {
                request.getRequestDispatcher("/logout.jsp").forward(request, response);
            } else if (action.equals("generateUser")) {
                productSession.generateUser();
            } else if (action.equals("displaySeatsMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/displaySeatsMain.jsp").forward(request, response);
            } else if (action.equals("displaySeats")) {
                String info = request.getParameter("id");
                System.out.println(info);
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventSessions(i, idType[1]);
                List<ArrayList> price = productSession.getSessionsPricing(i, idType[1]);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.setAttribute("price", price);
                request.getRequestDispatcher("/displaySeats.jsp").forward(request, response);
            } else if (action.equals("seatsPriceCreated")) {
                String apply = request.getParameter("apply");
                String seatsOption = request.getParameter("seatsOption");
                Long i = Long.valueOf(request.getParameter("id"));
                Integer noCat = Integer.valueOf(request.getParameter("noCat").toString());
                ArrayList<Double> cat = new ArrayList<Double>();

                for (int j = 1; j <= noCat; j++) {
                    cat.add(Double.valueOf(request.getParameter("cat" + j)));
                }

                productSession.setPricing(i, cat, noCat, apply, seatsOption);
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.setAttribute("success", "true");
                request.getRequestDispatcher("/displaySeatsMain.jsp").forward(request, response);
            } else if (action.equals("productEnterUser")) {
                ArrayList email = productSession.getEventOrganizersEmail();
                request.setAttribute("data", email);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/productEnterUser.jsp").forward(request, response);
            } /*
             End Product Management Part 2
            
            
            
            
             Property management System Part 2 Manpower and Outlets
             */ else if (action.equals("manpowerMain")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/manpowerMain.jsp").forward(request, response);
            } else if (action.equals("viewAllManpower")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("mList", mm.getAllManpower());
                request.getRequestDispatcher("/viewAllManpower.jsp").forward(request, response);
            } else if (action.equals("createManpower")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());
                request.getRequestDispatcher("/createManpower.jsp").forward(request, response);
            } else if (action.equals("manpowerCreated")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                //String price = request.getParameter("eprice");
                String mrole = request.getParameter("mrole");
                String mnumber = request.getParameter("mnumber");
                String mstandard = request.getParameter("mstandard");
                Long ePropertyId = Long.valueOf(request.getParameter("mname"));
                Boolean standard = Boolean.TRUE;
                //  System.out.println(price);
                System.out.println(mrole);
                System.out.println(mnumber);
                System.out.println(mstandard);
                System.out.println(ePropertyId);
                if (mstandard.equals("ns")) {
                    standard = Boolean.FALSE;
                } else {
                    standard = Boolean.TRUE;
                }
                System.out.print(standard);
                Integer mintnumber = Integer.valueOf(mnumber);
                ManpowerEntity manpower = mm.createNewManpower(mrole, mintnumber, standard, ePropertyId);
                if (manpower != null) {
                    request.setAttribute("manpower", manpower);
                    if (!standard) {
                        request.setAttribute("standard", standard);
                    }
                    request.getRequestDispatcher("/setPriceJustCreatedManpower.jsp").forward(request, response);
                } else {
                    request.setAttribute("errormsg", "Please note:The equipment creation is failed");
                    request.getRequestDispatcher("/createManpower.jsp").forward(request, response);
                }
            } else if (action.equals("setPriceJustCreatedManpower")) {
                String midStr = request.getParameter("mid");
                String mprice = request.getParameter("mprice");
                Long mid = Long.valueOf(midStr);
                System.out.println("==========test price" + mid + "  " + mprice);

                ManpowerEntity m = mm.mSetNoSPrice(mid, mprice);

                request.setAttribute("manpower", m);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("standard", m.getStandard());
                request.setAttribute("successmsg", "Price has been set for this Non-Standard Manpower");
                request.getRequestDispatcher("/setPriceSavedManpower.jsp").forward(request, response);
            } else if (action.equals("editManpower")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());

                request.getRequestDispatcher("/editManpower.jsp").forward(request, response);
            } else if (action.equals("createOutlet")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());
                request.getRequestDispatcher("/createOutlet.jsp").forward(request, response);
            } else if (action.equals("outletCreated")) {

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                //String price = request.getParameter("eprice");
                String oname = request.getParameter("oname");
                String otype = request.getParameter("otype");
                String description = request.getParameter("odescription");
                Long ePropertyId = Long.valueOf(request.getParameter("foname"));

                //  System.out.println(price);
                System.out.println(oname);
                System.out.println(otype);
                System.out.println(description);

                FoodOutletEntity foodoutlet = fom.createNewFoodOutlet(oname, otype, description, ePropertyId);
                if (foodoutlet != null) {
                    request.setAttribute("outlet", foodoutlet);
                    request.getRequestDispatcher("/outletCreated.jsp").forward(request, response);
                } else {
                    request.setAttribute("errormsg", "Please note:The Outlet creation is failed");
                    request.getRequestDispatcher("/createOutlet.jsp").forward(request, response);
                }
            } else if (action.equals("createOutlet")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());
                request.getRequestDispatcher("/createOutlet.jsp").forward(request, response);
            } else if (action.equals("outletCreated")) {

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                //String price = request.getParameter("eprice");
                String oname = request.getParameter("oname");
                String otype = request.getParameter("otype");
                String description = request.getParameter("odescription");
                Long ePropertyId = Long.valueOf(request.getParameter("foname"));

                //  System.out.println(price);
                System.out.println(oname);
                System.out.println(otype);
                System.out.println(description);

                FoodOutletEntity foodoutlet = fom.createNewFoodOutlet(oname, otype, description, ePropertyId);
                if (foodoutlet != null) {
                    request.setAttribute("outlet", foodoutlet);
                    request.getRequestDispatcher("/outletCreated.jsp").forward(request, response);
                } else {
                    request.setAttribute("errormsg", "Please note:The Outlet creation is failed");
                    request.getRequestDispatcher("/createOutlet.jsp").forward(request, response);
                }
            } else if (action.equals("editOutlet")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("properties", spm.getAllProperties());

                request.getRequestDispatcher("/editOutlet.jsp").forward(request, response);
            } /*
             End of Property Management System Part 2
            
            
            
             Start PRoduct Management System Part 3
             */ else if (action.equals("reserveTicketsMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/reserveTicketsMain.jsp").forward(request, response);
            } else if (action.equals("deleteTicketsMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/deleteTicketsMain.jsp").forward(request, response);
            } else if (action.equals("deleteTicketSelectSession")) {
                String info = request.getParameter("id");
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventSessions(i, idType[1]);
                request.setAttribute("eventType", idType[1]);
                request.setAttribute("data", data);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/deleteTicketSelectSession.jsp").forward(request, response);
            } else if (action.equals("closeSectionsMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/closeSectionsMain.jsp").forward(request, response);
            } else if (action.equals("closeSections")) {
                String info = request.getParameter("id");
                System.out.println(info);
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventSessions(i, idType[1]);
                List<ArrayList> sectionData = productSession.getClosedSections(i, idType[1]);
                request.setAttribute("data", data);
                request.setAttribute("sectionData", sectionData);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/closeSections.jsp").forward(request, response);
            } else if (action.equals("closedSections")) {
                String sectionData = request.getParameter("sectionData");
                sectionData = sectionData.substring(1, sectionData.length() - 1);

                String apply = request.getParameter("apply");
                Long i = Long.valueOf(request.getParameter("id"));
                String purpose = request.getParameter("purpose");

                productSession.setCloseSections(apply, i, purpose, sectionData);

                request.setAttribute("success", "true");
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/closeSectionsMain.jsp").forward(request, response);
            } else if (action.equals("resumeTicketMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/resumeTicketMain.jsp").forward(request, response);
            } else if (action.equals("resumeTicketSelectSession")) {
                String info = request.getParameter("id");
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventSessions(i, idType[1]);
                request.setAttribute("eventType", idType[1]);
                request.setAttribute("data", data);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/resumeTicketSelectSession.jsp").forward(request, response);
            } else if (action.equals("resumeSectionTickets")) {
                Long i = Long.valueOf(request.getParameter("id"));
                List<ArrayList> data = productSession.getSessionClosedSections(i);
                long propertyID = productSession.getPropertyID(i);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.setAttribute("propertyID", propertyID);
                request.getRequestDispatcher("/resumeSectionTickets.jsp").forward(request, response);
            } else if (action.equals("resumedTickets")) {
                String[] id = request.getParameterValues("id");
                productSession.deleteSessionReservedSections(id); //Cause this will delete the closed sections. So just reuse back
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("success", "true");
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/resumeTicketMain.jsp").forward(request, response);
            } else if (action.equals("alertMain")) {
                //String email = request.getParameter("email");
                boolean userFound = productSession.signIn(currentUser);
                System.out.println("ProductSESSION + " + currentUser);

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/alertMain.jsp").forward(request, response);
            } else if (action.equals("createAlertMain")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/createAlertMain.jsp").forward(request, response);
            } else if (action.equals("createAlert")) {
                String info = request.getParameter("id");
                System.out.println(info);
                String[] idType = info.split(" ");
                Long i = Long.valueOf(idType[0]);
                List<ArrayList> data = productSession.searchEventSessions(i, idType[1]);
                List<ArrayList> alerts = productSession.getAlerts(i, idType[1]);
                Date startDate = productSession.getEventStartDate(i, idType[1]);
                Date endDate = productSession.getEventEndDate(i, idType[1]);
                String startDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(startDate);
                String endDateString = (String) new SimpleDateFormat("yyyy-MM-dd").format(endDate);
                request.setAttribute("date", startDateString);
                request.setAttribute("alerts", alerts);
                request.setAttribute("endDate", endDateString);
                request.setAttribute("data", data);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/createAlert.jsp").forward(request, response);
            } else if (action.equals("createdAlert")) {

                String apply = request.getParameter("apply");
                Long i = Long.valueOf(request.getParameter("id"));
                String type = request.getParameter("type");
                String startDate = request.getParameter("date");
                String endDate = request.getParameter("endDate");
                int sales = Integer.valueOf(request.getParameter("sales"));
                String inCharge = request.getParameter("email");
                productSession.createAlert(apply, i, type, startDate, endDate, sales, inCharge);

                request.setAttribute("success", "true");
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.setAttribute("data", data);
                request.getRequestDispatcher("/createAlertMain.jsp").forward(request, response);
            } else if (action.equals("viewSalesProgress")) {
                List<ArrayList> data = productSession.getEventList();
                request.setAttribute("data", data);
                request.getRequestDispatcher("/viewSalesProgress.jsp").forward(request, response);
            } else if (action.equals("viewProgress")) {
                List<ArrayList> data = productSession.getEventList();
                List<ArrayList> sessionsDetails = getAllProductDetailsLocal.getAllSessions();
                List<ArrayList> salesDetails = getAllProductDetailsLocal.getSalesDetails();
                request.setAttribute("data", data);
                request.setAttribute("sessions", sessionsDetails);
                request.setAttribute("sales", salesDetails);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/viewProgress.jsp").forward(request, response);
            } else if (action.equals("seatConfiguration")) {
                String email = request.getParameter("email");
                boolean userFound = productSession.signIn(email);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/seatConfiguration.jsp").forward(request, response);
            } else if (action.equals("displaySeatsEnterUser")) {
                ArrayList email = productSession.getEventOrganizersEmail();
                request.setAttribute("data", email);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/displaySeatsEnterUser.jsp").forward(request, response);
            } else if (action.equals("promotionEnterUser")) {
                ArrayList email = productSession.getEventOrganizersEmail();
                request.setAttribute("data", email);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/promotionEnterUser.jsp").forward(request, response);
            } else if (action.equals("ticketReservationEnterUser")) {
                ArrayList email = productSession.getEventOrganizersEmail();
                request.setAttribute("data", email);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/ticketReservationEnterUser.jsp").forward(request, response);
            } else if (action.equals("alertEnterUser")) {
                ArrayList email = productSession.getEventOrganizersEmail();
                request.setAttribute("data", email);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/alertEnterUser.jsp").forward(request, response);
            } else if (action.equals("promotionOptions")) {
                String email = request.getParameter("email");
                boolean userFound = productSession.signIn(email);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/promotionOptions.jsp").forward(request, response);
            }/*
             End of Product Management Part 3
             */

        } catch (Exception ex) {
            ex.printStackTrace();
            //request.getRequestDispatcher("/error.jsp").forward(request, response);
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
