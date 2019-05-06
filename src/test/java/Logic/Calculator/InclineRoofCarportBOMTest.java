/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.LineItem;
import Data.Entity.Shed;
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
public class InclineRoofCarportBOMTest {
    
    public InclineRoofCarportBOMTest() {
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
    
    @org.junit.Test
    public void testSoffits() {
        System.out.println("soffits");
        int carportWidth = 0;
        int inclination = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.soffits(carportWidth, inclination);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testRafters() {
        System.out.println("rafters");
        int carportWidth = 0;
        int carportLength = 0;
        Shed shed = null;
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.rafters(carportWidth, carportLength, shed, shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testFasciaCarport() {
        System.out.println("fasciaCarport");
        int carportLength = 0;
        Shed shed = null;
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.fasciaCarport(carportLength, shed, shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testFasciaShed() {
        System.out.println("fasciaShed");
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.fasciaShed(shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testRoofTiles() throws Exception {
        System.out.println("roofTiles");
        int id = 0;
        int carportWidth = 0;
        int carportLength = 0;
        int inclination = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.roofTiles(id, carportWidth, carportLength, inclination);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testLaths() {
        System.out.println("laths");
        int cWidth = 0;
        int inclination = 0;
        int cLength = 0;
        int sLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.laths(cWidth, inclination, cLength, sLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testToplaths() {
        System.out.println("toplaths");
        int cLength = 0;
        int sLength = 0;
        int cWidth = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.toplaths(cLength, sLength, cWidth);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testRidgeTiles() {
        System.out.println("ridgeTiles");
        int id = 0;
        int cLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.ridgeTiles(id, cLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testRidgeTileBrackets() {
        System.out.println("ridgeTileBrackets");
        int cLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.ridgeTileBrackets(cLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testLathHolders() {
        System.out.println("lathHolders");
        int carportLength = 0;
        Shed shed = null;
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.lathHolders(carportLength, shed, shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testBeamsCarport() {
        System.out.println("beamsCarport");
        int carportLength = 0;
        Shed shed = null;
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.beamsCarport(carportLength, shed, shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testBeamsShed() {
        System.out.println("beamsShed");
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.beamsShed(shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testIntertiesSides() {
        System.out.println("intertiesSides");
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.intertiesSides(shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testIntertiesGables() {
        System.out.println("intertiesGables");
        int shedWidth = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.intertiesGables(shedWidth);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testRainboards() {
        System.out.println("rainboards");
        int cWidth = 0;
        int inclination = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.rainboards(cWidth, inclination);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testBracketInterties() {
        System.out.println("bracketInterties");
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.bracketInterties();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testScrewsInnerTimbering() {
        System.out.println("screwsInnerTimbering");
        int shedWidth = 0;
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.screwsInnerTimbering(shedWidth, shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testScrewsOuterTimbering() {
        System.out.println("screwsOuterTimbering");
        int shedWidth = 0;
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.screwsOuterTimbering(shedWidth, shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testScrewsLathHolders() {
        System.out.println("screwsLathHolders");
        int carportLength = 0;
        Shed shed = null;
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.screwsLathHolders(carportLength, shed, shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testLeftBracketRafters() {
        System.out.println("leftBracketRafters");
        int carportLength = 0;
        Shed shed = null;
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.leftBracketRafters(carportLength, shed, shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testRigthBracketRafters() {
        System.out.println("rigthBracketRafters");
        int carportLength = 0;
        Shed shed = null;
        int shedLength = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.rigthBracketRafters(carportLength, shed, shedLength);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testFasciaAndSoffitScrews() {
        System.out.println("fasciaAndSoffitScrews");
        int carportLength = 0;
        int carportWidth = 0;
        Shed shed = null;
        int shedLength = 0;
        int inclination = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.fasciaAndSoffitScrews(carportLength, carportWidth, shed, shedLength, inclination);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testLathScrews() {
        System.out.println("LathScrews");
        int carportWidth = 0;
        int inclination = 0;
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.LathScrews(carportWidth, inclination);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSquareDiscs() {
        System.out.println("squareDiscs");
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.squareDiscs();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testRoofTileBinders() {
        System.out.println("roofTileBinders");
        InclineRoofCarportBOM instance = new InclineRoofCarportBOM();
        LineItem expResult = null;
        LineItem result = instance.roofTileBinders();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        InclineRoofCarportBOM.main(args);
        fail("The test case is a prototype.");
    }
    
}
