/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.Carport;
import Data.Entity.LineItem;
import Data.Entity.Material;
import Data.Entity.Roof;
import Data.Entity.Shed;
import Data.Entity.Type;

import Presentation.Exceptions.NoSuchMaterialException;

import Logic.Controller.LogicFacade;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;

/**
 *
 * @author Sinan Jasar
 */
public class BOMRoofPackage {
    
    private static final BOMFundament fc = new BOMFundament();

    /**
     * Calculates the hypotenuse for the roof
     * @param c the carport
     * @return the hypotenuse for the roof
     */
   public double calculateHypotenuseForRoof(Carport c)
    {
        // calcaute the hypotenuse for the roof
        int triangleWidth = c.getWidth() / 2; // width of each triangle.
        double inclination = Math.toRadians(c.getInclination()); //Math.cos expects radians
        double hypotenuse = triangleWidth / Math.cos(inclination);
        return hypotenuse;
    }
   
    //------------------------------------------ calculation methods for fiiting and screws ----------------------------------------------------------
    /**
     * Calculates the amount of screws needed for lath holders
     * @param c the carport
     * @return amount of screws for lath holders (skruer for lægte holder)
     */
    private int amountOfScrewsLathHolders(Carport c) 
    {
        //one screw for each lath Holder
        double amount = amountOfLathHolders(c);
        return (int) Math.ceil(amount / 250); // a single pack consists of 250 screws. 
    }
    
