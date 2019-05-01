/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Database.DBConnector;
import Data.Entity.Material;
import Logic.Exceptions.NoSuchMaterialException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sinanjasar
 */
class MaterialMapper extends IMaterialMapper {

    private static MaterialMapper instance = null;
    Connection con = DBConnector.getConnection();

    public synchronized static MaterialMapper getInstance() {
        if (instance == null) {
            instance = new MaterialMapper();
        }
        return instance;
    }
    @Override
    public Material getMaterial_(String name) throws NoSuchMaterialException
    {
        int material_id = 0;
        String name_ = "";
        int length = 0;
        String unit = "";
        int price = 0;
        try {
            String sql = "SELECT * FROM `material` WHERE name = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                material_id = rs.getInt("material_id");
                name_ = rs.getString("name");
                length = rs.getInt("length");
                unit = rs.getString("unit");
                price = rs.getInt("price");
            }
        } catch (SQLException ex) {
            throw new NoSuchMaterialException();
        }
        return new Material(material_id, name, length, unit, price);
    }

    @Override
    public Material getMaterial(String name, int length) throws NoSuchMaterialException {
        int material_id = 0;
        String name_ = "";
        //int length = 0;
        String unit = "";
        int price = 0;
        try {
            String sql = "SELECT * FROM `material` WHERE name = ? and length = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, length);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                material_id = rs.getInt("material_id");
                name_ = rs.getString("name");
                length = rs.getInt("length");
                unit = rs.getString("unit");
                price = rs.getInt("price");
            }
        } catch (SQLException ex) {
            throw new NoSuchMaterialException();
        }
        return new Material(material_id, name, length, unit, price);
    }
    @Override
    public String getMaterial(int id) throws NoSuchMaterialException {
        String name_ = "";
        try {
            String sql = "SELECT name FROM `materials_withlength` WHERE material_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name_ = rs.getString("name");
            }
        } catch (SQLException ex) {
            throw new NoSuchMaterialException();
        }
        return name_;
    }
    

    @Override
    public List<Material> getMaterials() {
        
        List<Material> materials = new ArrayList();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM materials_withlength;");
            while (rs.next()) {
                int material_id = rs.getInt("material_id");
                String name_ = rs.getString("name");
                String unit = rs.getString("unit");

                materials.add(new Material(material_id, name_, unit));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return materials;
    }

    @Override
    public void updatePrice(int price, int id) throws NoSuchMaterialException {
        try {
            String sql = "UPDATE `material` SET price = ? WHERE material_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, price);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new NoSuchMaterialException();
        }
    }
    @Override
    public void insertMaterial(String name, int length, String unit, String desc, int price) {
        try {
            String sql = "INSERT INTO `material`(name, length, unit, description, price) "
                    + "VALUES(?, ?, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, length);
            pstmt.setString(3, unit);
            pstmt.setString(4, desc);
            pstmt.setInt(5, price);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void insertMaterialDim(int id, int length, int price, int stock) {
        try {
            String sql = "INSERT INTO `material_lengths`(material_id, length, price, stock) "
                    + "VALUES(?, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, length);
            pstmt.setInt(3, price);
            pstmt.setInt(4, stock);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public static void main(String[] args){
        try {
            System.out.println(IMaterialMapper.instance().getMaterial(7));
        } catch (NoSuchMaterialException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
