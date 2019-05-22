/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import DB.DataSourceMysqlTest;
import Data.Database.DBConnector;
import Data.Entity.Request;
import Data.Entity.Response;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.SystemErrorException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class IResponseMapperTest {
    
    private static IResponseMapper mapper;
    private static String sqlStatements = "";
    private static final DataSource ds = new DataSourceMysqlTest().getDataSource();
    
    public IResponseMapperTest() {
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

        mapper = IResponseMapper.instance();
        mapper.setDataSource(ds);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testInstance() {
        System.out.println("instance");
        IResponseMapper expResult = null;
        IResponseMapper result = IResponseMapper.instance();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testGetResponses_Size() {
        System.out.println("getResponses_Size");
        int userId = 1;
        IResponseMapper instance = IResponseMapper.instance();
        int expResult = 4;
        int result = 0;
        
        try {
            result = instance.getResponses(userId).size();
        } catch (SystemErrorException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetResponses() {
        System.out.println("getResponses");
        int userId = 0;
        IResponseMapper instance = IResponseMapper.instance();
        List<Response> expResult = null;
        List<Response> result = null;
        try {
            result = instance.getResponses(userId);
        } catch (SystemErrorException ex) {
            Logger.getLogger(IResponseMapperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetResponse_String() {
        System.out.println("getResponse_String");
        int requestId = 3;
        IResponseMapper instance = IResponseMapper.instance();
        String expResult = "3 3 2019-05-22 15:20:00 69850 0";
        String result = "";
        
        Response r = null;
        try {
            r = instance.getResponse(requestId);
            result =    String.valueOf(r.getRequest().getRequestId()) + " " +
                        String.valueOf(r.getSellerId()) + " " +
                        r.getDatePlaced() + " " +
                        String.valueOf(r.getSellPrice()) + " " +
                        String.valueOf(r.getStatus());
        } catch (NoSuchResponseException ex) {
            System.out.println(ex.getMessage());
        } catch (SystemErrorException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(expResult, result);
    }
    //(5, 3, "2019-05-22 15:40:00", 40000, 0);
    @Test
    public void testInsertResponse() throws SystemErrorException, NoSuchResponseException{
        System.out.println("insertResponse");

        IResponseMapper instance = IResponseMapper.instance();
        
        Request request = new Request(5, 1, "2019-05-22 12:40:00", null, null);
        Response r = new Response(request, 3, "2019-05-22 15:40:00", 50505);
        instance.insertResponse(r);
        
        Response result = instance.getResponse(5);
        assertEquals(result.getRequest().getRequestId(), 5);
        assertEquals(result.getSellerId(), 3);
        assertEquals(result.getDatePlaced(), "2019-05-22 15:40:00");
        assertEquals(result.getSellPrice(), 50505);
        assertEquals(result.getStatus(), 0);
    }
    
    /*
    Forventer NoSuchRequestException fordi, at getResponse
    kalder getRequest(RequestMapper), hvis den ingen request finder,
    så betyder det at der heller ingen response eksisterer
    */
    @Test (expected = NoSuchRequestException.class)
    public void testDeleteResponse() throws NoSuchResponseException, SystemErrorException {
        System.out.println("deleteResponse");
        int responseId = 1;
        IResponseMapper instance = IResponseMapper.instance();
        instance.deleteResponse(responseId);
        instance.getResponse(responseId);
    }

    
}
