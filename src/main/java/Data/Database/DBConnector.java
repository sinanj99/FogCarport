/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import Logic.Exceptions.NoSuchMaterialException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sinanjasar
 */
public class DBConnector {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://157.230.97.70:3306/CarportDB";
    private static final String USER = "root";
    private static final String PASSWORD = "prespa01";
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }
    
    public static void close() {
        try {
            if (conn != null || !conn.isClosed()) {

                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public static void main(String[] args) throws NoSuchMaterialException {
//        Test connection
//        try {
//            Connection con = DBConnector.getConnection();
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM material;");
//            while (rs.next()) {
//                System.out.println(rs.getString("name"));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

}
