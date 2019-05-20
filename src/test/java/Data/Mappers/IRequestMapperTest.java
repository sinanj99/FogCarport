/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.ShippingAddress;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
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
    
    public IRequestMapperTest() {
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
        IRequestMapper expResult = null;
        IRequestMapper result = IRequestMapper.instance();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetDataSource() {
        System.out.println("setDataSource");
        DataSource ds = null;
        IRequestMapper instance = new IRequestMapperImpl();
        instance.setDataSource(ds);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRequest() throws Exception {
        System.out.println("getRequest");
        int id = 0;
        IRequestMapper instance = new IRequestMapperImpl();
        Request expResult = null;
        Request result = instance.getRequest(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRequests() throws Exception {
        System.out.println("getRequests");
        IRequestMapper instance = new IRequestMapperImpl();
        List<Request> expResult = null;
        List<Request> result = instance.getRequests();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testInsertRequest() throws Exception {
        System.out.println("insertRequest");
        Request req = null;
        IRequestMapper instance = new IRequestMapperImpl();
        instance.insertRequest(req);
        fail("The test case is a prototype.");
    }

    @Test
    public void testInsertDimensions() throws Exception {
        System.out.println("insertDimensions");
        int id = 0;
        int length = 0;
        int price = 0;
        IRequestMapper instance = new IRequestMapperImpl();
        instance.insertDimensions(id, length, price);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDimensionPrice() throws Exception {
        System.out.println("getDimensionPrice");
        int roof_id = 0;
        int length = 0;
        IRequestMapper instance = new IRequestMapperImpl();
        int expResult = 0;
        int result = instance.getDimensionPrice(roof_id, length);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRoof_int() throws Exception {
        System.out.println("getRoof");
        int id = 0;
        IRequestMapper instance = new IRequestMapperImpl();
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
        IRequestMapper instance = new IRequestMapperImpl();
        Roof expResult = null;
        Roof result = instance.getRoof(id, length);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRoofs_0args() throws Exception {
        System.out.println("getRoofs");
        IRequestMapper instance = new IRequestMapperImpl();
        List<Roof> expResult = null;
        List<Roof> result = instance.getRoofs();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRoofs_int() throws Exception {
        System.out.println("getRoofs");
        int rooftype = 0;
        IRequestMapper instance = new IRequestMapperImpl();
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
        IRequestMapper instance = new IRequestMapperImpl();
        instance.updateRoofPrice(roof_id, price);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRequestShippingAddress() throws Exception {
        System.out.println("getRequestShippingAddress");
        int id = 0;
        IRequestMapper instance = new IRequestMapperImpl();
        ShippingAddress expResult = null;
        ShippingAddress result = instance.getRequestShippingAddress(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public class IRequestMapperImpl extends IRequestMapper {

        public void setDataSource(DataSource ds) {
        }

        public Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException {
            return null;
        }

        public List<Request> getRequests() throws SystemErrorException, NoSuchShedException {
            return null;
        }

        public void insertRequest(Request req) throws SystemErrorException {
        }

        public void insertDimensions(int id, int length, int price) throws SystemErrorException {
        }

        public int getDimensionPrice(int roof_id, int length) throws SystemErrorException {
            return 0;
        }

        public Roof getRoof(int id) throws NoSuchRoofException, SystemErrorException {
            return null;
        }

        public Roof getRoof(int id, int length) throws SystemErrorException {
            return null;
        }

        public List<Roof> getRoofs() throws SystemErrorException {
            return null;
        }

        public List<Roof> getRoofs(int rooftype) throws SystemErrorException {
            return null;
        }

        public void updateRoofPrice(int roof_id, int price) throws SystemErrorException {
        }

        public ShippingAddress getRequestShippingAddress(int id) throws SystemErrorException {
            return null;
        }
    }
    
}
