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
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The expected results and carport object in this class are based on the extradited 
 * example of a bill of materials on an inclined roof carport.
 * @author sinanjasar
 */
public class BOMToolshedTest {
    private static BOMToolshed bom;
    private static Carport carport;
    
    @BeforeClass
    public static void setUpClass() {
        bom = new BOMToolshed();
        carport = new Carport(new Roof(1,"roof",false),0,600,780,new Shed(530, 210));
    }
    @Test 
    public void shedClothingTest() {
        int expMin = 190;
        int expMax = 200;
        int result = bom.calculateQuantityForShedClothing(carport);
        assertTrue(expMin <= result && result <= expMax);
    }
}