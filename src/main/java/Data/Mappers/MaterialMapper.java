/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Database.DBConnector;
import Data.Entity.Material;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.SystemErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
class MaterialMapper extends IMaterialMapper {

    private static MaterialMapper instance = null;
    private final DBConnector connector = new DBConnector();
    private final Connection con = connector.getConnection();

    public synchronized static MaterialMapper getInstance() {
        if (instance == null) {
            instance = new MaterialMapper();
        }
        return instance;
    }

    @Override
    public Material getMaterial_(String name) throws NoSuchMaterialException {
        int material_id = 0;
        String name_ = "";
        int length = 0;
        String unit = "";
        int stock = 0;
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
                stock = rs.getInt("stock");
            }
        } catch (SQLException ex) {
//            throw new NoSuchMaterialException();
        }
        return new Material(material_id, name, length, unit, price, stock);
    }

    @Override
    public Material getMaterial(String name, int length) throws NoSuchMaterialException {
        int material_id = 0;
        String name_ = "";
        //int length = 0;
        String unit = "";
        int stock = 0;
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
                stock = rs.getInt("stock");
            }
        } catch (SQLException ex) {
//            throw new NoSuchMaterialException();
        }
        return new Material(material_id, name, length, unit, price, stock);
    }

    @Override
    public String getMaterial(int id) throws NoSuchMaterialException {
        String name_ = "";
        try {
            String sql = "SELECT name * `materials_withlength` WHERE material_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name_ = rs.getString("name");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return name_;
    }

    @Override
    public Material getMaterialWithLength(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        String name = "";
        String unit = "";
        int price = 0;
        int stock = 0;
        try {
            String sql = "SELECT * FROM `materials_withlength` WHERE material_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
                unit = rs.getString("unit");
            }
            sql = "SELECT * FROM `material_lengths` WHERE material_id = ? AND length = ?;";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, length);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                length = rs.getInt("length");
                price = rs.getInt("price");
                stock = rs.getInt("stock");
            } else {
                throw new NoSuchMaterialException("Material " + id + ", " + length + " could not be found");
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
        return new Material(id, name, length, unit, price, stock);
    }

    /**
     * Fetches material with specified id from material_nolength table in dB
     *
     * @param id of the material wanted
     * @return a desired material
     * @throws SystemErrorException
     */
    @Override
    public Material getMaterialNoLength(int id) throws SystemErrorException, NoSuchMaterialException {
        String name = "";
        String unit = "";
        int length = 0;
        int price = 0;
        int stock = 0;
        try {
            String sql = "SELECT * FROM `materials_nolength` WHERE material_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
                unit = rs.getString("unit");
                price = rs.getInt("price");
                stock = rs.getInt("stock");
            } else {
                throw new NoSuchMaterialException("Material " + id + " could not be found");
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
        return new Material(id, name, unit, price, stock);
    }

    /**
     * Substracts the qty parameter from material (with length) with specified
     * id.
     *
     * @param id of the material to be updated
     * @param qty quantity to substract
     * @throws NoSuchMaterialException if there is no material with the
     * specified id.
     * @throws SystemErrorException if any other database-related error occurs.
     */
    @Override
    public void updateStockWithLength(int id, int length, int qty) throws SystemErrorException, NoSuchMaterialException, IllegalArgumentException {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        } else {
            int stock = 0;
            try {
                String sql = "SELECT stock FROM `material_lengths` WHERE material_id = ? AND length = ?;";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.setInt(2, length);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    stock = rs.getInt("stock");
                    System.out.println(stock);
                } else {
                    throw new NoSuchMaterialException("Material " + id + "not found");
                }
                sql = "UPDATE `material_lengths` SET stock = ? WHERE material_id = ? AND length = ?;";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, stock -= qty);
                pstmt.setInt(2, id);
                pstmt.setInt(3, length);
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                throw new SystemErrorException(ex.getMessage());
            }
        }
    }

    /**
     * Substracts the qty parameter from material (without length) with
     * specified id.
     *
     * @param id of the material to be updated
     * @param qty quantity to substract
     * @throws NoSuchMaterialException if there is no material with the
     * specified id.
     * @throws SystemErrorException if any other database-related exception
     * occurs.
     */
    @Override
    public void updateStockNoLength(int id, int qty) throws SystemErrorException, NoSuchMaterialException, IllegalArgumentException {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        int stock = 0;
        try {
            String sql = "SELECT stock FROM `materials_nolength` WHERE material_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                stock = rs.getInt("stock");
                System.out.println("stock = " + stock);
            } else {
                throw new NoSuchMaterialException("Material with id + " + id + " could not be found");
            }
            sql = "UPDATE `materials_nolength` SET stock = ? WHERE material_id = ?;";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, stock -= qty);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SystemErrorException(ex.getMessage());
        }
    }

    @Override
    public void updatePrice(int price, int id) throws SystemErrorException {
        try {
            String sql = "UPDATE `material` SET price = ? WHERE material_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, price);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SystemErrorException(ex.getMessage());
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
    public void insertMaterialDim(int id, int length, int price, int stock) throws SystemErrorException {
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
            throw new SystemErrorException(ex.getMessage());
        }
    }

    public static void main(String[] args) throws SystemErrorException {
        try {
            System.out.println(IMaterialMapper.instance().getMaterialWithLength(1, 120));
        } catch (NoSuchMaterialException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Material> getMaterials() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
