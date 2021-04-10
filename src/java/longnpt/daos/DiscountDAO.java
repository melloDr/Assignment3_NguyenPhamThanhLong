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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class DiscountDAO {
    private static final Logger LOGGER = Logger.getLogger(DiscountDAO.class);

    public static int getDiscountPercent(String discountCode) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int percent = 0;
        String now = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select discountPercent\n"
                        + "from tblDiscount\n"
                        + "where discountCode = '" + discountCode + "' and deadline >= '" + now + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    percent = rs.getInt("discountPercent");
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
        return percent;
    }

    public static String getDiscountId(String discountCode) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String discountId = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select discountId\n"
                        + "from tblDiscount\n"
                        + "where discountCode = '" + discountCode + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    discountId = rs.getString("discountId");
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
        return discountId;
    }

    public static void setDiscountUsed(String discountCode) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update tblDiscount\n"
                        + "set discountPercent = '0'\n"
                        + "where discountCode = '" + discountCode + "'";
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
