/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import DB.DataSourceMysqlTest;
import Data.Database.DBConnector;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.ShippingAddress;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
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
public class IRequestMapperTest {
    
    private static IRequestMapper mapper;
    private static String sqlStatements = "";
    private static final DataSource ds = new DataSourceMysqlTest().getDataSource();
    
    public IRequestMapperTest() {
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

        mapper = IRequestMapper.instance();
        mapper.setDataSource(ds);
    }

    @Test
    public void testGetRequest() throws Exception, SystemErrorException, NoSuchRequestException, NoSuchShedException {
        System.out.println("getRequest");
        int id = 2;
        IRequestMapper instance = IRequestMapper.instance();
        //(1, "2019-05-22 12:10:00"),
        Request r = instance.getRequest(id);
        assertEquals(r.getRequestId(), 2);
        assertEquals(r.getUserId(), 1);
        assertEquals(r.getDatePlaced(), "2019-05-22 12:10:00");
    }

    @Test
    public void testGetRequests() throws Exception, SystemErrorException, NoSuchShedException {
        System.out.println("getRequests");
        IRequestMapper instance = IRequestMapper.instance();
        List<Request> expResult = null;
        List<Request> result = instance.getRequests();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testInsertRequest() throws Exception {
        System.out.println("insertRequest");
        Request req = null;
        IRequestMapper instance = IRequestMapper.instance();
        instance.insertRequest(req);
        fail("The test case is a prototype.");
    }

    @Test
    public void testInsertDimensions() throws Exception {
        System.out.println("insertDimensions");
        int id = 0;
        int length = 0;
        int price = 0;
        IRequestMapper instance = IRequestMapper.instance();
        //instance.insertDimensions(id, length, price);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDimensionPrice() throws Exception {
        System.out.println("getDimensionPrice");
        int roof_id = 0;
        int length = 0;
        IRequestMapper instance = IRequestMapper.instance();
        int expResult = 0;
        int result = instance.getDimensionPrice(roof_id, length);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRoof_int() throws Exception {
        System.out.println("getRoof");
        int id = 0;
        IRequestMapper instance = IRequestMapper.instance();
        Roof expResult = null;
        Roof result = instance.getRoof(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRoof_int_int() throws Exception {
        System.out.println("getRoof");
        int id = 0;
        int length = 0;
        IRequestMapper instance = IRequestMapper.instance();
        Roof expResult = null;
        Roof result = instance.getRoof(id, length);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRoofs_0args() throws Exception {
        System.out.println("getRoofs");
        IRequestMapper instance = IRequestMapper.instance();
        List<Roof> expResult = null;
        List<Roof> result = instance.getRoofs();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRoofs_int() throws Exception {
        System.out.println("getRoofs");
        int rooftype = 0;
        IRequestMapper instance = IRequestMapper.instance();
        List<Roof> expResult = null;
        List<Roof> result = instance.getRoofs(rooftype);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateRoofPrice() throws Exception {
        System.out.println("updateRoofPrice");
        int roof_id = 0;
        int price = 0;
        IRequestMapper instance = IRequestMapper.instance();
        instance.updateRoofPrice(roof_id, price);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRequestShippingAddress() throws Exception {
        System.out.println("getRequestShippingAddress");
        int id = 0;
        IRequestMapper instance = IRequestMapper.instance();
        ShippingAddress expResult = null;
        ShippingAddress result = instance.getRequestShippingAddress(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
   