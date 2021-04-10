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
import longnpt.dtos.DemiseDetailDTO;
import longnpt.utils.DBHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class DemiseDetailDAO {
    private static final Logger LOGGER = Logger.getLogger(DemiseDetailDAO.class);

    public static List<DemiseDetailDTO> getDemiseOfRent(String rentId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<DemiseDetailDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.getConnection();
            if (cn != null) {
                String sql = "select idDemise,d.carId,c.name,d.quantity,totalPrice,rentId\n"
                        + "from tblDemise d join tblCars c\n"
                        + "on d.carId = c.carId\n"
                        + "where rentId = '" + rentId + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String idDemise = rs.getString("idDemise");
                    String carId = rs.getString("carId");
                    String carName = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float totalPrice = rs.getFloat("totalPrice");
                    list.add(new DemiseDetailDTO(idDemise, carId, carName, quantity, totalPrice, rentId));
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
