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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.daos.CarDAO;
import longnpt.daos.DemiseDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class ReviewServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ReviewServlet.class);

    private static final String ERROR = "review.jsp";
    private static final String SUCCESS = "HistoryServlet";
    private static final String HOME = "SearchServlet";

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
            String btnAction = request.getParameter("btnAction");
            if ("Home".equals(btnAction)) {
                url = HOME;
                return;
            }
            String txtCarIdToRating = request.getParameter("txtCarIdToRating");
            int txtRating = Integer.parseInt(request.getParameter("txtRating"));
            String txtFeedback = request.getParameter("txtFeedback");
            if ("Complete".equals(btnAction)) {
                String now = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                String txtRentIdToView = (String) session.getAttribute("txtRentIdToView");
                String dateReturn = DemiseDAO.getDateReturn(txtRentIdToView);
                Date date1 = sdf.parse(dateReturn);
                Date date2 = sdf.parse(now);
                if (!date1.before(date2)) {
                    request.setAttribute("MSG_RATING_IN_PART", "you cannot evaluate a vehicle without completing an order!");
                    return;
                }

                CarDAO.updateFeedbackAndRate(txtCarIdToRating, txtFeedback, txtRating);
                session.setAttribute("MSG_FEEDBACK", "Thanks for feedback!");
                url = ERROR;
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
