/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
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
public class RegisterServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "login.jsp";
    private static final String BACK = "register.jsp";

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
            if ("login".equals(btnAction)) {
                url = SUCCESS;
                return;
            }
            String txtEmailToRegister = request.getParameter("txtEmailToRegister");
            session.setAttribute("TXTEMAIL", txtEmailToRegister);
            String txtPassToRegister = request.getParameter("txtPassToRegister");
            session.setAttribute("TXTPASS", txtPassToRegister);
            String txtPhoneToRegister = request.getParameter("txtPhoneToRegister");
            session.setAttribute("TXTPHONE", txtPhoneToRegister);
            String txtNameToRegister = request.getParameter("txtNameToRegister");
            session.setAttribute("TXTNAME", txtNameToRegister);
            String txtAddressToRegister = request.getParameter("txtAddressToRegister");
            session.setAttribute("TXTADDRESS", txtAddressToRegister);
            String name = UserDAO.checkName(txtEmailToRegister);
            if (txtEmailToRegister.isEmpty()) {
                request.setAttribute("MSG_REGISTER", "Your email's not impossible!");
                url = BACK;
            } else if (!name.isEmpty()) {
                request.setAttribute("MSG_REGISTER", "This email is already registered!");
                url = BACK;
            } else if (txtPassToRegister.isEmpty()) {
                request.setAttribute("MSG_REGISTER", "Your pass's not impossible!");
                url = BACK;
            } else if (txtPhoneToRegister.isEmpty()) {
                request.setAttribute("MSG_REGISTER", "Your phone's not impossible!");
                url = BACK;
            } else if (txtNameToRegister.isEmpty()) {
                request.setAttribute("MSG_REGISTER", "Your name's not impossible!");
                url = BACK;
            } else if (txtAddressToRegister.isEmpty()) {
                request.setAttribute("MSG_REGISTER", "Your address's not impossible!");
                url = BACK;
            } else {
                String txtDateToRegister = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                String txtStatusToRegister = "new";
                Random generator = new Random();
                int code = generator.nextInt(10000) + 1;
                UserDAO.addUser(txtEmailToRegister, txtPassToRegister, txtPhoneToRegister, txtNameToRegister, txtAddressToRegister, txtDateToRegister, txtStatusToRegister, code);
                session.setAttribute("MSG_REGISTER", "Your account has been successfully registered!");
                url = SUCCESS;
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
