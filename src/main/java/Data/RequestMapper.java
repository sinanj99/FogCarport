/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.NoSuchMaterialException;
import Logic.NoSuchRoofException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Obaydah Mohamad
 */
class RoofMapper extends IRoofMapper{
    
    private static RoofMapper instance = null;
    Connection con = DBConnector.getConnection();

    public synchronized static RoofMapper getInstance() {
        if (instance == null) {
            instance = new RoofMapper();
        }
        return instance;
    }

    @Override
    public Roof getRoof(String name) throws NoSuchRoofException {
        int roof_id = 0;
        String name_ = "";
        int price = 0;
        boolean inclined = false;
        
        try{
            String query = "SELECT * FROM rooftype WHERE name = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                roof_id = rs.getInt("roof_id");
                name_ = rs.getString("name");
                price = rs.getInt("price");
                if(rs.getInt("inclined") == 1) inclined = true;
            }
        }catch(SQLException e){
            throw new NoSuchRoofException();
        }
        
        return new Roof(roof_id, name_, price, inclined);
    }

    @Override
    public List<Roof> getRoofs() throws NoSuchRoofException {
        List<Roof> roofs = new ArrayList<Roof>();
        int roof_id = 0;
        String name = "";
        int price = 0;
        boolean inclined = false;
        
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rooftype");
            
            while(rs.next()){
                roof_id = rs.getInt("roof_id");
                name = rs.getString("name");
                price = rs.getInt("price");
                if(rs.getInt("inclined") == 1) inclined = true;
                roofs.add(new Roof(roof_id, name, price, inclined));
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new NoSuchRoofException();
        }
        
        return roofs;
    }

    @Override
    public void updatePrice(int roof_id, int price) throws NoSuchRoofException {
        try{
            String query = "UPDATE `rooftype` SET price = ? WHERE roof_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(2, roof_id);
            pstmt.setInt(1, price);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new NoSuchRoofException();
        }
    }
}
