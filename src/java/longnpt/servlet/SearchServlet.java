/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.daos.CarDAO;
import longnpt.dtos.CarDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class SearchServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SearchServlet.class);

    private static final String ERROR = "search.jsp";
    private static final String SEARCHCAR = "SearchCarServlet";
    private static final String LOGIN = "login.jsp";
    private static final String LOGINPAGE = "login.jsp";

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
            String btnPage = request.getParameter("btnPage");
            String txtEnd = null;
            if (btnPage != null) {
                txtEnd = btnPage;
            }
            int indexString = 1;
            if (txtEnd != null) {
                indexString = Integer.parseInt(txtEnd);
            }
            int count = CarDAO.countCar();
            int pageSize = 20, endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            request.setAttribute("END", endPage);
            request.setAttribute("COUNT_NO", indexString);
            //----------------------------
            session.setAttribute("FLAG_RESET_CART", "b");
            String btnAction = request.getParameter("btnAction");
            session.setAttribute("SPACE_OF_DAY", 1);
            if ("login or register".equals(btnAction)) {
                url = LOGINPAGE;
                return;
            }
            List<CarDTO> list = CarDAO.getAllCar(indexString);
            String txtContentToSearch = request.getParameter("txtContentToSearch");
            session.setAttribute("CONTENT_SEARCH", txtContentToSearch);
            String txtSearchBy = request.getParameter("txtSearchBy");
            String EMAIL_LOGIN = (String) session.getAttribute("EMAIL_LOGIN");
            String txtDateRental = (String) session.getAttribute("CONTENT_DATERENTAL");
            String txtDateReturn = (String) session.getAttribute("CONTENT_DATERETURN");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String now = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            session.setAttribute("DATE_START", now);
            String txtQuantitySearch = (String) session.getAttribute("QUANTITY_SEARCH");
            if (txtContentToSearch == null || ("" + txtContentToSearch).isEmpty() == true) {
                session.setAttribute("LIST_ALL_CAR", list);
                if ("rent it!".equals(btnAction.trim())) {
                    if (EMAIL_LOGIN == null || EMAIL_LOGIN.trim().isEmpty()) {
                        request.setAttribute("MSG_RENT_LOGIN", "You must login to use this fuction!");
                        url = LOGIN;
                    }
                    if (txtDateRental.isEmpty() || txtDateReturn.isEmpty()) {
                        request.setAttribute("MSG_DATE_TO_RENT", "Please enter the date to continue the function!");
                        return;
                    }
                }
//                list = CarDAO.searchListCar(txtContentToSearch, txtSearchBy);
            } else {
                if (txtDateRental != null || txtDateReturn != null) {
                } else {
                    url = SEARCHCAR;
                }
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
