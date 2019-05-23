/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import DB.DataSourceMysqlTest;
import Data.Database.DBConnector;
import Data.Entity.PrebuiltCarport;
import Presentation.Exceptions.SystemErrorException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sinanjasar
 */
public class IPrebuiltCarportMapperTest {
    
    private static IPrebuiltCarportMapper mapper;
    private static String sqlStatements = "";
    private static final DataSource ds = new DataSourceMysqlTest().getDataSource();
    
    public IPrebuiltCarportMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("MYSQL Tests...");

        BufferedReader sqlScript;
        try {
            //indlæser scriptet
            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("CarportTestScript.sql"), "UTF-8"));
            System.out.println("test");

            String sqlStatement;
            //henter alle sql statements fra scriptet
            while ((sqlStatement = sqlScript.readLine()) != null) {
                sqlStatements += sqlStatement + "\n";
            }
            System.out.println(sqlStatements);

            //henter DBCOnnector, ændrer URL til testDB
            DBConnector con = new DBConnector();
            //udfører alle statements (genstarter db-data)
            con.setDataSource(new DataSourceMysqlTest().getDataSource());
            con.getConnection();
            con.preparedStatement(sqlStatements).executeUpdate();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        mapper = IPrebuiltCarportMapper.instance();
        mapper.setDataSource(ds);
    }
    
    @Test
    public void testGetAllPrebuiltCarports() throws SystemErrorException {
        System.out.println("getAllPrebuiltCarports");
        IPrebuiltCarportMapper instance = IPrebuiltCarportMapper.instance();
        List<PrebuiltCarport> list  = instance.getAllPrebuiltCarports();
        int expResult = 11;
        int result = list.size();
        assertEquals(expResult, result);
    }
}
