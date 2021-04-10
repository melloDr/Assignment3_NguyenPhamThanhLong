/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.dtos.DemiseDTO;
import longnpt.utils.CheckValues;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class UpdateServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdateServlet.class);

    private static final String ERROR = "rent.jsp";
    private static final String SUCCESS = "rent.jsp";
    private static final String CONFIRM = "confirm.jsp";
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
            String btnAction = request.getParameter("btnAction");
            String FLAG_RESET_CART = (String) session.getAttribute("FLAG_RESET_CART") + "";
            if ("a".equals(FLAG_RESET_CART)) {
                btnAction = "Update quantity";
            }
            if ("Update quantity".equals(btnAction)) {
                List<DemiseDTO> list = (List<DemiseDTO>) session.getAttribute("LIST_RENT");
                String txtQuantityToRent = request.getParameter("txtQuantityToRent");
                session.setAttribute("QUANTITY_SEARCH", txtQuantityToRent);
                String txtIdToUpdate = request.getParameter("txtIdToUpdate");
                int i = CheckValues.getIndexListDemise(list, txtIdToUpdate);
                if (i < 0) {
                    session.setAttribute("FLAG_RESET_CART", "b");
                }
                list.get(i).setQuantity(Integer.parseInt(txtQuantityToRent));
                float totalPrice = list.get(i).getPrice() * list.get(i).getQuantity();
                list.get(i).setTotalPrice(totalPrice);
//                int checkQuantity = list.get(i).getQuantity();
                float totalPriceRental = (float) session.getAttribute("TOTAL_CART");
                if (totalPriceRental == 0) {
                    totalPriceRental = totalPrice;
                } else {
                    totalPriceRental = CheckValues.getTotalPriceCart(list);
                }
                session.setAttribute("SPACE_OF_DAY", 1);
                session.setAttribute("TOTAL_CART", totalPriceRental);
                session.setAttribute("LIST_RENT", list);
                session.setAttribute("FLAG_RESET_CART", "b");
                url = SUCCESS;
            } else if ("Delete".equals(btnAction)) {
                String txtIdToDelete = request.getParameter("txtIdToDelete");
                float totalPriceRental = (float) session.getAttribute("TOTAL_CART");
                List<DemiseDTO> list = (List<DemiseDTO>) session.getAttribute("LIST_RENT");
                int i = CheckValues.getIndexListDemise(list, txtIdToDelete);
//                totalPriceRental -= list.get(i).getTotalPrice();
//                totalPriceRental = CheckValues.getTotalPriceCart(list);
                list.remove(i);
                totalPriceRental = 0;
                for (int j = 0; j < list.size(); j++) {
                    totalPriceRental = list.get(j).getQuantity() * list.get(j).getTotalPrice();
                }
                session.setAttribute("LIST_RENT", list);
                session.setAttribute("TOTAL_CART", totalPriceRental);
                url = SUCCESS;
            } else if ("Confirm".equals(btnAction)) {
                String txtDateRental = (String) session.getAttribute("CONTENT_DATERENTAL");
                String txtDateReturn = (String) session.getAttribute("CONTENT_DATERETURN");
                int spaceOfDay = CheckValues.getDays(txtDateRental, txtDateReturn);
                session.setAttribute("SPACE_OF_DAY", spaceOfDay);
                float totalPriceRental = 0;
                try {
                    totalPriceRental = (float) session.getAttribute("TOTAL_CART");
                } catch (Exception e) {
                    totalPriceRental = 0;
                }
                int SPACE_OF_DAY = (int) session.getAttribute("SPACE_OF_DAY");
                if (totalPriceRental == 0) {
                    request.setAttribute("MSG_CART", "Your cart is empty!");
                    url = SEARCH;
                    return;
                } else {
                    totalPriceRental = 0;
                    List<DemiseDTO> list = (List<DemiseDTO>) session.getAttribute("LIST_RENT");
                    for (int i = 0; i < list.size(); i++) {
                        totalPriceRental = list.get(i).getQuantity() * list.get(i).getTotalPrice();
                    }
                }
                totalPriceRental *= SPACE_OF_DAY;
                session.setAttribute("TOTAL_CART", totalPriceRental);
                url = CONFIRM;
            } else if ("search".equals(btnAction)) {
                url = SEARCHPAGE;
                return;
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
