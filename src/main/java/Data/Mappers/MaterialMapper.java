/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Controller.DataFacade;
import Data.Database.DBConnector;
import Data.Database.DataSourceMysql;
import Data.Entity.Material;
import Data.Entity.Roof;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.InvalidInputException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
class MaterialMapper extends IMaterialMapper {

    private static MaterialMapper instance = null;
    private final DBConnector con = new DBConnector();
    Connection conn;

    @Override
    public void setDataSource(DataSource ds) {
        con.setDataSource(ds);
        conn = con.getConnection();
    }

    public synchronized static MaterialMapper getInstance() {
        if (instance == null) {
            instance = new MaterialMapper();
        }
        return instance;
    }

    @Override
    public Material getWoodMaterial(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        String name = "";
        String unit = "";
        int price = 0;
        int stock = 0;
        try {
            String sql = "SELECT * FROM `wood_materials` INNER JOIN material_lengths USING(material_id)  WHERE material_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
                unit = rs.getString("unit");
                length = rs.getInt("length");
                price = rs.getInt("price");
                stock = rs.getInt("stock");
            } else {
                throw new NoSuchMaterialException(id);
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
        return new Material(id, name, length, unit, price, stock);
    }

    
    @Override
    public Material getFitting(int id) throws SystemErrorException, NoSuchMaterialException {
        String name = "";
        String unit = "";
        int length = 0;
        int price = 0;
        int stock = 0;
        try {
            String sql = "SELECT * FROM `fittings_and_screws` WHERE fitting_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
                unit = rs.getString("unit");
                price = rs.getInt("price");
                stock = rs.getInt("stock");
            } else {
                throw new NoSuchMaterialException(id);
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
        return new Material(id, name, unit, price, stock);
    }
    @Override
    public void updateStockWithLength(int id, int length, int qty) throws SystemErrorException, NoSuchMaterialException, IllegalArgumentException {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        } else {
            int stock = 0;
            try {
                String sql = "SELECT stock FROM `material_lengths` WHERE material_id = ? AND length = ?;";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.setInt(2, length);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    stock = rs.getInt("stock");
                    System.out.println(stock);
                } else {
                    throw new NoSuchMaterialException(id);
                }
                sql = "UPDATE `material_lengths` SET stock = ? WHERE material_id = ? AND length = ?;";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, stock -= qty);
                pstmt.setInt(2, id);
                pstmt.setInt(3, length);
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                throw new SystemErrorException(ex.getMessage());
            }
        }
    }
    @Override
    public void updateStockNoLength(int id, int qty) throws SystemErrorException, NoSuchMaterialException, IllegalArgumentException {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        int stock = 0;
        try {
            String sql = "SELECT stock FROM `fittings_and_screws` WHERE material_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                stock = rs.getInt("stock");
                System.out.println("stock = " + stock);
            } else {
                throw new NoSuchMaterialException(id);
            }
            sql = "UPDATE `fittings_and_screws` SET stock = ? WHERE material_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stock -= qty);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SystemErrorException(ex.getMessage());
        }
    }

    @Override
    public void updatePriceWithLength(int price, int id) throws SystemErrorException, 
            NoSuchMaterialException, InvalidInputException {
        LinkedHashMap<Integer, Integer> prices = getMaterialLengthPrices(id);
        try {
            String sql = "SELECT * FROM material_lengths WHERE material_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int length;
                prices = DataFacade.getInstance().updatePrices(price, prices);
                for (Map.Entry<Integer, Integer> entry : prices.entrySet()) {
                    price = entry.getValue();
                    length = entry.getKey();
                    sql = "UPDATE material_lengths SET price = ? WHERE material_id = ? AND length = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, price);
                    pstmt.setInt(2, id);
                    pstmt.setInt(3, length);
                    pstmt.executeUpdate();
                }
            } else {
                throw new NoSuchMaterialException(id);
                
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }

    }


    @Override
    public void insertMaterialDim(int id, int length, int price, int stock) throws SystemErrorException {
        try {
            String sql = "INSERT INTO `material_lengths`(material_id, length, price, stock) "
                    + "VALUES(?, ?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, length);
            pstmt.setInt(3, price);
            pstmt.setInt(4, stock);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SystemErrorException(ex.getMessage());
        }
    }
    @Override
    public LinkedHashMap<Integer, Integer> getMaterialLengthPrices(int id) throws SystemErrorException {

        LinkedHashMap<Integer, Integer> prices = new LinkedHashMap();
        String sql = "SELECT * FROM material_lengths WHERE material_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                prices.put(rs.getInt("length"), rs.getInt("price"));
            }
        } catch (SQLException ex) {
            throw new SystemErrorException(ex.getMessage());
        }
        return prices;
    }
    @Override
    public LinkedHashMap<Integer, Integer> getRoofLengthPrices(int id) throws SystemErrorException {

        LinkedHashMap<Integer, Integer> prices = new LinkedHashMap();
        String sql = "SELECT * FROM roof_lengths WHERE roof_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                prices.put(rs.getInt("length"), rs.getInt("price"));
            }
        } catch (SQLException ex) {
            throw new SystemErrorException(ex.getMessage());
        }
        return prices;
    }

    @Override
    public void updatePriceFittings(int price, int id) throws SystemErrorException, NoSuchMaterialException {
        String sql = "SELECT * FROM fittings_and_screws WHERE fitting_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                sql = "UPDATE fittings_and_screws SET price = ? WHERE fitting_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, price);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            } else {
                throw new NoSuchMaterialException(id);
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public void updatePriceRoof(int price, int id) throws SystemErrorException, NoSuchRoofException, InvalidInputException {
        LinkedHashMap<Integer, Integer> prices = getRoofLengthPrices(id);
        try {
            String sql = "SELECT * FROM roof_lengths WHERE roof_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int length;
                prices = DataFacade.getInstance().updatePrices(price, prices);
                for (Map.Entry<Integer, Integer> entry : prices.entrySet()) {
                    price = entry.getValue();
                    length = entry.getKey();
                    sql = "UPDATE roof_lengths SET price = ? WHERE roof_id = ? AND length = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, price);
                    pstmt.setInt(2, id);
                    pstmt.setInt(3, length);
                    pstmt.executeUpdate();
                }
            } else {
                throw new NoSuchRoofException(id);
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public List<Material> getMaterials() throws SystemErrorException {
        List<Material> materials = new ArrayList();
        int id, length, price, stock;
        String name, unit;
        try {
            String sql = "WITH materials AS"
                    + " (SELECT *, ROW_NUMBER() OVER (PARTITION BY material_id ORDER BY price ASC) AS rn"
                    + " FROM material_lengths)"
                    + " SELECT *"
                    + " FROM materials INNER JOIN wood_materials USING(material_id)"
                    + " WHERE rn = 1;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("material_id");
                name = rs.getString("name");
                unit = rs.getString("unit");
                length = rs.getInt("length");
                price = rs.getInt("price");
                stock = rs.getInt("stock");
                materials.add(new Material(id, name, length, unit, price, stock));
            }
            sql = "SELECT * FROM fittings_and_screws;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("fitting_id");
                name = rs.getString("name");
                unit = rs.getString("unit");
                price = rs.getInt("price");
                stock = rs.getInt("stock");
                materials.add(new Material(id, name, unit, price, stock));
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
        return materials;
    }
    @Override
    public List<Roof> getRoofs() throws SystemErrorException {
        List<Roof> roofs = new ArrayList();
        int id, price, inclined, length;
        String name;
        boolean inclined_ = false;
        try {
            String sql = "WITH roof AS"
                    + " (SELECT *, ROW_NUMBER() OVER (PARTITION BY roof_id ORDER BY price ASC) AS rn"
                    + " FROM roof_lengths)"
                    + " SELECT *"
                    + " FROM roof INNER JOIN roofs USING(roof_id)"
                    + " WHERE rn = 1;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("roof_id");
                name = rs.getString("name");
                inclined = rs.getInt("inclined");
                length = rs.getInt("length"); 
                if(inclined == 1){
                    inclined_ = true;
                }
                price = rs.getInt("price");
                roofs.add(new Roof(id, name, price, inclined_, length));
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
        return roofs;
    }
}