   /**
    * Calculates the amount of screws needed for the laths
    * @param c
    * @return amount of screws for laths
    */
    private int amountOfScrewsLaths(Carport c) 
    {
        double amount = amountOfLaths(c) * 10;
        return (int) Math.ceil(amount / 100);
        /*amount of screws laths - unknown
        (random value = 10 screws for each lath.)*/
    }
    
    
    
    
    //---------------------------------------- methods for returning item in category fitting and screws --------------------------------------------------
    /**
     * Returns line item object of screws for lath holders
     * @param c the carport
     * @return LineItem object of screws for lath holders
     * @throws SystemErrorException if an sql-exception is thrown
     * @throws NoSuchMaterialException if no materials with specified id exist
     */
    public LineItem screwsLathHolders(Carport c) throws SystemErrorException, NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getFitting(7);
        return new LineItem(m, amountOfScrewsLathHolders(c), "Til montering af universalbeslag + toplægte", m.getPrice() * amountOfScrewsLathHolders(c), Type.NOLENGTH);
    }
    /**
     * Returns LineItem object of screws for laths
     * @param c the carport
     * @return LineItem object of screws for laths
     * @throws SystemErrorException if an sql-exception is thrown
     * @throws NoSuchMaterialException if no materials with specified id exist 
     */
    public LineItem LathScrews(Carport c) throws SystemErrorException, NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getFitting(8);
        return new LineItem(m, amountOfScrewsLaths(c), "til taglægter", m.getPrice() * amountOfScrewsLaths(c), Type.NOLENGTH);
    }
    
    
    
    //------------------------------------- calculation methods for tree -------------------------------------------------------
    
    /**
     * Calculates the amount of laths needed
     * @param c the carport
     * @return amount of alths needed
     */
    public int amountOfLaths(Carport c) 
    {
//        /*firstly, the roof is divided into 2 right-angled triangles,
//        and the hypotenuse is calculated*/

//        /*the hypotenuse is now used to calculate the amount of laths*/
         
        return fc.calculateQuantityOfRafterIncludedBackRafter((int) calculateHypotenuseForRoof(c), 40);
    }
    
    
    
    /**
     * Calculates the amount of soffits needed
     * @param c the carport
     * @return the amount of soffits (vindskeder)
     */ 
    private int amountOfSoffits(Carport c) 
    {
        int triangleWidth = c.getWidth() / 2; // width of each triangle.
        double inclination = Math.toRadians(c.getInclination()); //Math.cos expects radians
        double hypotenuse = triangleWidth / Math.cos(inclination);
        if (hypotenuse * 2 <= 240) {
            return 1;
        } else if (hypotenuse * 2 > 240 && hypotenuse <= 480) {
            return 2;
        } else if (hypotenuse * 2 > 480 && hypotenuse <= 720) {
            return 3;
        } else if (hypotenuse * 2 > 720 && hypotenuse <= 920) {
            return 4;
        } else {
            return 5; //max width of hypotenuse is 1060.
        }
        
    }
    
    
    //--------------------------------------- methods for retuning item in category tree ------------------------------------------------------------
    
    /**
     * Returns a LineItem object of laths
     * @param c the carport
     * @return a LineItem object of laths
     * @throws SystemErrorException if an sql-exception is thrown
     * @throws NoSuchMaterialException if no materials with specified id exist 
     */
    public LineItem laths(Carport c) throws NoSuchMaterialException, SystemErrorException 
    {
        Material m = LogicFacade.getInstance().getWoodMaterial(10, c.getLength() + 30);
        return new LineItem(m, amountOfLaths(c)*2-2, "til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte", m.getPrice() * amountOfLaths(c)*2-2, Type.LENGTH);
    }
    /**
     * Returns a LineItem object of top laths
     * @param c the carport
     * @return a LineItem object of top laths
     * @throws SystemErrorException if an sql-exception is thrown
     * @throws NoSuchMaterialException if no materials with specified id exist 
     */
    public LineItem toplaths(Carport c) throws NoSuchMaterialException, SystemErrorException 
    {
        Material m = LogicFacade.getInstance().getWoodMaterial(10, c.getLength());
        return new LineItem(m, 1, "toplægte til montering af rygsten lægges i toplægte holder", m.getPrice() * 1, Type.LENGTH);
    }
    
    /**
     * Returns a LineItem object of soffits
     * @param c the carport
     * @return a LineItem object of soffits
     * @throws SystemErrorException if an sql-exception is thrown
     * @throws NoSuchMaterialException if no materials with specified id exist 
     */
    public LineItem soffits(Carport c) throws NoSuchMaterialException, SystemErrorException 
    {
        Material m = LogicFacade.getInstance().getWoodMaterial(2, 480);
        return new LineItem(m, amountOfSoffits(c), "Vindskeder på rejsning", m.getPrice() * amountOfSoffits(c), Type.LENGTH);
    }
    
    
    //-------------------------------------- calculation methods for roof --------------------------------------------------------------------------
    
    /**
     * Calculates the amount of lath holders needed
     * @param c the carport
     * @return amount of lath holders (holder for lægte)
     */
    private int amountOfLathHolders(Carport c) 
    {
        /*amount of lath-holders is the same as rafters; 1 lath-holder for each rafter*/
        BOMFundament f = new BOMFundament();
        return f.calculateQuantityOfRafterIncludedBackRafter(c.getLength(), 90);
    }
     
     /**
     * Calculates the amount of roof tiles needed
     * @param c the carport
     * @return the amount of roof tiles (tagsten)
     */
    private int amountOfRoofTiles(Carport c) 
    {
      /* the following lines calculate the square meter measurements for a single tile,
         based on the given example */
       int triangleWidth = 360 / 2;
       double inclination = Math.toRadians(20);
       double hypotenuse_ = triangleWidth / Math.cos(inclination);
       double TILE_W_X_H = (hypotenuse_ * 730) / 288;
       
       
       double hypotenuse = calculateHypotenuseForRoof(c);
       
        /*for a carport with width 360 and length 730, 288 roof tiles are needed.

        firstly, the roof is divided into 2 right-angled triangles,
        and the hypotenuse is calculated*/
        

        /*the answer can be calculated by dividing the square meter measurement of the roof (hypotenuse * carportlength)
        with the square meter measurement of a single tile. to find the square meter measurement
        of a single tile the following equation can be solved:
        
        (hypotenuse*730) / x = 288

        x = (hypotenuse * 730) / 288;
        
        x = 486
       
        since the square measurement of a single tile is now known, the total amount of tiles
        can be calculated.
         */
        double roofWxH = Math.ceil((hypotenuse * c.getLength()));
        double result = roofWxH / TILE_W_X_H;
        return (int) result;

    } 
    
     /**
     * Calculates the amount of ridge tiles
     * @param carportLength
     * @return (rygsten)
     */
    private int amountOfRidgeTiles(Carport c) 
    {
        /*
        since the width of the roof and amount of ridge tiles needed is known (21), 
        the width of a single tile can be calculated.
         */
        int tileWidth = 37;
        return c.getLength() / tileWidth;
    }
    
     /**
     * Calculates the amount of ridge tile brackets
     * @param carportLength - length of carport
     * @return amount of ridge tile brackets (rygstensbeslag)
     */
    private int amountOfRidgeTileBrackets(Carport c) 
    {
        //same amount as ridge tiles.
        return amountOfRidgeTiles(c);

    }
    
    
    //-------------------------------------- methods for returning item in category roof ------------------------------------------------------------
    
    /**
     * Returns a LineItem object of lath holders
     * @param c the carport
     * @return a LineItem object of lath holders
     * @throws SystemErrorException if an sql-exception is thrown
     * @throws NoSuchMaterialException if no materials with specified id exist 
     */
    public LineItem lathHolders(Carport c) throws SystemErrorException, NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getFitting(13);
        return new LineItem(m, amountOfLathHolders(c), "monteres på toppen af spæret (til toplægte)", m.getPrice() * amountOfLathHolders(c), Type.ROOF);
    }
    
    /**
     * Returns a LineItem object of laths
     * @param c the carport
     * @return a LineItem object of laths
     * @throws Presentation.Exceptions.NoSuchRoofException if a roof with the specified id is not found
     */
    public LineItem roofTiles(Carport c) throws NoSuchRoofException, SystemErrorException 
    {
        Roof r = LogicFacade.getInstance().newGetRoof(c.getRoof().getRoof_id(), 37);
        return new LineItem(r, amountOfRoofTiles(c), "monteres på taglægter", r.getPrice() * amountOfRoofTiles(c));
    }
    /**
     * Returns a LineItem object of laths
     * @return a LineItem object of laths
     * @throws NoSuchMaterialException if no materials with specified id can be found
     * @throws SystemErrorException an sql-exception is thrown
     */
    public LineItem roofTileBinders() throws SystemErrorException, NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getFitting(15);
        return new LineItem(m, 2, "til montering af tagsten, alle ydersten + hver anden fastgøres", m.getPrice() * 2, Type.ROOF);
    }
    /**
     * Returns a LineItem object of ridge tiles
     * @param c the carport
     * @return a LineItem object of ridge tiles
     */
    public LineItem ridgeTiles(Carport c) throws SystemErrorException 
    {
        Roof r = LogicFacade.getInstance().newGetRoof(c.getRoof().getRoof_id(), 6);
        return new LineItem(r, amountOfRidgeTiles(c), "monteres på toplægte med medfølgende beslag se tagstens vejledning", r.getPrice() * amountOfRidgeTiles(c));
    }
    /**
     * Returns a LineItem object of laths
     * @param c the carport
     * @return a LineItem object of laths
     * @throws NoSuchMaterialException if no materials with specified id can be found
     * @throws SystemErrorException an sql-exception is thrown
     */
    public LineItem ridgeTileBrackets(Carport c) throws SystemErrorException, NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getFitting(14);
        return new LineItem(m, amountOfRidgeTileBrackets(c), "Til montering af rygsten", m.getPrice() * amountOfRidgeTileBrackets(c), Type.ROOF);
    }
    
}
