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
public class BOMInclineRoof {

    // contstants used in roof-calculations. 
    private static BOMFlatRoof fc = new BOMFlatRoof();
    private static final int TRIANGLE_WIDTH = 360 / 2;
    private static final double INCLINATION = Math.toRadians(20);
    private static final double HYPOTENUSE = TRIANGLE_WIDTH / Math.cos(INCLINATION);
    private static final double TILE_W_X_H = (HYPOTENUSE * 730) / 288;
    private static final int TILE_WIDTH = 730 / 21;

    private static BOMToolshed tbom = new BOMToolshed();

    
    //------------------------------------------ calculation methods for fiiting and screws ----------------------------------------------------------
   
    
    private int amountOfScrewsLathHolders(int carportLength, Shed shed, int shedLength) 
    {
        double amount = amountOfLathHolders(carportLength, shed, shedLength);
        return (int) Math.ceil(amount / 250); // a single pack consists of 250 screws. 
    }
    
    private int amountOfScrewsLaths(int carportWidth, int inclination) 
    {
        double amount = amountOfLaths(carportWidth, inclination) * 10;
        return (int) Math.ceil(amount / 100);
        /*amount of screws laths - unknown
        (random value = 10 screws for each lath.)*/
    }
    
    
    //---------------------------------------- methods for returning item in category fitting and screws --------------------------------------------------
    
    
    public LineItem screwsLathHolders(int carportLength, Shed shed, int shedLength) throws SystemErrorException, NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterialNoLength(7);
        return new LineItem(m, amountOfScrewsLathHolders(carportLength, shed, shedLength), "Til montering af universalbeslag + toplægte", m.getPrice() * amountOfScrewsLathHolders(carportLength, shed, shedLength), Type.NOLENGTH);
    }
     
    public LineItem LathScrews(int carportWidth, int inclination) throws SystemErrorException, NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterialNoLength(8);
        return new LineItem(m, amountOfScrewsLaths(carportWidth, inclination), "til taglægter", m.getPrice() * amountOfScrewsLaths(carportWidth, inclination), Type.NOLENGTH);
    }
    
    
    
    //------------------------------------- calculation methods for tree -------------------------------------------------------
    
    
     public int amountOfLathsDraw(int carportWidth, double inclination) 
    {
//        /*firstly, the roof is divided into 2 right-angled triangles,
//        and the hypotenuse is calculated*/
//        int triangleWidth = carportWidth / 2; // width of each triangle.
//        inclination = Math.toRadians(inclination); //Math.cos expects radians
//        double hypotenuse = triangleWidth / Math.cos(inclination);
//        /*there must be a space of 35 cm between the first and secong lath, and a space
//        of 30 cm between the last lath and the top.*/
//        hypotenuse -= 65;
//        /*the hypotenuse is now used to calculate the amount of laths*/
//        //amount of holes or amount of rafters(excluding back rafter)
//        return fc.calculateQuantityOfSpærIncludedBackSpær((int) carportWidth / 2, 35);
        
        //for at compileren ikke broker sig, bare fjern når metoden virker
        return 2;
    }
    
    public int amountOfLaths(int carportWidth, double inclination) 
    {
//        /*firstly, the roof is divided into 2 right-angled triangles,
//        and the hypotenuse is calculated*/
//        int triangleWidth = carportWidth / 2; // width of each triangle.
//        inclination = Math.toRadians(inclination); //Math.cos expects radians
//        double hypotenuse = triangleWidth / Math.cos(inclination);
//        /*there must be a space of 35 cm between the first and secong lath, and a space
//        of 30 cm between the last lath and the top.*/
////      hypotenuse -= 65;
//        /*the hypotenuse is now used to calculate the amount of laths*/
//        //amount of holes or amount of rafters(excluding back rafter)
//        return fc.calculateQuantityOfSpærIncludedBackSpær((int) hypotenuse, 40) * 2 - 2;

         //for at compileren ikke broker sig, bare fjern når metoden virker
        return 2;
    }
    
     /**
     *
     * @return (toplægte)
     */
    private int amountOfTopLaths() {
        return 1;
    }
    
     /**
     *
     * @param carportWidth
     * @param inclination
     * @return (vindskeder)
     */
    private int amountOfSoffits(int carportWidth, double inclination) 
    {
//        int triangleWidth = carportWidth / 2; // width of each triangle.
//        inclination = Math.toRadians(inclination); //Math.cos expects radians
//        double hypotenuse = triangleWidth / Math.cos(inclination);
//        if (hypotenuse * 2 <= 240) {
//            return 1;
//        } else if (hypotenuse * 2 > 240 && hypotenuse <= 480) {
//            return 2;
//        } else if (hypotenuse * 2 > 480 && hypotenuse <= 720) {
//            return 3;
//        } else if (hypotenuse * 2 > 720 && hypotenuse <= 920) {
//            return 4;
//        } else {
//            return 5; //max width of hypotenuse is 1060.
//        }

        //for at compileren ikke broker sig, bare fjern når metoden virker
        return 2;
    }
    
    
    //--------------------------------------- methods for retuning item in category tree ------------------------------------------------------------
    
    
    public LineItem laths(int cWidth, int inclination, int cLength, int sLength) throws NoSuchMaterialException, SystemErrorException 
    {
        Material m = LogicFacade.getInstance().getMaterialWithLength(7, cLength - sLength + 30);
        return new LineItem(m, amountOfLaths(cWidth, inclination), "til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte", m.getPrice() * amountOfLaths(cWidth, inclination), Type.LENGTH);
    }
     
    public LineItem toplaths(int cLength, int sLength, int cWidth) throws NoSuchMaterialException, SystemErrorException 
    {
        Material m = LogicFacade.getInstance().getMaterialWithLength(7, cLength);
        return new LineItem(m, amountOfTopLaths(), "toplægte til montering af rygsten lægges i toplægte holder", m.getPrice() * amountOfTopLaths(), Type.LENGTH);
    }
    
     /*
    beslagsskruer (vinkelbeslag):
    4 skruer per vinkelbeslag
    (står ikke i styklisten, men i beskrivelsen)
     */
    public LineItem soffits(int carportWidth, int inclination) throws NoSuchMaterialException, SystemErrorException 
    {
        Material m = LogicFacade.getInstance().getMaterialWithLength(1, 480);
        return new LineItem(m, amountOfSoffits(carportWidth, inclination), "Vindskeder på rejsning", m.getPrice() * amountOfSoffits(carportWidth, inclination), Type.LENGTH);
    }
    
    
    //-------------------------------------- calculation methods for roof --------------------------------------------------------------------------
    
    
    private int amountOfLathHolders(int carportLength, Shed shed, int shedLength) 
    {
//        /*amount of lath-holders is the same as rafters; 1 lath-holder for each rafter*/
//        return amountOfRafters(carportLength);

        //for at compileren ikke broker sig, bare fjern når metoden virker
        return 2;
    }
     
     /**
     *
     * @param carportWidth
     * @param carportLength
     * @param inclination
     * @return (tagsten)
     */
    private int amountOfRoofTiles(int carportWidth, int carportLength, double inclination) 
    {

//        /*for a carport with width 360 and length 730, 288 roof tiles are needed.
//
//        firstly, the roof is divided into 2 right-angled triangles,
//        and the hypotenuse is calculated*/
//        int triangleWidth = carportWidth / 2; // width of each triangle.
//        inclination = Math.toRadians(inclination); //Math.cos expects radians
//        double hypotenuse = triangleWidth / Math.cos(inclination);
//
//        /*the answer can be calculated by dividing the square meter measurement of the roof (hypotenuse * carportlength)
//        with the square meter measurement of a single tile. to find the square meter measurement
//        of a single tile the following equation can be solved:
//        
//        (hypotenuse*730) / x = 288
//
//        x = (hypotenuse * 730) / 288;
//        
//        x = 486
//       
//        since the square measurement of a single tile is now known, the total amount of tiles
//        can be calculated.
//         */
//        double roofWxH = Math.ceil((hypotenuse * carportLength));
//        double result = roofWxH / TILE_W_X_H;
//        return (int) result;

        //for at compileren ikke broker sig, bare fjern når metoden virker
        return 2;

    } 
    
     private int amountOfRoofTileBinders() 
     {
        return 2; // 2 packs -- amount in a single pack and how many is needed is unknown. 
    }
    
     /**
     *
     * @param carportLength
     * @return (rygsten)
     */
    private int amountOfRidgeTiles(int carportLength) 
    {
//        /*
//        since the width of the roof and amount of ridge tiles needed is known (21), 
//        the width of a single tile can be calculated.
//         */
//        return carportLength / TILE_WIDTH;

        //for at compileren ikke broker sig, bare fjern når metoden virker
        return 2;
    }
    
     /**
     *
     * @param carportLength
     * @return (rygstensbeslag)
     */
    private int amountOfRidgeTileBrackets(int carportLength) 
    {
//        //same amount as ridge tiles.
//        return amountOfRidgeTiles(carportLength);

        //for at compileren ikke broker sig, bare fjern når metoden virker
        return 2;
    }
    
    
    //-------------------------------------- methods for returning item in category roof ------------------------------------------------------------
    
    
    public LineItem lathHolders(int carportLength, Shed shed, int shedLength) throws SystemErrorException, NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterialNoLength(13);
        return new LineItem(m, amountOfLathHolders(carportLength, shed, shedLength), "monteres på toppen af spæret (til toplægte)", m.getPrice() * amountOfLathHolders(carportLength, shed, shedLength), Type.ROOF);
    }
    
    public LineItem roofTiles(int id, int carportWidth, int carportLength, int inclination) throws NoSuchRoofException 
    {
        Roof r = LogicFacade.getInstance().newGetRoof(id, 37);
        return new LineItem(r, amountOfRoofTiles(carportWidth, carportLength, inclination), "monteres på taglægter", r.getPrice() * amountOfRoofTiles(carportWidth, carportLength, inclination));
    }
    
    public LineItem roofTileBinders() throws SystemErrorException, NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterialNoLength(15);
        return new LineItem(m, amountOfRoofTileBinders(), "til montering af tagsten, alle ydersten + hver anden fastgøres", m.getPrice() * amountOfRoofTileBinders(), Type.ROOF);
    }
    
    public LineItem ridgeTiles(int id, int cLength) 
    {
        Roof r = LogicFacade.getInstance().newGetRoof(id, 6);
        return new LineItem(r, amountOfRidgeTiles(cLength), "monteres på toplægte med medfølgende beslag se tagstens vejledning", r.getPrice() * amountOfRidgeTiles(cLength));
    }
     
    public LineItem ridgeTileBrackets(int cLength) throws SystemErrorException, NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterialNoLength(14);
        return new LineItem(m, amountOfRidgeTileBrackets(cLength), "Til montering af rygsten", m.getPrice() * amountOfRidgeTileBrackets(cLength), Type.ROOF);
    }
    
    
    
    //--------------------------------------------------- other ------------------------------------------------------------------------'
    
     /**
     *
     * @param c
     * @param inclination
     * @return (taglægter)
     */
    public int spaceLength(Carport c, double inclination) 
    {
//        /*firstly, the roof is divided into 2 right-angled triangles,
//        and the hypotenuse is calculated*/
//        int triangleWidth = c.getWidth() / 2; // width of each triangle.
//        inclination = Math.toRadians(inclination); //Math.cos expects radians
//        double hypotenuse = triangleWidth / Math.cos(inclination);
//        /*there must be a space of 35 cm between the first and secong lath, and a space
//        of 30 cm between the last lath and the top.*/
//        hypotenuse -= 65;
//        //amount of holes or amount of rafters(excluding back rafter)
//        int holeQty = (int) hypotenuse / 40;
//        //the total width of all rafters together(excluding back rafter)
//        float totalRafterWidth = holeQty * 3.8f;
//        //the total width of all holes together
//        float totalHoleWidth = (int) hypotenuse - totalRafterWidth;
//        //the space between each rafter
//        float spaceBetweenRafters = totalHoleWidth / holeQty;
//
//        if (spaceBetweenRafters > 40) {
//            holeQty++;
//        }
//        return (int) spaceBetweenRafters;
        
        //for at compileren ikke broker sig, bare fjern når metoden virker
        return 2;
    }
}
