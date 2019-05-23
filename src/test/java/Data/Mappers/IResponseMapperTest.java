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
    
    @Test
    public void testGetResponses() throws SystemErrorException {
        System.out.println("getResponses");
        int userId = 1;
        IResponseMapper instance = IResponseMapper.instance();
        List<Response> list = instance.getResponses(userId);
        
        int expResult = 4;
        int result = 0;
        result = list.size();
        assertEquals(expResult, result);
        
        Response firstResponse = list.get(3);
        assertEquals(1, firstResponse.getRequest().getRequestId());
        assertEquals(3, firstResponse.getSellerId());
        assertEquals("2019-05-22 15:00:00", firstResponse.getDatePlaced());
        assertEquals(40000, firstResponse.getSellPrice());
        assertEquals(0, firstResponse.getStatus());
        
        Response secondResponse = list.get(2);
        assertEquals(2, secondResponse.getRequest().getRequestId());
        assertEquals(3, secondResponse.getSellerId());
        assertEquals("2019-05-22 15:10:00", secondResponse.getDatePlaced());
        assertEquals(40000, secondResponse.getSellPrice());
        assertEquals(0, secondResponse.getStatus());
        
        Response thirdResponse = list.get(1);
        assertEquals(3, thirdResponse.getRequest().getRequestId());
        assertEquals(3, thirdResponse.getSellerId());
        assertEquals("2019-05-22 15:20:00", thirdResponse.getDatePlaced());
        assertEquals(69850, thirdResponse.getSellPrice());
        assertEquals(0, thirdResponse.getStatus());
        
        Response fourthResponse = list.get(0);
        assertEquals(4, fourthResponse.getRequest().getRequestId());
        assertEquals(3, fourthResponse.getSellerId());
        assertEquals("2019-05-22 15:30:00", fourthResponse.getDatePlaced());
        assertEquals(40000, fourthResponse.getSellPrice());
        assertEquals(0, fourthResponse.getStatus());

    }

    @Test
    public void testGetResponse() throws NoSuchResponseException, SystemErrorException {
        System.out.println("getResponse");
        int requestId = 3;
        IResponseMapper instance = IResponseMapper.instance();
        
        Response r = null;
        r = instance.getResponse(requestId);
        assertEquals(3, r.getRequest().getRequestId());
        assertEquals(3, r.getSellerId());
        assertEquals("2019-05-22 15:20:00", r.getDatePlaced());
        assertEquals(69850, r.getSellPrice());
        assertEquals(0, r.getStatus());
    }
    
    @Test (expected = NoSuchResponseException.class)
    public void testGetResponseFail() throws NoSuchResponseException, SystemErrorException{
        System.out.println("getResponseFail");
        int responseId = 124;
        IResponseMapper instance = IResponseMapper.instance();
        instance.getResponse(responseId);
    }
    
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
    
    @Test (expected = NoSuchResponseException.class)
    public void testDeleteResponse() throws NoSuchResponseException, SystemErrorException {
        System.out.println("deleteResponse");
        int responseId = 1;
        IResponseMapper instance = IResponseMapper.instance();
        instance.deleteResponse(responseId);
        instance.getResponse(responseId);
    }

    
}
