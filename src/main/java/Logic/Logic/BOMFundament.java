/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Logic;

import Data.Entity.Carport;
import Data.Entity.LineItem;
import Data.Entity.Material;
import Data.Entity.Type;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.SystemErrorException;

/**
 * Calculations regarding the fundament of a carport. Calculates the amount of
 * materials needed and returns a lineitem for the specific material.
 *
 * @author Kasper Jeppesen
 */
public class BOMFundament {

    //------------------------------- calculate methods for fittings and screws ------------------------------------------------------------------------
    /**
     * @param c
     * @return the amount of packages of bracket screws (beslag skruer) needed
     * for the carport.
     */
    protected int calculateQuantityOfBracketScrews(Carport c) {
        //At minimun one package is needed
        int packsNeeded = 1;
        int quantity = 0;

        //---- bracket screws (beslagskruer) used for bracket interties (beslag) -------
        // Three sides per brackets Interties (beslag)
        int sidesOfLeftBracketInterties = calculateQuantityOfLeftBracketInterties(c) * 3;
        int sidesOfRightBracketInterties = calculateQuantityOfRightBracketInteries(c) * 3;

        //Three brackes screws for each side
        quantity = (sidesOfRightBracketInterties + sidesOfLeftBracketInterties) * 3;

        //--- bracket screws (beslagskruer) used for perforated band (hulbånd) -----
        //Two bracket screws (beslagskruer) for each rafter (spær) the perforated band (hulbånd) crosses
        //The perforated band (hulb¨nd) does not cross front or back rafter (spær) therefor minus 2
        int quantityOfRafter = calculateQuantityOfRafterIncludedBackRafter(c.getLength(), 60) - 2;

        //*2 because there is two perforated bands (hulbånd) and *2 because two bracket screws (beslagskruer) for each bracket interties (beslag)
        quantity += quantityOfRafter * 2 * 2;

        //250 is the amount of beslagskruer in a pack
        while (quantity > 250) {
            packsNeeded++;
            quantity -= 250;
        }
        return packsNeeded;
    }

    /**
     *
     * @param c
     * @return the amount of boards bolts (brædderbolte) needed for the carport
     */
    protected int calculateQuantityOfBordsBolts(Carport c) {
        //two boards bolts (brædderbolte) for each post (stolpe)
        return calculateQuantityOfPost(c) * 2;
    }

    /**
     *
     * @param c
     * @return the amount of sqaure disc (firkant skiver) needed for the carport
     */
    protected int CalculateQuantityOfSqaureDisc(Carport c) {
        //One sqaure disc (firkant skive) for each board bolt (brææder bolt)
        return calculateQuantityOfBordsBolts(c);
    }

    /**
     *
     * @param c
     * @return the amount of right bracket interties (højre beslag) needed for
     * the carport
     */
    protected int calculateQuantityOfRightBracketInteries(Carport c) {
        //one right bracket interties (højre beslag) per rafter (spær)
        return calculateQuantityOfRafterIncludedBackRafter(c.getLength(), 60);
    }

    /**
     *
     * @param c
     * @return the amount of left bracket interties (venstre beslag) needed for
     * the carport
     */
    protected int calculateQuantityOfLeftBracketInterties(Carport c) {
        //one left bracket interties (venstre beslag) per rafter (spær)
        return calculateQuantityOfRafterIncludedBackRafter(c.getLength(), 60);
    }

