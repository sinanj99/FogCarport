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
public class BOMFundamentTest {
    private static BOMFundament bom;
    private static Carport carport;
    
    @BeforeClass
    public static void setUpClass() {
        bom = new BOMFundament();
        carport = new Carport(new Roof(1,"roof",false),0,600,780,new Shed(530, 210));
    }

    @Test
    public void calculateQuantityOfBracketScrews(){
        int expected = 2;
        int result = bom.calculateQuantityOfBracketScrews(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfBordsBolts(){
        int expected = 22;
        int result = bom.calculateQuantityOfBordsBolts(carport);
        assertEquals(expected, result);
    } 
    @Test
    public void calculateQuantityOfRafters(){
        int expected = 14;
        /*the result deviates by one digit, 
        since the limit is 0.5 greater that the one in the pdf-example*/
        int result = bom.calculateQuantityOfRafterIncludedBackRafter(carport.getLength(), 60);
        assertEquals(expected, result);
    }
    @Test
    public void calculateQuantityOfRightBracketInteries(){
        int expected = 14;
        int result = bom.calculateQuantityOfRightBracketInteries(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfLeftBracketInteries(){
        int expected = 14;
        int result = bom.calculateQuantityOfRightBracketInteries(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfPost(){
        int expected = 11;
        int result = bom.calculateQuantityOfPost(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfUnderneathBoardForFrontAndBack(){
        int expected = 4;
        int result = bom.calculateQuantityOfUnderneathBoardForFrontAndBack(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfUnderneathBoardForSides(){
        int expected = 4;
        int result = bom.calculateQuantityOfUnderneathBoardForSides(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfOuterBoardForFront(){
        int expected = 2;
        int result = bom.calculateQuantityOfOuterBoardForFront(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfOuterBoardForSides(){
        int expected = 4;
        int result = bom.calculateQuantityOfOuterBoardForSides(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfWaterBoardForFront(){
        int expected = 2;
        int result = bom.calculateQuantityOfWaterBoardForFront(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfWaterBoardForSides(){
        int expected = 4;
        int result = bom.calculateQuantityOfWaterBoardForSides(carport);
        assertEquals(expected, result);
    }
    
    @Test
    public void calculateQuantityOfPerforatedBands(){
        double expected = 2; // 2 rolls consisting of 10m bands each
        double result = bom.calculateQuantityOfPerforatedBands(carport);
        assertEquals(expected, result, 0);
    }
}
