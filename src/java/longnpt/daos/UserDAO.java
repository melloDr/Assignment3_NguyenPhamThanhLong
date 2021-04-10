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
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    public static String checkLogin(String id, String pass) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String name = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select name\n"
                        + "from tblUser\n"
                        + "where email = '" + id + "' and password = '" + pass + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    name = rs.getString("name");
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
        return name;
    }

    public static String getCode(String id) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String code = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select code\n"
                        + "from tblUser\n"
                        + "where email = '" + id + "';";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    code = rs.getString("code");
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
        return code;
    }

    public static String getName(String mail) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String name = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select name\n"
                        + "from tblUser\n"
                        + "where email = '" + mail + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    name = rs.getString("name");
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
        return name;
    }

    public static String getStatus(String id) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String status = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select status\n"
                        + "from tblUser\n"
                        + "where email = '" + id + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    status = rs.getString("status");
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
        return status;
    }

    public static void addUser(String email, String pass, String phone, String name, String address, String date, String status, int code) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "insert tblUser(email,password,phone,name,address,createDate,status,code)\n"
                        + "values('" + email + "','" + pass + "','" + phone + "','" + name + "','" + address + "','" + date + "','" + status + "','" + code + "')";
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

    public static void updateStatus(String id) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "update tblUser \n"
                        + "set status = 'active'\n"
                        + "where email = '" + id + "'";
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

    public static String checkName(String email) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String name = "";
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select name\n"
                        + "from tblUser\n"
                        + "where email = '" + email + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    name = rs.getString("name");
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
        return name;
    }
}
