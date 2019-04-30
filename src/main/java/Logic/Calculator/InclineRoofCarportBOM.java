/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Mappers.IMaterialMapper;
import Data.Entity.LineItem;
import Data.Entity.Material;
import Logic.Exceptions.NoSuchMaterialException;

/**
 *
 * @author Kasper Jeppesen
 */
public class InclineRoofCarportBOM {

    /**
     *
     * @return (vindskeder)
     */
    public int amountOfSoffits() {
        return 2;
    }

    /**
     *
     * @param carportLength
     * @param isShed
     * @param shedLength
     * @return (spær)
     */
    public static int amountOfRafters(int carportLength, boolean isShed, int shedLength) {
        //the laths extend beyond the first/last rafter by 30 cm.
        carportLength -= 30;
        int shedRafters = 0;

        //if there is a shed the amount of rafters must be calculated seperately,
        //since the space limits are different. 
        if (isShed) {
            carportLength -= shedLength;
            shedRafters = amountOfRaftersShed(shedLength);
        }
        //the minimum quantity of rafters will always be 3.
        int rafterQty = 3;

        float widthOfSpær = 4.5f;

        //this is the first rafter which gets added between the front rafter and back rafter.
        int lastAdded = 1;

        //there is a space between the middle rafter and front rafter, and a space between the middle and back rafter.
        int spaceAmount = 2;
        float spaceBetweenSpær = (carportLength - (widthOfSpær * rafterQty)) / spaceAmount;

        //105 is the limit of how much space is allowed between the rafters
        while (spaceBetweenSpær >= 105) {
            //number of added rafters get doubled everty time
            lastAdded *= 2;
            rafterQty += lastAdded;
            //as a result the amount of spaces between rafters also gets doubled every time
            spaceAmount *= 2;

            spaceBetweenSpær = (carportLength - widthOfSpær * rafterQty) / spaceAmount;
        }
        return rafterQty + shedRafters;
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
        return lathQty;
    }

    public static int amountOfLathHolders(int carportLength, boolean isShed, int shedLength) {
        /*amount of lath-holders is the same as rafters; 1 lath-holder for each rafter*/
        return amountOfRafters(carportLength, isShed, shedLength);
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
         */
        double x = (hypotenuse * 730) / 288;
        /*
        since the square measurement of a single tile is now known, the total amount of tiles
        can be calculated.
         */

        double result = (hypotenuse * carportLength) / (x);
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
        int tileWidth = carportLength / 21;
        return carportLength / tileWidth;
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
     * @param isShed
     * @param shedLength
     * @return (sternbrædder)
     */
    public static int amountOfFasciaBoardsCarport(int carportLength, boolean isShed, int shedLength) {
        /*
        (carport minus shed)
         */
        int length = carportLength;
        if (isShed) {
            length -= shedLength;
        }
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
     * @param isShed
     * @param shedLength
     * @return (remme - carportdel)
     */
    public int amountOfBeams(int carportLength, boolean isShed, int shedLength) {
        if (isShed) {
            carportLength -= shedLength;
        }
        int totalLengthNeeded = carportLength * 2; //*2 since there is two (length)sides. 
        if (totalLengthNeeded <= 480) {
            return 1;
        } else if (totalLengthNeeded > 480 && totalLengthNeeded <= 480 * 2) {
            return 2;
        } else if (totalLengthNeeded > 480 && totalLengthNeeded <= 480 * 3) {
            return 3;
        } else {
            return 4; //4*480 is 1920, max length needed is 780*2 (1560)
        }
    }

    public int amountOfBeamsShed(int shedLength) {
        int totalLengthNeeded = shedLength * 2;
        if (totalLengthNeeded <= 480) {
            return 2; // 1 board of 540cm will be enough for both sides. 
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
    public int amountOfTopLaths() {
        return 2;
    }

    public LineItem rafters(int carportLength, boolean isShed, int shedLength) throws NoSuchMaterialException {
        Material m = IMaterialMapper.instance().getMaterial_("fædigskåret (byg-selv spær)");
        return new LineItem(m, amountOfRafters(carportLength, isShed, shedLength), "byg-selv spær (skal samles) 8 stk.", m.getPrice() * amountOfRafters(carportLength, isShed, shedLength));
    }

    public static void main(String[] args) {
//        System.out.println(amountOfRafters(730, true, 220));
//        System.out.println(amountOfLaths(360,20));
//        System.out.println(roofTiles(360,730, 20));
//        System.out.println(amountOfRidgeTiles(730));
//        System.out.println(amountOfFasciaBoardsCarport(730,true,220));
//        System.out.println(amountOfFasciaBoardsShed(220));

    }
}
