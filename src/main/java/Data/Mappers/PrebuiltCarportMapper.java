/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Database.DBConnector;
import Data.Entity.PrebuiltCarport;
import Presentation.Exceptions.NoSuchPrebuiltCarportException;
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
public class PrebuiltCarportMapper extends IPrebuiltCarportMapper
{
    private static PrebuiltCarportMapper instance = null;
    //Connection con = DBConnector.getConnection();
    private final DBConnector con = new DBConnector();
    Connection conn;
    
    @Override
    public void setDataSource(DataSource ds)
    {
        con.setDataSource(ds);
        conn = con.getConnection();
    }
    
    public synchronized static PrebuiltCarportMapper getInstance()
    {
        if(instance==null)
        {
            instance = new PrebuiltCarportMapper();
        }
        return instance;
    }

    @Override
    public List<PrebuiltCarport> getAllPrebuiltCarports() throws NoSuchPrebuiltCarportException 
    {
        ArrayList prebuiltCarports = new ArrayList();
        
        int id = 0;
        String imgPath = "";
        int carportWidth = 0;
        int carportLength = 0;
        boolean shed = false;
        int shedWidth = 0;
        int shedLength = 0;
        int price = 0;
        
        try {
            String sql = "SELECT * FROM `prebuilt_carport` ;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) 
            {
                id = rs.getInt("id");
                imgPath = rs.getString("img_path");
                carportWidth = rs.getInt("carport_width");
                carportLength = rs.getInt("carport_length");
                shed = rs.getBoolean("shed");
                shedWidth = rs.getInt("shed_width");
                shedLength = rs.getInt("shed_length");
                price = rs.getInt("price");
                
                prebuiltCarports.add(new PrebuiltCarport(id, imgPath, carportWidth, carportLength, shed, shedWidth, shedLength, price));
                
            }
        } 
        catch (SQLException ex) 
        {
            throw new NoSuchPrebuiltCarportException(ex.getMessage());
        }
        
        return prebuiltCarports;
    }
    
    
    public static void main(String[] args) {
        try 
        {
            System.out.println(IPrebuiltCarportMapper.instance().getAllPrebuiltCarports());
        } 
        catch (NoSuchPrebuiltCarportException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
}
