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
import longnpt.dtos.DemiseDTO;
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class DemiseDAO {
    private static final Logger LOGGER = Logger.getLogger(DemiseDAO.class);

    public static DemiseDTO searchCarPrepare(String carId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DemiseDTO dto = new DemiseDTO();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select carId, name,category,price\n"
                        + "from tblCars c \n"
                        + "where carId = '" + carId + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    float price = rs.getFloat("price");
                    int quantity = 1;
                    dto = new DemiseDTO(carId, name, category, price, quantity, price);
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
        return dto;
    }

    public static int getQuantityMax(String carId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int quantity = 0;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select quantity\n"
                        + "from tblCars\n"
                        + "where carId = '" + carId + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

    public static void addRent(String email, float total, String rentalDate, String status, String dateRental, String dateReturn, String discountId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "insert tblRental(email, total, rentalDate,status,dateRental,dateReturn,discountId)\n"
                        + "values('" + email + "','" + total + "','" + rentalDate + "','" + status + "','" + dateRental + "','" + dateReturn + "','" + discountId + "')";
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

    public static void addDemise(String carId, int quantity, float totalPrice, int rentId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "insert tblDemise(carId,quantity,totalPrice,rentId)\n"
                        + "values('" + carId + "','" + quantity + "','" + totalPrice + "','" + rentId + "')";
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

    public static int getRentalID() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "SELECT TOP 1 rentId as result FROM tblRental ORDER BY rentId DESC";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("result");
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

    public static String getDateRental(String rentId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String dateRental = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select  dateRental\n"
                        + "from tblRental\n"
                        + "where rentId = '" + rentId + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    dateRental = rs.getString("dateRental");
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
        return dateRental;
    }

    public static String getDateReturn(String rentId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String dateReturn = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select  dateReturn\n"
                        + "from tblRental\n"
                        + "where rentId = '" + rentId + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    dateReturn = rs.getString("dateReturn");
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
        return dateReturn;
    }
}
