/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Logic;

import Data.Entity.Carport;
import Data.Entity.Roof;
import Data.Entity.Shed;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The expected results and carport object in this class are based on the extradited 
 * example of a bill of materials on an inclined roof carport.
 * @author sinanjasar
 */
public class BOMRoofPackageTest {
    private static BOMRoofPackage bom;
    private static Carport carport;
    
    @BeforeClass
    public static void setUpClass() {
        bom = new BOMRoofPackage();
        carport = new Carport(new Roof(1,"roof",true),20,360,730,new Shed(360,220));
    }
    @Test
    public void amountOfSoffits() {
        int expResult = 2;
        int result = bom.amountOfSoffits(carport);
        assertEquals(expResult, result);
    }
    @Test
    public void amountOfLathHolders() {
        int expResult = 8;
        int result = bom.amountOfLathHolders(carport);
        assertEquals(expResult, result);
    }
    @Test
    public void amountOfScrewsLathHolders() {
        int expResult = 1;
        int result = bom.amountOfScrewsLathHolders(carport);
        assertEquals(expResult, result);
    }
    @Test
    public void amountOfRidgeTiles() {
        int expResult = 21;
        int result = bom.amountOfRidgeTiles(carport);
        assertEquals(expResult, result);
    }
    @Test
    public void amountOfRoofTiles() {
        int expResult = 288;
        int result = bom.amountOfRoofTiles(carport);
        assertEquals(expResult, result);
    }
    @Test
    public void amountOfRidgeTileBrackets() {
        int expResult = 21;
        int result = bom.amountOfRidgeTileBrackets(carport);
        assertEquals(expResult, result);
    }
}