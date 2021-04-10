/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnpt.daos.DemiseDAO;
import longnpt.dtos.CarDTO;
import longnpt.dtos.DemiseDTO;
import longnpt.utils.CheckValues;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class RentServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RentServlet.class);

    private static final String ERROR = "invalid.jsp";
    private static final String SUCCESS = "rent.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String BACK = "SearchServlet";

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
            String now = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            String txtDateRental = (String) session.getAttribute("CONTENT_DATERENTAL");
            String txtDateReturn = (String) session.getAttribute("CONTENT_DATERETURN");
            String EMAIL_LOGIN = (String) session.getAttribute("EMAIL_LOGIN");
            session.setAttribute("MSG_BUYDATE", now);
            if (EMAIL_LOGIN == null || EMAIL_LOGIN.trim().isEmpty()) {
                request.setAttribute("MSG_RENT_LOGIN", "You must login to use this fuction!");
                url = LOGIN;
                return;
            }
            if (txtDateRental == null || txtDateReturn == null || txtDateRental.isEmpty() || txtDateReturn.isEmpty()) {
                request.setAttribute("MSG_DATE_TO_RENT", "Please enter the date to continue the function!");
                url = BACK;
                return;
            }
            String txtCarIdToRent = request.getParameter("txtCarIdToRent");
            List<DemiseDTO> list = (List) session.getAttribute("LIST_RENT");
            List<CarDTO> listCar = (List) session.getAttribute("LIST_ALL_CAR");
            DemiseDTO dto = DemiseDAO.searchCarPrepare(txtCarIdToRent);
            float totalPriceRental = 0;
            int i = CheckValues.getIndexListDemise(list, txtCarIdToRent);
            int j = CheckValues.getIndexListCar(listCar, txtCarIdToRent);
            if (i >= 0) { //hàm kiểm tra quantity vào
                if (list.get(i).getQuantity() >= listCar.get(j).getQuantity()) {
                    request.setAttribute("MSG_QUANTITY_ADD", "Your quantity must not be larger than the number of cars!");
                    url = SUCCESS;
                    return;
                }
            }
            float totalPrice = 0;
            String a = "";
            if (list == null) {
                list = new ArrayList<>();
                list.add(dto);
            } else if (i != -1) { // hàm kiểm tra xe add thêm có trùng không
                list.get(i).setQuantity(list.get(i).getQuantity() + 1);
                totalPrice = list.get(i).getPrice() * list.get(i).getQuantity();
                list.get(i).setTotalPrice(totalPrice);
                totalPriceRental = totalPrice;
                a = "1";
            } else {
                list.add(dto);
            }
            int quantityMax = DemiseDAO.getQuantityMax(txtCarIdToRent);
            session.setAttribute("QUANTITY_MAX", quantityMax);
            session.setAttribute("LIST_RENT", list);
            String stringtotalPriceRental = "";
            if (list.size() != 1) {
                try {
                    stringtotalPriceRental = (String) session.getAttribute("TOTAL_CART");//try catch ở đây
                    totalPriceRental = Float.parseFloat(stringtotalPriceRental);
                } catch (Exception e) {
                    totalPriceRental = (float) session.getAttribute("TOTAL_CART");
                }
                totalPriceRental = 0;
                for (int k = 0; k < list.size(); k++) {
                    totalPriceRental += list.get(k).getQuantity() * list.get(k).getPrice();
                }
                 session.setAttribute("SPACE_OF_DAY", 1);
//                totalPriceRental += dto.getPrice();
            } else {
                if (!"1".equals(a)) {
                    totalPriceRental = dto.getPrice();
                }
            }
            session.setAttribute("TOTAL_CART", totalPriceRental);
            url = SUCCESS;
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
