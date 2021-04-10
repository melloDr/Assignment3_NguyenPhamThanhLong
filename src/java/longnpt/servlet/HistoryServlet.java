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
import longnpt.daos.DemiseDAO;
import longnpt.daos.DemiseDetailDAO;
import longnpt.daos.HistoryDAO;
import longnpt.dtos.DemiseDetailDTO;
import longnpt.dtos.HistoryDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class HistoryServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(HistoryServlet.class);

    private static final String SUCCESS = "history.jsp";
    private static final String ERROR = "invalid.jsp";
    private static final String REVIEW = "review.jsp";

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
        String url = SUCCESS;
        HttpSession session = request.getSession();
        try {
            String btnAction = request.getParameter("btnAction");
            List<HistoryDTO> list = HistoryDAO.getListAllHistory();
            session.setAttribute("LIST_SEARCH_HISTORY", list);
            String txtemail = (String) session.getAttribute("EMAIL_LOGIN");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if ("Search history".equals(btnAction)) {
                String txtcontentToSearch = request.getParameter("contentToSearch");
                String txtdateToSearch = request.getParameter("dateToSearch");
                if (!txtcontentToSearch.trim().isEmpty()) {
                    String carId = CarDAO.getCarId(txtcontentToSearch);
                    if (carId == null || carId.isEmpty()) {
                        url = SUCCESS;
                        return;
                    }
                    list = HistoryDAO.searchHistoryByName(carId, txtemail);
                } else {
                    list = HistoryDAO.searchHistoryByDate(txtdateToSearch, txtemail);
                }
                if (list != null || !list.isEmpty()) {
                    session.setAttribute("LIST_SEARCH_HISTORY", list);
                    url = SUCCESS;
                } else {
                    session.setAttribute("MSG_SEARCH_HISTORY", "Your search term is not available!");
                    url = SUCCESS;
                }
            } else if ("Cancel this order".equals(btnAction)) {
                String txtRentIdToCancel = request.getParameter("txtRentIdToCancel");
                String now = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                String dateRental = DemiseDAO.getDateRental(txtRentIdToCancel);
                Date date1 = sdf.parse(now);
                Date date2 = sdf.parse(dateRental);
                if (!date1.before(date2)) {
                    session.setAttribute("MSG_CANCLE_IN_PAST", "you cannot cancel an order in the past!");
                    list = HistoryDAO.getListAllHistory();
                    session.setAttribute("LIST_SEARCH_HISTORY", list);
                    url = SUCCESS;
                    return;
                }
                HistoryDAO.updateStatus(txtRentIdToCancel, txtemail);
                session.setAttribute("MSG_CANCEL_ORDER", "Your order has been successfully canceled!");
                list = HistoryDAO.getListAllHistory();
                session.setAttribute("LIST_SEARCH_HISTORY", list);
                List<DemiseDetailDTO> listToUpdateQuantity = DemiseDetailDAO.getDemiseOfRent(txtRentIdToCancel);
                for (int i = 0; i < listToUpdateQuantity.size(); i++) {
                    HistoryDAO.updateQuantityAfterCancel(listToUpdateQuantity.get(i).getQuantity(), listToUpdateQuantity.get(i).getCarId());
                }
            } else if ("View detail and rating".equals(btnAction)) {
                String txtRentIdToView = request.getParameter("txtRentIdToView");
                session.setAttribute("txtRentIdToView", txtRentIdToView);
                List<DemiseDetailDTO> listToView = DemiseDetailDAO.getDemiseOfRent(txtRentIdToView);
                session.setAttribute("LIST_REVIEW", listToView);
                url = REVIEW;
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
