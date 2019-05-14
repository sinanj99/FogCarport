/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.SystemErrorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
public class DBConnector {

    private DataSource ds;
    private static Connection conn;

    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    public Connection getConnection() {
        if (conn == null) {
            try {
                conn = ds.getConnection();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }

//    public Connection open() throws SQLException {
//        if (conn == null || conn.isClosed()) {
//            conn = ds.getConnection();
//        }
//        return conn;
//    }

//    public void close() {
//        try {
//            if (conn != null || !conn.isClosed()) {
//
//                conn.close();
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

//    public static Connection getTestConnection() {
//        URL = "jdbc:mysql://157.230.97.70:3306/CarportDBTest";
//        try {
//            Class.forName(DRIVER);
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return conn;
//    }
    
    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public PreparedStatement preparedStatement(String sql, int indicator) throws SQLException {
        return conn.prepareStatement(sql, indicator);
    }
}
