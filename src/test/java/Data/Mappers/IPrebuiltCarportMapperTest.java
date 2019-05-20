/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.PrebuiltCarport;
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
public class IPrebuiltCarportMapperTest {
    
    public IPrebuiltCarportMapperTest() {
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
        IPrebuiltCarportMapper expResult = null;
        IPrebuiltCarportMapper result = IPrebuiltCarportMapper.instance();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetDataSource() {
        System.out.println("setDataSource");
        DataSource ds = null;
        IPrebuiltCarportMapper instance = new IPrebuiltCarportMapperImpl();
        instance.setDataSource(ds);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllPrebuiltCarports() throws Exception {
        System.out.println("getAllPrebuiltCarports");
        IPrebuiltCarportMapper instance = new IPrebuiltCarportMapperImpl();
        List<PrebuiltCarport> expResult = null;
        List<PrebuiltCarport> result = instance.getAllPrebuiltCarports();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public class IPrebuiltCarportMapperImpl extends IPrebuiltCarportMapper {

        public void setDataSource(DataSource ds) {
        }

        public List<PrebuiltCarport> getAllPrebuiltCarports() throws SystemErrorException {
            return null;
        }
    }
    
}
