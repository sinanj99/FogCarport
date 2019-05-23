/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import DB.DataSourceMysqlTest;
import Data.Database.DBConnector;
import Data.Entity.Carport;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.Shed;
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
    public void testInsertRequest() throws SystemErrorException {
        System.out.println("insertRequest");
        IRequestMapper instance = IRequestMapper.instance();
        ShippingAddress sa = new ShippingAddress("Jimmy", "Johnson", "Tagensvej", 2200, "Koebenhavn n");
        Shed s = new Shed(210, 210);
        Carport c = new Carport(instance.getRoof(1), 0, 300, 300, s);
        Request req = new Request(sa, 1, "2019-05-22 18:10:36", c);
        instance.insertRequest(req);
        Request r = instance.getRequest(6);
        
        assertEquals(r.getRequestId(), 6);
        assertEquals(r.getUserId(), 1);
        assertEquals(r.getDatePlaced(), "2019-05-22 18:10:36");
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
        int expResult = 6;
        List<Request> result = instance.getRequests();
        assertEquals(expResult, result.size());
    }

    /*
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
    public void testUpdateRoofPrice() throws SystemErrorException {
        System.out.println("updateRoofPrice");
        int roof_id = 0;
        int price = 0;
        IRequestMapper instance = IRequestMapper.instance();
        instance.updateRoofPrice(roof_id, price);
        fail("The test case is a prototype.");
    }
    */
    @Test
    public void testGetRoof() throws SystemErrorException {
        System.out.println("getRoof");
        IRequestMapper instance = IRequestMapper.instance();

        int id = 6;
        Roof r = instance.getRoof(id);
        assertEquals(r.getRoof_id(), 6);
        assertEquals(r.getName(), "Betonstagsten - Teglrød");
    }

    @Test
    public void testGetRequestShippingAddress() throws SystemErrorException {
        System.out.println("getRequestShippingAddress");
        IRequestMapper instance = IRequestMapper.instance();
        int id = 3;
        ShippingAddress sa = instance.getRequestShippingAddress(id);
        assertEquals(sa.getFirstname(), "Michael");
        assertEquals(sa.getLastname(), "Jackson");
        assertEquals(sa.getAddress(), "Aaboulevarden");
        assertEquals(sa.getZipcode(), 2200);
        assertEquals(sa.getCity(), "Koebenhavn N");
        
    }
}
   