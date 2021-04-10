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
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.daos.CarDAO;
import longnpt.daos.DemiseDAO;
import longnpt.daos.DiscountDAO;
import longnpt.dtos.DemiseDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class ConfirmServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ConfirmServlet.class);

    private static final String ERROR = "confirm.jsp";
    private static final String SEARCHPAGE = "search.jsp";
    private static final String SEARCH = "SearchServlet";

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
            float TOTAL_CART = (float) session.getAttribute("TOTAL_CART");
            String btnAction = request.getParameter("btnAction");
            if ("Add discount!".equals(btnAction)) {
                String txtCodeDiscount = request.getParameter("txtCodeDiscount");
                int percent = DiscountDAO.getDiscountPercent(txtCodeDiscount);
                if (percent == 0) {
                    session.setAttribute("MSG_DISCOUNT", "Please check the promotional code and date of use, thank you!");
                    url = ERROR;
                } else {
                    String discountId = DiscountDAO.getDiscountId(txtCodeDiscount);
                    session.setAttribute("DISCOUNTID", discountId);
                    request.setAttribute("MSG_PERCENT", "You get a " + percent + " percent bonus");
                    TOTAL_CART = TOTAL_CART * (100 - percent) / 100;
                    session.setAttribute("TOTAL_CART", TOTAL_CART);
                    DiscountDAO.setDiscountUsed(txtCodeDiscount);
                    url = ERROR;
                }
            } else if ("Complete the order".equals(btnAction)) {
                List<DemiseDTO> list = (List) session.getAttribute("LIST_RENT");
                String EMAIL_LOGIN = (String) session.getAttribute("EMAIL_LOGIN");
                String txtDateRental = (String) session.getAttribute("CONTENT_DATERENTAL");
                String txtDateReturn = (String) session.getAttribute("CONTENT_DATERETURN");

                String status = "true";
                String DISCOUNTID = (String) session.getAttribute("DISCOUNTID");
                if (DISCOUNTID == null || DISCOUNTID.isEmpty()) {
                    DISCOUNTID = "10";
                }       
                String now = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                DemiseDAO.addRent(EMAIL_LOGIN, TOTAL_CART, now, status, txtDateRental, txtDateReturn, DISCOUNTID);
                TimeUnit.SECONDS.sleep(2);
                // add detailRental
                int rentalId = DemiseDAO.getRentalID();
                for (int i = 0; i < list.size(); i++) {
                    DemiseDAO.addDemise(list.get(i).getCarId(), list.get(i).getQuantity(), list.get(i).getTotalPrice(), rentalId);
                    CarDAO.updateQuantityCar(list.get(i).getCarId(), DemiseDAO.getQuantityMax(list.get(i).getCarId()) - list.get(i).getQuantity());
                }
                list.removeAll(list);
                session.setAttribute("LIST_RENT", list);
                session.setAttribute("TOTAL_CART", 0);
                request.setAttribute("MSG_THANKS", "Thank you for using our service!");
                url = SEARCH;
            } else if ("search".equals(btnAction)) {
                url = SEARCHPAGE;
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
