/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Response;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.SystemErrorException;
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
    
    public IResponseMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
    public void testSetDataSource() {
        System.out.println("setDataSource");
        DataSource ds = null;
        IResponseMapper instance = new IResponseMapperImpl();
        instance.setDataSource(ds);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetResponses() {
        System.out.println("getResponses");
        int userId = 0;
        IResponseMapper instance = new IResponseMapperImpl();
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
    public void testGetResponse() {
        System.out.println("getResponse");
        int requestId = 0;
        IResponseMapper instance = new IResponseMapperImpl();
        Response expResult = null;
        Response result = null;
        try {
            result = instance.getResponse(requestId);
        } catch (NoSuchResponseException ex) {
            Logger.getLogger(IResponseMapperTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemErrorException ex) {
            Logger.getLogger(IResponseMapperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testInsertResponse() {
        System.out.println("insertResponse");
        Response res = null;
        IResponseMapper instance = new IResponseMapperImpl();
        try {
            instance.insertResponse(res);
        } catch (SystemErrorException ex) {
            Logger.getLogger(IResponseMapperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteResponse() throws Exception {
        System.out.println("deleteResponse");
        int responseId = 0;
        IResponseMapper instance = new IResponseMapperImpl();
        instance.deleteResponse(responseId);
        fail("The test case is a prototype.");
    }

    public class IResponseMapperImpl extends IResponseMapper {

        public void setDataSource(DataSource ds) {
        }

        public List<Response> getResponses(int userId) {
            return null;
        }

        public Response getResponse(int requestId) {
            return null;
        }

        public void insertResponse(Response res) {
        }

        public void deleteResponse(int responseId) throws NoSuchResponseException {
        }
    }
    
}