    //--------------------- methods for returning items in the category fittins and screw -----------------------------------------------------------
    /**
     *
     * @param c
     * @return a LineItem of bracket screws (beslagskruer)
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem bracketScrews(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getFitting(7);
        return new LineItem(m, calculateQuantityOfBracketScrews(c), "Til montering af universalbeslag + hulbånd", m.getPrice() * calculateQuantityOfBracketScrews(c), Type.NOLENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of boards bolts (brædderbolte)
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem boardBolts(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getFitting(9);
        return new LineItem(m, calculateQuantityOfBordsBolts(c), "Til montering af rem på stolper", m.getPrice() * calculateQuantityOfBordsBolts(c), Type.NOLENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of sqaure discs (firkant skiver)
     * @throws NoSuchMaterialException
     * @throws SystemErrorException
     */
    protected LineItem squareDiscs(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getFitting(10);
        return new LineItem(m, CalculateQuantityOfSqaureDisc(c), "Til montering af rem på stolper", m.getPrice() * CalculateQuantityOfSqaureDisc(c), Type.NOLENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of right bracket interties (højre beslag)
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem rightBracketInteries(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getFitting(1);
        return new LineItem(m, calculateQuantityOfRightBracketInteries(c), "Til montering af spær på rem", m.getPrice() * calculateQuantityOfRightBracketInteries(c), Type.NOLENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of left bracket interties (venstre beslag)
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem leftBracketInteries(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getFitting(2);
        return new LineItem(m, calculateQuantityOfLeftBracketInterties(c), "Til montering af spær på rem", m.getPrice() * calculateQuantityOfLeftBracketInterties(c), Type.NOLENGTH);
    }

    //------------------------------------- calculate methods for tree-fundament ------------------------------------------------------------
    /**
     * 
     * @param length
     * @param limit - the limit of how much space must be between each rafter
     * (spær)
     * @return amount of rater (spær) needed for the carport
     */
    protected int calculateQuantityOfRafterIncludedBackRafter(int length, int limit) {
        int quantity = calculateQuantityOFRafterExcluedBackRafter(length, limit) + 1;
        return quantity;
    }

    /**
     * Used by calculateQuantityOfSpærExcludedBackSpær() and spaceBetweenSpær()
     * to calculate the space between spær
     *
     * @param c
     * @param quantityOfSpær
     * @return
     */
    protected float calculateSpaceBetweenRafter(int length, int quantityOfRafter) {

        float widthOfRafter = 4.5f;

        float spaceOfAllRafter = quantityOfRafter * widthOfRafter;
        float spaceAviable = length - spaceOfAllRafter;
        float spaceBetweenRafter = spaceAviable / quantityOfRafter;

        return spaceBetweenRafter;
    }

    /**
     * This method is used to DrawSVG as amount of raters (spær) excluded the
     * back raft is eqaul to amount of spaces between rafter (spær)
     *
     * @param length
     * @param limit - the limit of how much space must be between each rafter
     * (spær)
     * @return amount of rafter (spær) minus the back rafter (bagspæret)
     */
    protected int calculateQuantityOFRafterExcluedBackRafter(int length, int limit) {

        //Amount of rafter (spær) needed - Integer cut of any decimal, therefor if the division eqauls example 7,5, then the interger hold 7
        int quantity = length / limit;

        float spaceBetweenRafter = calculateSpaceBetweenRafter(length, quantity);
        //Since the interget cut of any decimal, the quantity used to calculate the space between rafter (spær) could lead to a case where the space
        // between rafter (spær) was too high, and if so the quantity get added by 1. (so in the example instead of 7, we want it to be 8)
        if (spaceBetweenRafter > limit) {
            quantity++;
        }

        return quantity;
    }

    /**
     * This method is used to drawSVG
     *
     * @param length
     * @param limit
     * @return the space between spær
     */
    protected float spaceBetweenRafter(int length, int limit) {
        float spaceBetweenRafters = calculateSpaceBetweenRafter(length, calculateQuantityOFRafterExcluedBackRafter(length, limit));

        if (spaceBetweenRafters > limit) {
            int quantityOfRafterExcludedBackRafter = calculateQuantityOFRafterExcluedBackRafter(length, limit) + 1;
            spaceBetweenRafters = calculateSpaceBetweenRafter(length, quantityOfRafterExcludedBackRafter);
        }

        return spaceBetweenRafters;
    }

    /**
     *
     * @param c
     * @return the amount of post(stolper) needed for the carport
     */
    protected int calculateQuantityOfPost(Carport c) {
        //Substraicing spaceBetweenSpær times 2 because post (stolper) cant appear at the front spear and back spear
        float lengthAvaiableForPost = c.getLength() - spaceBetweenRafter(c.getLength(), 60) * 2;
        // four corner stolper
        int quantity = 4;

        if (c.getShed() != null) {

            lengthAvaiableForPost -= c.getShed().getLength();

            if (c.getWidth() - c.getShed().getWidth() == 30) {
                //A shed consists of 6 post(stolper), the 2 back corner of the carport, aswell as two front post for the shed abd two middle post for the shed
                quantity += 4;
            } else {
                //one extra post(stolpe) is needed to function as a corner post for the shed, as the top right corner post for the carport can no longer be used
                quantity += 5;
            }

        }

        //check if carport is to small to have other post(stolper) than the corner post and eventual post added for the shed
        if (lengthAvaiableForPost <= 250) {
            //the amount of cornerpost & shedpost)
            return quantity;
        }

        float widthOfPost = 9.7f;
        int numberOfSpacesBewteenPost = 1;

        //get the space between post(stolper) at one of the sides (in cm)
        float SpaceBetweenPost = lengthAvaiableForPost - (widthOfPost * (quantity / 2));
        //250 is the limit of how much space must be between post (stolper)
        while (SpaceBetweenPost > 250) {
            // add a post(stolpe) for each side
            quantity = quantity + 2;
            numberOfSpacesBewteenPost = numberOfSpacesBewteenPost * 2;
            //get the space between post(stolper) at one of the sides (in cm)
            SpaceBetweenPost = (lengthAvaiableForPost - (widthOfPost * (quantity / 2))) / numberOfSpacesBewteenPost;
        }
        return quantity;
    }

    /**
     * the length of a underneath borad is 250cm?? so one board can cover up 250
     * of a carport width
     *
     * @param c
     * @return the amount of underneath board (understern brædder) for the
     * front, is needed for the carport
     */
    protected int calculateQuantityOfUnderneathBoardForFrontAndBack(Carport c) {
        int quantity = 0;

        if (c.getWidth() < 360) {
            // 1 is enough for each side
            quantity = 2;
        } else if (c.getWidth() >= 360 && c.getWidth() <= 720) {
            // 2 is needed for each side
            quantity = 4;
        } else {
            quantity = 6;
        }
        return quantity;
    }

    /**
     * the length of a underneath board (understern brædt) is 250cm?? so one
     * board can cover 250cm of the carport length
     *
     * @param c
     * @return the amount of underneath boards (understern brædder) for the
     * sides, needed for the carport
     */
    protected int calculateQuantityOfUnderneathBoardForSides(Carport c) {
        int quantity;

        if (c.getLength() < 540) {
            // 1 is enough for each side
            quantity = 2;
        }
        else {
            // 2 is needed for each side
            quantity = 4;
        }
        return quantity;
    }

    /**
     *
     * @param c
     * @return the amount of outer boards (overstern brædder) for the front,
     * needed for the carport
     */
    protected int calculateQuantityOfOuterBoardForFront(Carport c) {
        /*
        The amount of outer board (overstern brædder) for the front is equal to the amount of underneath board (understern brædder) for the front and back, 
        divided by 2 because outer board is only for the front
         */
        return calculateQuantityOfUnderneathBoardForFrontAndBack(c) / 2;
    }

    /**
     *
     * @param c
     * @return the amount of outer boards (overstern brædder) for the sides,
     * needed for the carport
     */
    protected int calculateQuantityOfOuterBoardForSides(Carport c) {
        //the amount of outer boards (overstern brædder) is eqaul to the amount of underneath boards (undertern brædder)
        return calculateQuantityOfUnderneathBoardForSides(c);
    }

    /**
     *
     * @param c
     * @return the amount of water boards (vandbræt) for the front. needed for
     * the carport
     */
    protected int calculateQuantityOfWaterBoardForFront(Carport c) {
        /*
        The amount of water boards (vandbræt) for the front is equal to the amount of underneath board (understern brædder) for the front and back, 
        divided by 2 because water board (vandbræt) is only for the front
         */
        return calculateQuantityOfUnderneathBoardForFrontAndBack(c) / 2;
    }

    /**
     *
     * @param c
     * @return the amount of water boards (vandbræt) for the sides, needed for
     * the carport
     */
    protected int calculateQuantityOfWaterBoardForSides(Carport c) {
        //amount of water boards (vandbræt) is eqaul to the amount of underneath boards (understernbrædder)
        return calculateQuantityOfUnderneathBoardForSides(c);
    }

    //--------------- methods for returning items in the category tree-fundament -------------------------------------------------------------------
    /**
     *
     * @param c
     * @return LineItem of rafters (spær)
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem rafter(Carport c) throws NoSuchMaterialException, SystemErrorException {
        LineItem l = null;
        Material m;

        String desc = "Spær, monteres på rem";

        for (int i = 240; i <= 750; i += 30) {
            if (c.getLength() == i) {
                m = LogicFacade.getInstance().getWoodMaterial(1, i);
                l = new LineItem(m, calculateQuantityOfRafterIncludedBackRafter(c.getLength(), 60), desc, m.getPrice() * calculateQuantityOfRafterIncludedBackRafter(c.getLength(), 60), Type.LENGTH);
            }
        }
        return l;
    }

    /**
     *
     * @param c
     * @return LineItem of post (stolper)
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem post(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getWoodMaterial(3, 300);
        return new LineItem(m, calculateQuantityOfPost(c), "Stolper, nedgraves 90cm i jord", m.getPrice() * calculateQuantityOfPost(c), Type.LENGTH);

    }

    /**
     *
     * @param c
     * @return LineItem of underneath boards (understern brædder) for the front
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem underneathBoardForFrontAndBack(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getWoodMaterial(4, 360);
        return new LineItem(m, calculateQuantityOfUnderneathBoardForFrontAndBack(c), "understernbrædder til forende og bagende", m.getPrice() * calculateQuantityOfUnderneathBoardForFrontAndBack(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of underneath boards (understern brædder) for the sides
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem underneathBoardForSides(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getWoodMaterial(4, 540);
        return new LineItem(m, calculateQuantityOfUnderneathBoardForSides(c), "understernbrædder til siderne", m.getPrice() * calculateQuantityOfUnderneathBoardForSides(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of outer boards (overstern brædder) for the front
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem outerBoardForFront(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getWoodMaterial(6, 360);
        return new LineItem(m, calculateQuantityOfOuterBoardForFront(c), "oversternbrædder for front", m.getPrice() * calculateQuantityOfOuterBoardForFront(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of outer boards (overstern brædder) for the sides
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem outerBoardForSides(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getWoodMaterial(6, 540);
        System.out.println("length : " + m.getLength());
        return new LineItem(m, calculateQuantityOfOuterBoardForSides(c), "oversternbrædder for siderne", m.getPrice() * calculateQuantityOfOuterBoardForSides(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of water boards (vandbræt) for the front
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem waterBoardForFront(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getWoodMaterial(7, 360);
        return new LineItem(m, calculateQuantityOfWaterBoardForFront(c), "vandbrædt på stern i forenden", m.getPrice() * calculateQuantityOfWaterBoardForFront(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of water boards (vandbræt) for the sides
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem waterBoardForSides(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getWoodMaterial(7, 540);
        return new LineItem(m, calculateQuantityOfWaterBoardForSides(c), "vandbrædt på stern i siderne", m.getPrice() * calculateQuantityOfWaterBoardForSides(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return the amount of rater for strap (spær for remmen)
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem RafterForStrap(Carport c) throws NoSuchMaterialException, SystemErrorException {
        LineItem l = null;
        Material m;

        String desc = "Remme i sider, sadles ned i stolper";

        for (int i = 240; i <= 750; i += 30) {
            if (c.getLength() == i) {
                m = LogicFacade.getInstance().getWoodMaterial(1, i);
                l = new LineItem(m, 2, desc, m.getPrice() * 2, Type.LENGTH);
            }
        }
        return l;
    }

    //------------------------- -----------calculation methods for the roof ---------------------------------------------------------------------
    static int tagpladeAntal(Carport c) {
        return c.getLength() / 30; // widt of a single roof tile is 30 cm. 

    }

    //------------------------------- methods for returning items of category roof --------------------------------------------------------------
    protected LineItem roof(Carport c) {
        LineItem l = null;
        String desc = "Tagplader monteres på spær";
        for (int i = 260; i <= 770; i += 30) {
            if (c.getLength() + 20 == i) {
                l = new LineItem(c.getRoof(), tagpladeAntal(c), desc, c.getRoof().getPrice() * tagpladeAntal(c));
            }
        }
        return l;
    }

    //-------------------------------- calculation methods for other things -----------------------------
    /**
     * Calcs amount of bands needed by calculating square root of length^2 +
     * width^2 (hypotenuse), multiplying this by 2, since 2 bands are needed,
     * and lastly, dividing the result by 10 since the length of a single band
     * is 10m.
     *
     * @param c
     * @return the amount of peforated bands needed for the carport.
     */
    protected double calculateQuantityOfPerforatedBands(Carport c) {
        double lengthM;
        // if there is a toolshed, the length of the toolshed must be subtracted from the carport length. 
        if (c.getShed() != null) {
            lengthM = (c.getLength() - c.getShed().getLength() - spaceBetweenRafter(c.getLength(), 60)) / 100;
        } else {
            //length in meters
            lengthM = (c.getLength() - spaceBetweenRafter(c.getLength(), 60)) / 100;
        }
        // width in meters
        double widthM = c.getWidth() / 100;
        //Math.ceil = round up to nearest integer
        //Math.sqrt = square root
        return Math.ceil((Math.sqrt((lengthM * lengthM) + (widthM * widthM)) * 2) / 10);
    }

    //------------------------------------ methods for retuning items of -------------------------------------------------------------
    /**
     *
     * @param c
     * @return LineItem of perforated bands (hulbånd)
     * @throws NoSuchMaterialException
     * @throws Presentation.Exceptions.SystemErrorException
     */
    protected LineItem perforatedBands(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getFitting(16);
        return new LineItem(m, (int) calculateQuantityOfPerforatedBands(c), "Til vindkryds på spær", m.getPrice() * (int) calculateQuantityOfPerforatedBands(c), Type.NOLENGTH);
    }

//    protected int calculateQuantityOfSpær2(int length) {
//        //the front and back spær
//        int quantity = 2;
//        float widthOfSpær = 4.5f;
//
//        // This is the first spær which get added between the front spær and back spær
//        int lastAddedSpær = 1;
//        quantity += lastAddedSpær;
//        // 2 = There is a space between the middle spær to the front spær, and a space between the middle spær and back spær
//        int numberOfSpacesBetweenSpær = 2;
//        float spaceBetweenSpær = (length - (widthOfSpær * quantity)) / numberOfSpacesBetweenSpær;
//
//        // 75 is the limit of how much space must be between spærene
//        while (spaceBetweenSpær > 75) {
//            //number of added spær gets doubled everty time
//            lastAddedSpær *= 2;
//            quantity += lastAddedSpær;
//            // as a result the amount of spaces between spær also gets doubled every time
//            numberOfSpacesBetweenSpær = numberOfSpacesBetweenSpær * 2;
//            spaceBetweenSpær = (length - (widthOfSpær * quantity)) / numberOfSpacesBetweenSpær;
//        }
//        return quantity;
//    }
//    protected float spaceBetweenSpær2(int length) {
//        //the front and back spær
//        int quantity = 2;
//        float widthOfSpær = 4.5f;
//
//        // This is the first spær which get added between the front spær and back spær
//        int lastAddedSpær = 1;
//        quantity += lastAddedSpær;
//        // 2 = There is a space between the middle spær to the front spær, and a space between the middle spær and back spær
//        int numberOfSpacesBetweenSpær = 2;
//        float spaceBetweenSpær = (length - (widthOfSpær * quantity)) / numberOfSpacesBetweenSpær;
//
//        // 75 is the limit of how much space must be between spærene
//        while (spaceBetweenSpær > 75) {
//            //number of added spær gets doubled everty time
//            lastAddedSpær *= 2;
//            quantity += lastAddedSpær;
//            // as a result the amount of spaces between spær also gets doubled every time
//            numberOfSpacesBetweenSpær *= 2;
//            spaceBetweenSpær = (length - (widthOfSpær * quantity)) / numberOfSpacesBetweenSpær;
//        }
//        return spaceBetweenSpær;
//    }
}
