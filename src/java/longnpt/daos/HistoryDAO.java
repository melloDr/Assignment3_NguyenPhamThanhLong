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
import longnpt.dtos.HistoryDTO;
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class HistoryDAO {
    private static final Logger LOGGER = Logger.getLogger(HistoryDAO.class);

    public static List<HistoryDTO> searchHistoryByDate(String date, String email) throws SQLException {
        List<HistoryDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select ROW_NUMBER() over (order by rentalDate),rentId ,total,rentalDate,dateRental,dateReturn \n"
                        + "from tblRental r join tblUser u\n"
                        + "on r.email = u.email\n"
                        + "where rentalDate = '" + date + "' and r.status = 'true' and r.email = '" + email + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String rentId = rs.getString("rentId");
                    float total = rs.getFloat("total");
                    String rentalDate = rs.getString("rentalDate");
                    String dateRental = rs.getString("dateRental");
                    String dateReturn = rs.getString("dateReturn");
                    list.add(new HistoryDTO(rentId, total, rentalDate, dateRental, dateReturn));
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

    public static List<HistoryDTO> searchHistoryByName(String carId, String email) throws SQLException {
        List<HistoryDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select ROW_NUMBER() over (order by r.rentalDate), r.rentId,total,rentalDate,dateRental,dateReturn\n"
                        + "from tblRental r join tblDemise d \n"
                        + "on r.rentId = d.rentId join tblCars c\n"
                        + "on d.carId = c.carId join tblUser u\n"
                        + "on r.email = u.email\n"
                        + "where c.carId = '" + carId + "' and r.status = 'true' and r.email = '" + email + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String rentId = rs.getString("rentId");
                    float total = rs.getFloat("total");
                    String rentalDate = rs.getString("rentalDate");
                    String dateRental = rs.getString("dateRental");
                    String dateReturn = rs.getString("dateReturn");
                    list.add(new HistoryDTO(rentId, total, rentalDate, dateRental, dateReturn));
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

    public static List<HistoryDTO> getListAllHistory() throws SQLException {
        List<HistoryDTO> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select rentId,total,rentalDate,dateRental,dateReturn\n"
                        + "from tblRental\n"
                        + "where status = 'true'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String rentId = rs.getString("rentId");
                    float total = rs.getFloat("total");
                    String rentalDate = rs.getString("rentalDate");
                    String dateRental = rs.getString("dateRental");
                    String dateReturn = rs.getString("dateReturn");
                    list.add(new HistoryDTO(rentId, total, rentalDate, dateRental, dateReturn));
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

    public static void updateStatus(String rentId, String email) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update tblRental\n"
                        + "set status = 'false'\n"
                        + "where rentId = '" + rentId + "' and email = '" + email + "'";
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

    public static void updateQuantityAfterCancel(int quantityToUpdate, String carId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update tblCars\n"
                        + "set quantity = quantity + '" + quantityToUpdate + "'\n"
                        + "where  carId = " + carId + "";
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
}
