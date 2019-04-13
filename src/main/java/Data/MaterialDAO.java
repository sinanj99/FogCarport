/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasper Jeppesen
 */
public class MaterialDAO
{
    public static Material getMaterial(String materialName)
    {
        Material m = null;
        int id = 0;
        String name = null;
        int length = 0;
        String unit = null;
        String description = null;
        int price = 0;
        
        try
        {
            Connection connection = DB.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM material WHERE name = '" + materialName + "'");
            
            while(rs.next())
            {
                id=rs.getInt("material_id");
                name = rs.getString("name");
                length = rs.getInt("length");
                unit = rs.getString("unit");
                description = rs.getString("description");
                price = rs.getInt("price");
            }
                    
        }
        catch (SQLException ex)
        {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        m = new Material(id, name, length, unit, description, price);
        
        return m;
    }
    
     public static void main(String[] args) throws Exception
     {
         Material m = MaterialDAO.getMaterial("45x195mm spærtræ. ubh. 2m");
        System.out.println(m.getId());
        System.out.println(m.getMaterialName());
        System.out.println(m.getLength());
        System.out.println(m.getUnit());
        System.out.println(m.getDescription());
        System.out.println(m.getPrice());
     }
}
