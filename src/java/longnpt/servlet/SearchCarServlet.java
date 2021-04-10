/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.daos.CarDAO;
import longnpt.dtos.CarDTO;
import longnpt.dtos.DemiseDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class SearchCarServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SearchCarServlet.class);

    private static final String ERROR = "invalid.jsp";
    private static final String SUCCESS = "searchCar.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String SEARCHSERVLET = "SearchServlet";
    private static final String BACK = "SearchServlet";
    private static final String CART = "rent.jsp";
    private static final String FEEDBACK = "ShowFeedbackServlet";
    private static final String UPDATE = "UpdateServlet";

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
            request.setAttribute("END", "0");
            request.setAttribute("COUNT_NO", indexString);
            //----------------------------
            String btnAction = request.getParameter("btnAction");
            if ("login or register".equals(btnAction)) {
                url = LOGIN;
                return;
            }
            if ("Your cart".equals(btnAction)) {
                List<DemiseDTO> list = (List<DemiseDTO>) session.getAttribute("LIST_RENT");
                if (list == null) {
                    session.setAttribute("TOTAL_CART", 0);
                    session.setAttribute("SPACE_OF_DAY", 1);
                } else {
                    session.setAttribute("FLAG_RESET_CART", "a");
                    url = UPDATE;
                    return;
                }
                url = CART;
                return;
            }
            if ("Show feedback".equals(btnAction)) {
                url = FEEDBACK;
                return;
            }
            String txtContentToSearch = request.getParameter("txtContentToSearch");
            if (txtContentToSearch == null || txtContentToSearch.trim().isEmpty()) {
                request.setAttribute("MSG_INPUTNAME", "Please input name or category to search!");
                url = SEARCHSERVLET;
                return;
            }
            String txtDateRental = request.getParameter("txtDateRental");
            String txtDateReturn = request.getParameter("txtDateReturn");
            String txtSearchBy = request.getParameter("txtSearchBy");
            String txtQuantitySearch = request.getParameter("txtQuantitySearch");
            session.setAttribute("QUANTITY_SEARCH", txtQuantitySearch);
            session.setAttribute("CONTENT_SEARCH", txtContentToSearch);
            session.setAttribute("CONTENT_DATERENTAL", txtDateRental);
            session.setAttribute("CONTENT_DATERETURN", txtDateReturn);
            String now = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            session.setAttribute("DATE_START", now);
            if (txtDateRental.isEmpty() || txtDateReturn.isEmpty()) {
                List<CarDTO> list = CarDAO.searchListCar(txtContentToSearch, txtSearchBy, txtDateRental, txtDateReturn, txtQuantitySearch);
                String EMAIL_LOGIN = (String) session.getAttribute("EMAIL_LOGIN");
                if (txtContentToSearch == null || txtContentToSearch.trim().isEmpty()) {
//                list = CarDAO.getAllCar(indexString);
                    request.setAttribute("MSG_INPUTNAME", "Please input name or category to search!");
                    url = SEARCHSERVLET;
                    return;
                }
                session.setAttribute("LIST_ALL_CAR", list);
                if ("rent it!".equals(btnAction.trim())) {
                    if (EMAIL_LOGIN == null || EMAIL_LOGIN.trim().isEmpty()) {
                        request.setAttribute("MSG_RENT_LOGIN", "You must login to use this fuction!");
                        url = LOGIN;
                        return;
                    }
                }
                String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                session.setAttribute("MSG_BUYDATE", date);
                url = SUCCESS;

            }
            Date dateRental = sdf.parse(txtDateRental);
            Date dateReturn = sdf.parse(txtDateReturn);
            if (dateRental.before(dateReturn)) {
                List<CarDTO> list = CarDAO.searchListCar(txtContentToSearch, txtSearchBy, txtDateRental, txtDateReturn, txtQuantitySearch);
                String EMAIL_LOGIN = (String) session.getAttribute("EMAIL_LOGIN");
                if (txtContentToSearch == null || txtContentToSearch.trim().isEmpty()) {
//                list = CarDAO.getAllCar(indexString);
                    url = SEARCHSERVLET;
                    return;
                }
                session.setAttribute("LIST_ALL_CAR", list);
                if ("rent it!".equals(btnAction.trim())) {
                    if (EMAIL_LOGIN == null || EMAIL_LOGIN.trim().isEmpty()) {
                        request.setAttribute("MSG_RENT_LOGIN", "You must login to use this fuction!");
                        url = LOGIN;
                        return;
                    }
                    if (txtDateRental.isEmpty() || txtDateReturn.isEmpty()) {
                        request.setAttribute("MSG_DATE_TO_RENT", "Please enter the date to continue the function!");
                        url = BACK;
                        return;
                    }
                }
                url = SUCCESS;
            } else {
                request.setAttribute("MSG_DATE", "Date rent must before date return");
                url = BACK;
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
