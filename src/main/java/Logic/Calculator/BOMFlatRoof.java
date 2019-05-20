/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.Carport;
import Data.Mappers.IMaterialMapper;
import Data.Entity.LineItem;
import Data.Entity.Material;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.Shed;
import Data.Entity.Type;
import Data.Mappers.IRequestMapper;
import Logic.Controller.LogicFacade;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.SystemErrorException;

/**
 *
 * @author Kasper Jeppesen
 */
public class BOMFlatRoof {

    private BOMInclineRoof ir = new BOMInclineRoof();

    //------------------------------- calculate methods for fittings and screws ------------------------------------------------------------------------
    /**
     * @param c
     * @return the amount of packages of bracket screws (beslag skruer) needed
     * for the carport.
     */
    public int calculateQuantityOfBracketScrews(Carport c) {
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
    public int calculateQuantityOfBordsBolts(Carport c) {
        //two boards bolts (brædderbolte) for each post (stolpe)
        return calculateQuantityOfPost(c) * 2;
    }

    /**
     *
     * @param c
     * @return the amount of sqaure disc (firkant skiver) needed for the carport
     */
    private int CalculateQuantityOfSqaureDisc(Carport c) {
        //One sqaure disc (firkant skive) for each board bolt (brææder bolt)
        return calculateQuantityOfBordsBolts(c);
    }

    /**
     *
     * @param c
     * @return the amount of right bracket interties (højre beslag) needed for
     * the carport
     */
    public int calculateQuantityOfRightBracketInteries(Carport c) {
        //one right bracket interties (højre beslag) per rafter (spær)
        return calculateQuantityOfRafterIncludedBackRafter(c.getLength(), 60);
    }

    /**
     *
     * @param c
     * @return the amount of left bracket interties (venstre beslag) needed for
     * the carport
     */
    public int calculateQuantityOfLeftBracketInterties(Carport c) {
        //one left bracket interties (venstre beslag) per rafter (spær)
        return calculateQuantityOfRafterIncludedBackRafter(c.getLength(), 60);
    }

    //--------------------- methods for returning items in the category fittins and screw -----------------------------------------------------------
    /**
     *
     * @param c
     * @return a LineItem of bracket screws (beslagskruer)
     * @throws NoSuchMaterialException
     */
    public LineItem bracketScrews(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("4.0x50mm beslagskruer 250 stk");
        return new LineItem(m, calculateQuantityOfBracketScrews(c), "Til montering af universalbeslag + hulbånd", m.getPrice() * calculateQuantityOfBracketScrews(c), Type.NOLENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of boards bolts (brædderbolte)
     * @throws NoSuchMaterialException
     */
    public LineItem boardBolts(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("10x120mm brædderbolt");
        return new LineItem(m, calculateQuantityOfBordsBolts(c), "Til montering af rem på stolper", m.getPrice() * calculateQuantityOfBordsBolts(c), Type.NOLENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of sqaure discs (firkant skiver)
     * @throws NoSuchMaterialException
     * @throws SystemErrorException
     */
    public LineItem squareDiscs(Carport c) throws NoSuchMaterialException, SystemErrorException {
        Material m = LogicFacade.getInstance().getMaterialNoLength(10);
        return new LineItem(m, CalculateQuantityOfSqaureDisc(c), "Til montering af rem på stolper", m.getPrice() * CalculateQuantityOfSqaureDisc(c), Type.NOLENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of right bracket interties (højre beslag)
     * @throws NoSuchMaterialException
     */
    public LineItem rightBracketInteries(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("universal 190mm højre");
        return new LineItem(m, calculateQuantityOfRightBracketInteries(c), "Til montering af spær på rem", m.getPrice() * calculateQuantityOfRightBracketInteries(c), Type.NOLENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of left bracket interties (venstre beslag)
     * @throws NoSuchMaterialException
     */
    public LineItem leftBracketInteries(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("universal 190mm venstre");
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
    public int calculateQuantityOfRafterIncludedBackRafter(int length, int limit) {
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
    private float calculateSpaceBetweenRafter(int length, int quantityOfRafter) {
       
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
    public int calculateQuantityOFRafterExcluedBackRafter(int length, int limit) {
        
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
    public float spaceBetweenRafter(int length, int limit) {
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
    public int calculateQuantityOfPost(Carport c) {
        //Substraicing spaceBetweenSpær times 2 because post (stolper) cant appear at the front spear and back spear
        float lengthAvaiableForPost = c.getLength() - spaceBetweenRafter(c.getLength(), 60) * 2;
        // four corner stolper
        int quantity = 4;

        if (c.getShed_() != null) {

            lengthAvaiableForPost -= c.getShed_().getLength();

            if (c.getWidth() - c.getShed_().getWidth() == 30) {
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
    public int calculateQuantityOfUnderneathBoardForFrontAndBack(Carport c) {
        int quantity = 0;

        if (c.getWidth() < 250) {
            // 1 is enough for each side
            quantity = 2;
        }
        if (c.getWidth() >= 250 && c.getWidth() <= 500) {
            // 2 is needed for each side
            quantity = 4;
        }
        if (c.getWidth() > 500 && c.getWidth() < 750) {
            //3 is needed for each side
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
    public int calculateQuantityOfUnderneathBoardForSides(Carport c) {
        int quantity = 0;

        if (c.getLength() < 250) {
            // 1 is enough for each side
            quantity = 2;
        }
        if (c.getLength() >= 250 && c.getLength() <= 500) {
            // 2 is needed for each side
            quantity = 4;
        }
        if (c.getLength() > 500 && c.getLength() < 750) {
            // 3 is needed for each side
            quantity = 6;
        }
        return quantity;
    }

    /**
     *
     * @param c
     * @return the amount of outer boards (overstern brædder) for the front,
     * needed for the carport
     */
    public int calculateQuantityOfOuterBoardForFront(Carport c) {
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
    public int calculateQuantityOfOuterBoardForSides(Carport c) {
        //the amount of outer boards (overstern brædder) is eqaul to the amount of underneath boards (undertern brædder)
        return calculateQuantityOfUnderneathBoardForSides(c);
    }

    /**
     *
     * @param c
     * @return the amount of water boards (vandbræt) for the front. needed for
     * the carport
     */
    public int calculateQuantityOfWaterBoardForFront(Carport c) {
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
    public int calculateQuantityOfWaterBoardForSides(Carport c) {
        //amount of water boards (vandbræt) is eqaul to the amount of underneath boards (understernbrædder)
        return calculateQuantityOfUnderneathBoardForSides(c);
    }

    //--------------- methods for returning items in the category tree-fundament -------------------------------------------------------------------
    /**
     *
     * @param c
     * @return LineItem of rafters (spær)
     * @throws NoSuchMaterialException
     */
    public LineItem rafter(Carport c) throws NoSuchMaterialException {
        LineItem l = null;
        Material m;

        String desc = "Spær, monteres på rem";

        for (int i = 240; i <= 750; i += 30) {
            if (c.getLength() == i) {
                m = LogicFacade.getInstance().getMaterial("45x195mm spærtræ. ubh. ", i);
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
     */
    public LineItem post(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("97x97mm trykimp. Stolpe");
        return new LineItem(m, calculateQuantityOfPost(c), "Stolper, nedgraves 90cm i jord", m.getPrice() * calculateQuantityOfPost(c), Type.LENGTH);

    }

    /**
     *
     * @param c
     * @return LineItem of underneath boards (understern brædder) for the front
     * @throws NoSuchMaterialException
     */
    public LineItem underneathBoardForFrontAndBack(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("25x200mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfUnderneathBoardForFrontAndBack(c), "understernbrædder til forende og bagende", m.getPrice() * calculateQuantityOfUnderneathBoardForFrontAndBack(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of underneath boards (understern brædder) for the sides
     * @throws NoSuchMaterialException
     */
    public LineItem underneathBoardForSides(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("25x200mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfUnderneathBoardForSides(c), "understernbrædder til siderne", m.getPrice() * calculateQuantityOfUnderneathBoardForSides(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of outer boards (overstern brædder) for the front
     * @throws NoSuchMaterialException
     */
    public LineItem outerBoardForFront(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("25x125m trykimp. brædt");
        return new LineItem(m, calculateQuantityOfOuterBoardForFront(c), "oversternbrædder for siderne", m.getPrice() * calculateQuantityOfOuterBoardForFront(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of outer boards (overstern brædder) for the sides
     * @throws NoSuchMaterialException
     */
    public LineItem outerBoardForSides(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("25x125m trykimp. brædt");
        return new LineItem(m, calculateQuantityOfOuterBoardForSides(c), "oversternbrædder for siderne", m.getPrice() * calculateQuantityOfOuterBoardForSides(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of water boards (vandbræt) for the front
     * @throws NoSuchMaterialException
     */
    public LineItem waterBoardForFront(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("19x100mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfWaterBoardForFront(c), "vandbrædt på stern i forenden", m.getPrice() * calculateQuantityOfWaterBoardForFront(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return LineItem of water boards (vandbræt) for the sides
     * @throws NoSuchMaterialException
     */
    public LineItem waterBoardForSides(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("19x100mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfWaterBoardForSides(c), "vandbrædt på stern i siderne", m.getPrice() * calculateQuantityOfWaterBoardForSides(c), Type.LENGTH);
    }

    /**
     *
     * @param c
     * @return the amount of rater for strap (spær for remmen)
     * @throws NoSuchMaterialException
     */
    public LineItem RafterForStrap(Carport c) throws NoSuchMaterialException {
        LineItem l = null;
        Material m;

        String desc = "Remme i sider, sadles ned i stolper";

        for (int i = 240; i <= 750; i += 30) {
            if (c.getLength() == i) {
                m = LogicFacade.getInstance().getMaterial("45x195mm spærtræ. ubh. ", i);
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
    public LineItem roof(Carport c) {
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
     * Calcs amount of bands needed by dividing length of a single band (10m)
     * with the square root of length^2 + width^2 (hypothesis) and multiplying
     * this with 2 as 2 bands are needed.
     *
     * @param c
     * @return the amount of peforated bands needed for the carport.
     */
    public double calculateQuantityOfPerforatedBands(Carport c) {
        double lengthM;
        // if there is a toolshed, the length of the toolshed must be subtracted from the carport length. 
        if (c.getShed_() != null) {
            lengthM = c.getLength() - c.getShed_().getLength() - spaceBetweenRafter(c.getLength(), 60) / 100;
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
     */
    public LineItem perforatedBands(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("hulbånd 1x20 mm. 10 mtr.");
        return new LineItem(m, (int) calculateQuantityOfPerforatedBands(c), "Til vindkryds på spær", m.getPrice() * (int) calculateQuantityOfPerforatedBands(c), Type.NOLENGTH);
    }

//    public int calculateQuantityOfSpær2(int length) {
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
//    public float spaceBetweenSpær2(int length) {
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
