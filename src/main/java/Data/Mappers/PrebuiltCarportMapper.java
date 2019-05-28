/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Database.DBConnector;
import Data.Entity.PrebuiltCarport;
import Data.Entity.Shed;
import Presentation.Exceptions.NoSuchPrebuiltCarportException;
import Presentation.Exceptions.SystemErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Kasper Jeppesen
 */
public class PrebuiltCarportMapper extends IPrebuiltCarportMapper {

    private static PrebuiltCarportMapper instance = null;
    private final DBConnector con = new DBConnector();
    Connection conn;

    @Override
    public void setDataSource(DataSource ds) {
        con.setDataSource(ds);
        conn = con.getConnection();
    }

    public synchronized static PrebuiltCarportMapper getInstance() {
        if (instance == null) {
            instance = new PrebuiltCarportMapper();
        }
        return instance;
    }

    @Override
    public List<PrebuiltCarport> getAllPrebuiltCarports() throws SystemErrorException {
        ArrayList<PrebuiltCarport> prebuiltCarports = new ArrayList();

        int id = 0;
        String imgPath = "";
        int carportWidth = 0;
        int carportLength = 0;
        int shedWidth = 0;
        int shedLength = 0;
        int price = 0;
        Shed shed = null;

        try {
            String sql = "SELECT * FROM `prebuilt_carports` LEFT JOIN prebuilt_sheds USING(prebuilt_carport_id);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("prebuilt_carport_id");
                imgPath = rs.getString("img_path");
                carportWidth = rs.getInt("carport_width");
                carportLength = rs.getInt("carport_length");
                shedWidth = rs.getInt("shed_width");
                shedLength = rs.getInt("shed_length");
                price = rs.getInt("price");

                if (shedWidth != 0) {
                    prebuiltCarports.add(new PrebuiltCarport(id, imgPath, carportWidth, carportLength, new Shed(shedWidth, shedLength), price));
                } else {
                    prebuiltCarports.add(new PrebuiltCarport(id, imgPath, carportWidth, carportLength, null, price));
                }

            }
        } catch (SQLException ex) {
            throw new SystemErrorException(ex.getMessage());
        }
        for (PrebuiltCarport c : prebuiltCarports) {
            System.out.println(c);
        }
        return prebuiltCarports;
    }
}
