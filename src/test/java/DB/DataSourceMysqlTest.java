/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;

/**
 *
 * @author sinanjasar
 */
public class DataSourceMysqlTest {

    private MysqlDataSource ds = new MysqlDataSource();

    /**
     * Constructs datasource that works with remote dB
     */
    public DataSourceMysqlTest() {

        ds.setServerName("157.230.97.70");
        ds.setPort(3306);
        ds.setDatabaseName("CarportTest");
        ds.setUser("root");
        ds.setPassword("prespa01");
        try {
            ds.setAllowMultiQueries(true);
            ds.setUseSSL(false);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public MysqlDataSource getDataSource() {
        return ds;
    }
}
