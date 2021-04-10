/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import longnpt.dtos.CarDTO;
import longnpt.dtos.FeedbackDTO;
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class CarDAO {

    private static final Logger LOGGER = Logger.getLogger(CarDAO.class);

    public static List<CarDTO> getAllCar(int index) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CarDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select carId,name,color,year,category,price,quantity\n"
                        + "from\n"
                        + "(select ROW_NUMBER() over (order by name) as r, carId,name,color,year,category,price,quantity\n"
                        + "from tblCars\n"
                        + "where quantity > 0) as x\n"
                        + "where r between " + index + " * 20 -19 and " + index + " * 20";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int carId = rs.getInt("carId");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    String category = rs.getString("category");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    list.add(new CarDTO(carId, name, color, year, category, price, quantity));
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public static List<CarDTO> searchListCar(String content, String searchBy, String dateRental, String dateReturn, String quantityToSearch) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CarDTO> list = new ArrayList<>();
        CarDTO car = null;
        String sqlAddQuantity = "";
        if (quantityToSearch == null || quantityToSearch.isEmpty()) {
            quantityToSearch = "1";
        }
        sqlAddQuantity = "and quantity >= " + quantityToSearch + "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select carId,name,color,year,category,price,quantity\n"
                        + "from tblCars\n"
                        + "where " + searchBy + " like '%" + content + "%' ";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int carId = rs.getInt("carId");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    String category = rs.getString("category");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");// nếu quantity mà = 0  thì gọi hàm dưới chạy
                    // mục tiêu để add vào những cái xe sắp được ra tù
                    if (quantity == 0) {
                        if (dateRental != null || !dateRental.isEmpty() || dateReturn != null || !dateReturn.isEmpty()) {
                            car = searchCarPrepare(carId, dateRental, dateReturn, quantityToSearch);
                            if (car != null) {
                                list.add(car);
                            } else {
                                car = searchCarPrepare2(carId, dateRental, dateReturn, quantityToSearch);
                                if (car != null) {
                                    list.add(car);
                                }
                            }
                        }
                    }
                    if (quantity >= Integer.parseInt(quantityToSearch)) {
                        list.add(new CarDTO(carId, name, color, year, category, price, quantity));
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public static CarDTO searchCarPrepare(int carId, String dateRental, String dateReturn, String quantityToSearch) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        CarDTO car = null;
        String sqlAddQuantity = "";
        if (!quantityToSearch.trim().isEmpty() || quantityToSearch != null) {
            sqlAddQuantity = "and quantity >= " + quantityToSearch + "";
        }
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select c.carId,name,color,year,category,price,(c.quantity + d.quantity) as quantity\n"
                        + "from tblCars c join tblDemise d \n"
                        + "on c.carId = d.carId\n"
                        + "where d.dateReturn < '" + dateRental + "'  and c.carId = " + carId + " ";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    String category = rs.getString("category");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    car = new CarDTO(carId, name, color, year, category, price, quantity);
//                    list.add(new CarDTO(carId, name, color, year, category, price, quantity));
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return car;
    }

    public static CarDTO searchCarPrepare2(int carId, String dateRental, String dateReturn, String quantityToSearch) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        CarDTO car = null;
        String sqlAddQuantity = "";
        if (!quantityToSearch.trim().isEmpty() || quantityToSearch != null) {
            sqlAddQuantity = "and quantity >= " + quantityToSearch + "";
        }
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select c.carId,name,color,year,category,price,(c.quantity + d.quantity) as quantity\n"
                        + "from tblCars c join tblDemise d \n"
                        + "on c.carId = d.carId\n"
                        + "where d.dateRental > '" + dateReturn + "'  and c.carId = " + carId + " ";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    String category = rs.getString("category");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    car = new CarDTO(carId, name, color, year, category, price, quantity);
//                    list.add(new CarDTO(carId, name, color, year, category, price, quantity));
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return car;
    }

    public static int countCar() throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select COUNT(*) from tblCars where quantity > 0";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return result;
    }

    public static void updateQuantityCar(String carId, int quantity) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update tblCars\n"
                        + "set quantity = '" + quantity + "'\n"
                        + "where carId = '" + carId + "'";
                pst = cn.prepareStatement(sql);
                pst.executeQuery();
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public static String getCarId(String carName) throws SQLException {
        String carId = "";
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select carId\n"
                        + "from tblCars \n"
                        + "where name = '" + carName + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    carId = rs.getString("carId");
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return carId;
    }

    public static void updateFeedbackAndRate(String carId, String feedback, int rating) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update  tblCars\n"
                        + "set feedback = '" + feedback + "', rating = '" + rating + "'\n"
                        + "where carId = '" + carId + "'";
                pst = cn.prepareStatement(sql);
                pst.executeQuery();
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public static List<FeedbackDTO> getAllCarToShowFeedback(int index) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<FeedbackDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select carId,name,feedback,rating\n"
                        + "from\n"
                        + "(select ROW_NUMBER() over (order by year) as r, carId,name,feedback,rating\n"
                        + "from tblCars\n"
                        + "where quantity > 0) as x\n"
                        + "where r between " + index + " * 20 -19 and " + index + " * 20";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String carId = rs.getString("carId");
                    String name = rs.getString("name");
                    String feedback = rs.getString("feedback");
                    if (feedback == null || feedback.isEmpty()) {
                        feedback = "No one has rated this car yet!";
                    }
                    int rating = rs.getInt("rating");
                    list.add(new FeedbackDTO(carId, name, feedback, rating));
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }
}
