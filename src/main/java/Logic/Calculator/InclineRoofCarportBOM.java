/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.LineItem;
import Data.Entity.Material;
import Data.Entity.Roof;
import Data.Entity.Shed;
import Data.Entity.Type;
import Logic.Controller.Manager;
import Logic.Exceptions.NoSuchRoofException;

/**
 *
 * @author Sinan Jasar
 */
public class InclineRoofCarportBOM {

    //static contstants used in roof-calculations. 
    private static final int TRIANGLE_WIDTH = 360 / 2;
    private static final double INCLINATION = Math.toRadians(20);
    private static final double HYPOTENUSE = TRIANGLE_WIDTH / Math.cos(INCLINATION);
    private static final double TILE_W_X_H = (HYPOTENUSE * 730) / 288;
    private static final int TILE_WIDTH = 730 / 21;

    private static ToolShedBOM tbom = new ToolShedBOM();

    /**
     *
     * @param carportWidth
     * @param inclination
     * @return (vindskeder)
     */
    public static int amountOfSoffits(int carportWidth, double inclination) {
        int triangleWidth = carportWidth / 2; // width of each triangle.
        inclination = Math.toRadians(inclination); //Math.cos expects radians
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

    /**
     *
     * @param carportLength
     * @param shed
     * @param shedLength
     * @return (spær)
     */
    public static int amountOfRafters(int carportLength, Shed shed, int shedLength) {
        //the laths extend beyond the first/last rafter by 30 cm.
        carportLength -= 30;
        int shedRafters = 0;

        //if there is a shed the amount of rafters must be calculated seperately,
        //since the space limits are different. 
        if (shed!=null) {
            carportLength -= shedLength;
            shedRafters = amountOfRaftersShed(shedLength);
        }
        //the minimum quantity of rafters will always be 3.
        double rafterQty = 3;

        float widthOfSpær = 4.5f;

        //this is the first rafter which gets added between the front rafter and back rafter.
        int lastAdded = 1;

        //there is a space between the middle rafter and front rafter, and a space between the middle and back rafter.
        int spaceAmount = 2;
        double spaceBetweenSpær = (carportLength - (widthOfSpær * rafterQty)) / spaceAmount;

        //105 is the limit of how much space is allowed between the rafters
        while (spaceBetweenSpær >= 105) {
            //number of added rafters get doubled everty time
            lastAdded *= 2;
            rafterQty += lastAdded;
            //as a result the amount of spaces between rafters also gets doubled every time
            spaceAmount *= 2;

            spaceBetweenSpær = (carportLength - widthOfSpær * rafterQty) / spaceAmount;
        }
        return (int) Math.ceil((rafterQty + shedRafters));
    }
    
    public static int amountOfRaftersShed(int shedLength) {

        int quantity = 3;
        float widthOfSpær = 4.5f;

        // this is the first rafter which gets added between the front rafter and back rafter
        int lastAdded = 1;

        // there is a space between the middle rafter and front rafter, and a space between the middle and back rafter.
        int spaceAmount = 2;
        float spaceBetweenSpær = (shedLength - (widthOfSpær * quantity)) / spaceAmount;

        // 105 is the limit of how much space is allowed between the rafters
        while (spaceBetweenSpær >= 105) {
            //number of added rafters get doubled everty time
            lastAdded *= 2;
            quantity += lastAdded;
            // as a result the amount of spaces between rafters also gets doubled every time
            spaceAmount *= 2;
            spaceBetweenSpær = (shedLength - (widthOfSpær * quantity)) / spaceAmount;
        }
       
        return quantity;
    }

    /**
     *
     * @param carportWidth
     * @param inclination
     * @return (taglægter)
     */
    public static int amountOfLaths(int carportWidth, double inclination) {
        /*firstly, the roof is divided into 2 right-angled triangles,
        and the hypotenuse is calculated*/
        int triangleWidth = carportWidth / 2; // width of each triangle.
        inclination = Math.toRadians(inclination); //Math.cos expects radians
        double hypotenuse = triangleWidth / Math.cos(inclination);
        /*there must be a space of 35 cm between the first and secong lath, and a space
        of 30 cm between the last lath and the top.*/
        hypotenuse -= 65;
        /*the hypotenuse is now used to calculate the amount of laths*/
        float lathWidth = 3.8f;
        //minimum qty of rafters is 3. 
        int lathQty = 3;
        //minimum qty of added rafter is 1. 
        int lastAdded = 1;
        //minimum amount of spaces is 2.
        int spaceAmount = 2;
        float spaceBetweenLaths = ((float) hypotenuse - lathWidth * lathQty) / spaceAmount;
        // 50 is the limit of how much space is allowed between the rafters
        while (spaceBetweenLaths > 50) {
            //amount of rafters added is doubled each time. 
            lastAdded *= 2;
            lathQty += lastAdded;
            //amount of spaces added is doubled each time as well. 
            spaceAmount *= 2;
            //the space is updated.
            spaceBetweenLaths = ((float) hypotenuse - lathWidth * lathQty) / spaceAmount;
        }
         //times 2 since it is only calculated for 1 side
         //times 2 since the lath length is half of carport length
        return lathQty * 2 * 2;
    }

    public static int amountOfLathHolders(int carportLength, Shed shed, int shedLength) {
        /*amount of lath-holders is the same as rafters; 1 lath-holder for each rafter*/
        return amountOfRafters(carportLength, shed, shedLength);
    }

    /**
     *
     * @param carportWidth
     * @param carportLength
     * @param inclination
     * @return (tagsten)
     */
    public static int amountOfRoofTiles(int carportWidth, int carportLength, double inclination) {

        /*for a carport with width 360 and length 730, 288 roof tiles are needed.

        firstly, the roof is divided into 2 right-angled triangles,
        and the hypotenuse is calculated*/
        int triangleWidth = carportWidth / 2; // width of each triangle.
        inclination = Math.toRadians(inclination); //Math.cos expects radians
        double hypotenuse = triangleWidth / Math.cos(inclination);

        /*the answer can be calculated by dividing the square meter measurement of the roof (hypotenuse * carportlength)
        with the square meter measurement of a single tile. to find the square meter measurement
        of a single tile the following equation can be solved:
        
        (hypotenuse*730) / x = 288

        x = (hypotenuse * 730) / 288;
        
        x = 486
       
        since the square measurement of a single tile is now known, the total amount of tiles
        can be calculated.
         */
        double roofWxH = Math.ceil((hypotenuse * carportLength));
        double result = roofWxH / TILE_W_X_H;
        return (int) result;

    }

    /**
     *
     * @param carportLength
     * @return (rygsten)
     */
    public static int amountOfRidgeTiles(int carportLength) {
        /*
        since the width of the roof and amount of ridge tiles needed is known (21), 
        the width of a single tile can be calculated.
         */
        return carportLength / TILE_WIDTH;
    }

    /**
     *
     * @param carportLength
     * @return (rygstensbeslag)
     */
    public static int amountOfRidgeTileBrackets(int carportLength) {
        //same amount as ridge tiles.
        return amountOfRidgeTiles(carportLength);
    }

    /**
     *
     * @param carportLength
     * @param shed
     * @param shedLength
     * @return (sternbrædder)
     */
    public static int amountOfFasciaBoardsCarport(int carportLength, Shed shed, int shedLength) {
        /*
        (carport minus shed)
         */
        int length = carportLength;
        if (shed!=null) {
            length -= shedLength;
        }
        // check if 1 is enough.
        if (length <= 600) {
            return 2;
        } else {
            /*3 will be more than enough since max length is 780. 780 * 2 is
            780*2 is 1560 and 600 * 3 1800*/
            return 3;
        }
    }

    /**
     *
     * @param shedLength
     * @return (sternbrædder - skur)
     */
    public static int amountOfFasciaBoardsShed(int shedLength) {

        if (shedLength <= 540 / 2) {
            return 1; // 1 board of 540cm will be enough for both sides. 
        } else if (shedLength > 540 / 2 && shedLength <= 540) {
            /*
            else if carport is between 270 and 540, 2 will be needed.
             */
            return 2;

        } else {
            /*
            else, 3 will be more than enough. 690 which is max length of shed * 2
            is 1380. three boards equals 1620.
             */
            return 3;
        }
    }

    /**
     *
     * @param carportLength
     * @param shed
     * @param shedLength
     * @return (remme - carportdel)
     */
    public static int amountOfBeams(int carportLength, Shed shed, int shedLength) {
        return 2;
    }

    /**
     *
     * @param shedLength
     * @return (rem (skur))
     */
    public static int amountOfBeamsShed(int shedLength) {
        int totalLengthNeeded = shedLength * 2;
        if (totalLengthNeeded <= 480) {
            return 1; // 1 board of 540cm will be enough for both sides. 
        } else if (totalLengthNeeded > 480 && totalLengthNeeded <= 480 * 2) {

            return 2;

        } else {

            return 3;
        }
    }

    /**
     *
     * @return (toplægte)
     */
    public static int amountOfTopLaths() {
        return 2;
    }

    /**
     *
     * @param carportWidth
     * @param inclination
     * @return (vandbræt)
     */
    public static int amountOfRainBoards(int carportWidth, double inclination) {
        return amountOfSoffits(carportWidth, inclination);
    }

    /**
     *
     * @return (løsholter, gavl)
     */
    public static int amountOfIntertiesGable() {
        return 6;
    }

    /**
     *
     * @return (løsholter, sider)
     */
    public static int amountOfIntertiesSides() {
        return 4;
    }

    /**
     *
     * @return (vinkelbeslag til løsholter)
     */
    public static int amountOfBracketsInterties() {
        /*
        2 brackets per intertie.
         */
        return (amountOfIntertiesGable() + amountOfIntertiesSides()) * 2;
    }

    /**
     *
     * @return (beslagsskruer til vinkelbeslag (løsholter) (står ikke i stykliste))
     */
    public static int amountOfBracketScrewsInterties() {
        return amountOfBracketsInterties() * 4; // 4 screws per bracket
    }

    /**
     *
     * @param shedWidth
     * @param shedLength
     * @return (beslagsskruer for beklædning inderst)
     */
    public static int amountOfBracketScrewsTimbering1(int shedWidth, int shedLength) {
        double amount = tbom.calculateQuantityForBeklædning1(shedWidth, shedLength) * 3;
        return (int) Math.ceil(amount/350);// a single pack consists of 350 screws. 
        /*
        inner : 3 4,5x70 mm. screws for each.
         */
    }
    /**
     * 
     * @param shedWidth
     * @param shedLength
     * @return (beslagsskruer for bekædning yderst)
     */
    public static int amountOfBracketScrewsTimbering2(int shedWidth, int shedLength) {
        double amount = tbom.calculateQuantityForBeklædning2(shedWidth, shedLength) * 6;
        return (int) Math.ceil(amount/200); // a single pack consists of 200 screws. 
        /*
        outer : 6 4,5x70 mm. screws for each.
         */
    }

    public static int amountOfScrewsLathHolders(int carportLength, Shed shed, int shedLength) {
        double amount = amountOfLathHolders(carportLength, shed, shedLength);
        return (int) Math.ceil(amount/250); // a single pack consists of 250 screws. 
        
        
        /*
            
    bracket screws for lath holders :
    1 screw per holder
         */
    }
    /**
     * 
     * @param carportLength
     * @param shed
     * @param shedLength
     * @return (højrebeslag til spær)
     */
    public static int amountOfLeftBracketRafters(int carportLength, Shed shed, int shedLength) {
        return amountOfRafters(carportLength, shed, shedLength);
    }
    /**
     * 
     * @param carportLength
     * @param shed
     * @param shedLength
     * @return (venstrebeslag spær)
     */
    public static int amountOfRightBracketRafters(int carportLength, Shed shed, int shedLength) {
        return amountOfRafters(carportLength, shed, shedLength);
    }
    /**
     * 
     * @param carportLength
     * @param shed
     * @param shedLength
     * @return (skruer til spærbeslag)
     */
    public static int amountOfScrewsRafterBrackets(int carportLength, Shed shed, int shedLength) {
        double amount = amountOfRightBracketRafters(carportLength, shed, shedLength)
                * 3 + amountOfLeftBracketRafters(carportLength, shed, shedLength) * 3;
        return (int) Math.ceil(amount/250);
    }
    /**
     * 
     * @param carportLength
     * @param carportWidth
     * @param shed
     * @param shedLength
     * @param inclination
     * @return (skruer til sternbræt og vindskeder)
     */
    public static int amountOfScrewsFasciaAndSoffits(int carportLength, int carportWidth,
            Shed shed, int shedLength, int inclination) {
        double amount = amountOfRafters(carportLength, shed, shedLength) * 2 /* for each rafter */ * 2 /*2 sides*/
                + amountOfLaths(carportWidth, inclination) * 2 /* for each lath*/ * 2 /*2 sides*/;
        return (int) Math.ceil(amount/200);
        /*
        screws (fascia)
        2 4,5 x 60 mm screws for each rafter. * 2 (2 sider)

        screws (soffit)
        2 4,5 x 60 mm screws for each lath-end.
        */
    }
    /**
     * 
     * @param carportWidth
     * @param inclination
     * @return (antal skruer til lægter (står ikke i beskrivelsen))
     */
    public static int amountOfScrewsLaths(int carportWidth, int inclination) {
        double amount = amountOfLaths(carportWidth, inclination) * 10;
        return (int) Math.ceil(amount/100);
        /*amount of screws laths - unknown
        (random value = 10 screws for each lath.)*/
    }
    
    /**
     * 
     * @return (firkantskiver)
     */
    public static int amountOfSquareDiscs() {
        return 20; 
    }
    
    public static int amountOfRoofTileBinders() {
        return 2; // 2 packs -- amount in a single pack and how many is needed is unknown. 
    }
    /*
    beslagsskruer (vinkelbeslag):
    4 skruer per vinkelbeslag
    (står ikke i styklisten, men i beskrivelsen)
    */
    
    public LineItem soffits(int carportWidth, int inclination) {
        Material m = Manager.getMaterialWithLength(1, 480);
        return new LineItem(m, amountOfSoffits(carportWidth, inclination), "Vindskeder på rejsning", m.getPrice() * amountOfSoffits(carportWidth, inclination), Type.LENGTH);
    }
    public LineItem rafters(int carportWidth, int carportLength, Shed shed, int shedLength) {
        Material m = Manager.getMaterialWithLength(3, carportWidth);
        return new LineItem(m, amountOfRafters(carportLength, shed, shedLength), "Spær, monteres på rem", m.getPrice() * amountOfRafters(carportLength, shed, shedLength), Type.LENGTH);
    }
    public LineItem fasciaCarport(int carportLength, Shed shed, int shedLength) {
        Material m = Manager.getMaterialWithLength(1, 600);
        return new LineItem(m, amountOfFasciaBoardsCarport(carportLength, shed, shedLength), "Sternbrædder til siderne Carport del", m.getPrice() * amountOfFasciaBoardsCarport(carportLength, shed, shedLength), Type.LENGTH);
    }
    public LineItem fasciaShed(int shedLength) {
        Material m = Manager.getMaterialWithLength(1, 540);
        return new LineItem(m, amountOfFasciaBoardsShed(shedLength), "Sternbrædder til siderne Skur del ( deles )", m.getPrice() * amountOfFasciaBoardsShed(shedLength), Type.LENGTH);
    }
    public LineItem roofTiles(int id, int carportWidth, int carportLength, int inclination) throws NoSuchRoofException {
        Roof r = Manager.newGetRoof(id, 37);
        return new LineItem(r, amountOfRoofTiles(carportWidth, carportLength, inclination), "monteres på taglægter", r.getPrice() *  amountOfRoofTiles(carportWidth, carportLength, inclination));
    }
    public LineItem laths(int cWidth, int inclination, int cLength, int sLength) {
        Material m = Manager.getMaterialWithLength(7, cLength-sLength+30);
        return new LineItem(m, amountOfLaths(cWidth, inclination), "til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte", m.getPrice() * amountOfLaths(cWidth, inclination), Type.ROOF);
    }
    public LineItem toplaths(int cLength, int sLength, int cWidth) {
        Material m = Manager.getMaterialWithLength(7, cLength-sLength-90);
        return new LineItem(m, amountOfTopLaths(), "toplægte til montering af rygsten lægges i toplægte holder", m.getPrice() * amountOfTopLaths(), Type.ROOF);
 
    }
    public LineItem ridgeTiles(int id, int cLength) {
        Roof r = Manager.newGetRoof(id, 6);
        return new LineItem(r, amountOfRidgeTiles(cLength), "monteres på toplægte med medfølgende beslag se tagstens vejledning", r.getPrice() * amountOfRidgeTiles(cLength));
    }
    public LineItem ridgeTileBrackets(int cLength) {
        Material m = Manager.getMaterialNoLength(14);
        return new LineItem(m, amountOfRidgeTileBrackets(cLength), "Til montering af rygsten", m.getPrice() * amountOfRidgeTileBrackets(cLength), Type.ROOF);
    }
    public LineItem lathHolders(int carportLength, Shed shed, int shedLength) {
        Material m = Manager.getMaterialNoLength(13);
        return new LineItem(m, amountOfLathHolders(carportLength, shed, shedLength), "monteres på toppen af spæret (til toplægte)", m.getPrice() * amountOfLathHolders(carportLength, shed, shedLength), Type.ROOF);
    }
    public LineItem beamsCarport(int carportLength, Shed shed, int shedLength) {
        Material m = Manager.getMaterialWithLength(3, carportLength-shedLength-30);
        return new LineItem(m, amountOfBeams(carportLength, shed, shedLength), "Remme i sider, sadles ned i stolper Carport del", m.getPrice() * amountOfLathHolders(carportLength, shed, shedLength), Type.LENGTH);
    }
    public LineItem beamsShed(int shedLength) {
        Material m = Manager.getMaterialWithLength(3, 480);
        return new LineItem(m, amountOfBeamsShed(shedLength), "Remme i sider, sadles ned i stolper Skur del", m.getPrice() * amountOfBeamsShed(shedLength), Type.LENGTH);
    }
    public LineItem intertiesSides(int shedLength) {
        Material m = Manager.getMaterialWithLength(4, shedLength);
        return new LineItem(m, amountOfIntertiesSides(), "Løsholter i siderne af skur", m.getPrice() * amountOfIntertiesSides(), Type.LENGTH);
    }
    public LineItem intertiesGables(int shedWidth) {
        Material m = Manager.getMaterialWithLength(4, shedWidth);
        return new LineItem(m, amountOfIntertiesGable(), "Løsholter i gavle af skur", m.getPrice() * amountOfIntertiesGable(), Type.LENGTH);
    }
    public LineItem rainboards(int cWidth, int inclination) {
        Material m = Manager.getMaterialWithLength(5, 480);
        return new LineItem(m, amountOfRainBoards(cWidth, inclination), "Vand bræt på vindskeder", m.getPrice() * amountOfRainBoards(cWidth, inclination), Type.LENGTH);
    }
    public LineItem bracketInterties() {
        Material m = Manager.getMaterialNoLength(5);
        return new LineItem(m, amountOfBracketsInterties(), "til montering af løsholter", m.getPrice() * amountOfBracketsInterties(), Type.NOLENGTH);
    }
    public LineItem screwsInnerTimbering(int shedWidth, int shedLength) {
        Material m = Manager.getMaterialNoLength(12);
        return new LineItem(m, amountOfBracketScrewsTimbering1(shedWidth, shedLength), "til montering af inderste bræt ved beklædning", m.getPrice() * amountOfBracketScrewsTimbering1(shedWidth, shedLength), Type.NOLENGTH);
    }
    public LineItem screwsOuterTimbering(int shedWidth, int shedLength) {
        Material m = Manager.getMaterialNoLength(11);
        return new LineItem(m, amountOfBracketScrewsTimbering2(shedWidth, shedLength), "til montering af yderste bræt ved beklædning", m.getPrice() * amountOfBracketScrewsTimbering2(shedWidth, shedLength), Type.NOLENGTH);
    }
    public LineItem screwsLathHolders(int carportLength, Shed shed, int shedLength) {
        Material m = Manager.getMaterialNoLength(7);
        return new LineItem(m, amountOfScrewsLathHolders(carportLength, shed, shedLength), "Til montering af universalbeslag + toplægte", m.getPrice() * amountOfScrewsLathHolders(carportLength, shed, shedLength),Type.NOLENGTH);
    }
    public LineItem leftBracketRafters(int carportLength, Shed shed, int shedLength) {
        Material m = Manager.getMaterialNoLength(2);
        return new LineItem(m, amountOfLeftBracketRafters(carportLength, shed, shedLength), "Til montering af spær på rem", m.getPrice() * amountOfLeftBracketRafters(carportLength, shed, shedLength),Type.NOLENGTH);
    }
    public LineItem rigthBracketRafters(int carportLength, Shed shed, int shedLength) {
        Material m = Manager.getMaterialNoLength(1);
        return new LineItem(m, amountOfRightBracketRafters(carportLength, shed, shedLength), "Til montering af spær på rem", m.getPrice() * amountOfRightBracketRafters(carportLength, shed, shedLength),Type.NOLENGTH);
    }

    public LineItem fasciaAndSoffitScrews(int carportLength, int carportWidth, 
            Shed shed, int shedLength, int inclination) {
        Material m = Manager.getMaterialNoLength(6);
        return new LineItem(m, amountOfScrewsFasciaAndSoffits(carportLength, carportWidth,
        shed, shedLength, inclination), "Til montering af Stern, vindskeder, vindkryds & vand bræt", m.getPrice() * amountOfScrewsFasciaAndSoffits(carportLength, carportWidth,
        shed, shedLength, inclination),Type.NOLENGTH);
    }
    public LineItem LathScrews(int carportWidth, int inclination) {
        Material m = Manager.getMaterialNoLength(8);
        return new LineItem(m, amountOfScrewsLaths(carportWidth, inclination), "til taglægter", m.getPrice() * amountOfScrewsLaths(carportWidth, inclination),Type.NOLENGTH);
    }
    public LineItem squareDiscs() {
        Material m = Manager.getMaterialNoLength(10);
        return new LineItem(m, amountOfSquareDiscs(), "Til montering af rem på stolper", m.getPrice() * amountOfSquareDiscs(),Type.NOLENGTH);
    }
    public LineItem roofTileBinders() {
        Material m = Manager.getMaterialNoLength(15);
        return new LineItem(m, amountOfRoofTileBinders(), "til montering af tagsten, alle ydersten + hver anden fastgøres", m.getPrice() * amountOfRoofTileBinders(),Type.ROOF);
    }



    public static void main(String[] args) throws NoSuchRoofException {
//        System.out.println(amountOfRafters(730, true, 220));
//        System.out.println(amountOfLaths(360,20));
        System.out.println(amountOfRoofTiles(360, 730, 20));
//        System.out.println(amountOfRidgeTiles(730));
//        System.out.println(amountOfFasciaBoardsCarport(730,true,220));
//        System.out.println(amountOfFasciaBoardsShed(220));
//        System.out.println(amountOfBracketScrewsInterties());
//System.out.println(amountOfScrewsLathHolders(360, true, 220));
//        System.out.println(amountOfScrewsFasciaAndSoffits(730, 360, true,220,20));
//System.out.println(amountOfScrewsLaths(360,20));
//System.out.println(TILE_W_X_H);
//System.out.println(fasciaCarport(730, true, 220));

    }
}
