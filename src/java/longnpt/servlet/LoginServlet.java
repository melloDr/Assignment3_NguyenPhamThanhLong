/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.servlet;

import com.gpcoder.javaxmail.SendMailTLS;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.daos.UserDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {
        private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "SearchServlet";
    private static final String VERIFY = "verify.jsp";
    private static final String REGISTERPAGE = "register.jsp";
    private static final String SEARCHPAGE = "search.jsp";

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
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            String btnAction = request.getParameter("btnAction");
            if ("register".equals(btnAction)) {
                url = REGISTERPAGE;
                return;
            } else if ("search".equals(btnAction)) {
                url = SEARCHPAGE;
                return;
            }
            String txtEmailLogin = request.getParameter("txtEmailLogin");
            String PasswordLogin = request.getParameter("txtPasswordLogin");
            String name = UserDAO.checkLogin(txtEmailLogin, PasswordLogin);
            session.setAttribute("EMAIL_LOGIN", txtEmailLogin);
            session.setAttribute("NAME_LOGIN", UserDAO.getName(txtEmailLogin));
            if (!name.trim().isEmpty()) {
                String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
                boolean valid = longnpt.utils.VerifyUtils.verify(gRecaptchaResponse);
                if (valid) {
                    String status = UserDAO.getStatus(txtEmailLogin);
                    if ("new".equals(status)) {
                        SendMailTLS.sendVerify(txtEmailLogin.trim(), txtEmailLogin);
                        url = VERIFY;
                    }
                    if ("active".equals(status)) {
                        url = SUCCESS;
                    }
                } 
            } else {
                request.setAttribute("MSG_LOGIN", "Your account was wrong! Please try again!");
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
