                                                                                         /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import Presentation.Exceptions.SystemErrorException;
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
        /*
        Kasper, Sinan og jeg kører på lokale databaser.
        Hvis du ikke har lyst til det, så erstat koden nedenunder med det her:
        
        ds.setServerName("157.230.97.70");
        ds.setPort(3306);
        ds.setDatabaseName("fogcarport");
        ds.setUser("root");
        ds.setPassword("prespa01");
        */
        ds.setServerName("localhost");
        ds.setPort(3308);
        ds.setDatabaseName("fogcarport");
        ds.setUser("root");
        ds.setPassword("12qwaszx");
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
