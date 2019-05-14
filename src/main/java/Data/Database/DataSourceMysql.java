/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import Logic.Exceptions.SystemErrorException;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sinanjasar
 */
public class DataSourceMysql {

    private MysqlDataSource ds = new MysqlDataSource();

    /**
     * Constructs datasource that works with remote dB
     */
    public DataSourceMysql() {
        ds.setServerName("157.230.97.70");
        ds.setPort(3306);
        ds.setDatabaseName("CarportDB");
        ds.setUser("root");
        ds.setPassword("prespa01");
        try {
            ds.setUseSSL(false);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public MysqlDataSource getDataSource() {
        return ds;
    }
}
