/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

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

/**
 *
 * @author Kasper Jeppesen
 */
public class BOMFlatRoof {

    /**
     *
     * @param length
     * @return the amount of packages of bracket screws needed for the carport.
     */
    public int calculateQuantityOfBeslagskruer(int lengthOfCarport) {
        //Der skal som minimum bruges 1 pakke. 
        int packsNeeded = 1;
        int quantity = 0;

        // Tre sider per beslag. 
        int sidesOfVenstreBeslag = calculateQuantityOfVenstrebeslag(lengthOfCarport) * 3;
        int sidesOfHøjreBeslag = calculateQuantityOfHøjreBeslag(lengthOfCarport) * 3;

        //Tre beslagsskruer per side.
        quantity = (sidesOfHøjreBeslag + sidesOfVenstreBeslag) * 3;

        //2 beslagskruer for each spær the hulbånd crosses and the hulbånd does not croos front or back spær, therefor minus 2
        int quantityOfSpær = calculateQuantityOfSpærIncludedBackSpær(lengthOfCarport, 60) - 2;

        //*2 fordi der er to hulbånd og *2 fordi der er to skruer per beslag. 
        quantity += quantityOfSpær * 2 * 2;

        //250 is the amount of beslagskruer in a pack
        while (quantity > 250) {
            packsNeeded++;
            quantity -= 250;
        }
        return packsNeeded;
    }

    public int calculateQuantityOfBrædderbolt(int length, int width, Shed shed) {
        //two brædderbolte per stolpe
        return calculateQuantityOfStolper(length, width, shed) * 2;
    }

    public int calculateQuantityOfHøjreBeslag(int lengthOfCarport) {
        //one venstrebeslag per spær
        return calculateQuantityOfSpærIncludedBackSpær(lengthOfCarport, 60);
    }

    public int calculateQuantityOfVenstrebeslag(int lengthOfCarport) {
        //one venstrebeslag per spær
        return calculateQuantityOfSpærIncludedBackSpær(lengthOfCarport, 60);
    }

    public int calculateQuantityOfUndersternbrædderForFrontAndBack(int width) {
        int quantity = 0;

        if (width < 250) {
            // 1 is enough for each side
            quantity = 2;
        }
        if (width >= 250 && width <= 500) {
            // 2 is needed for each side
            quantity = 4;
        }
        if (width > 500 && width < 750) {
            quantity = 6;
        }
        return quantity;
    }

    /**
     *
     * @param length
     * @return
     */
    public int calculateQuantityOfUndersternbrædderForSides(int length) {
        int quantity = 0;

        if (length < 250) {
            // 1 is enough for each side
            quantity = 2;
        }
        if (length >= 250 && length <= 500) {
            // 2 is needed for each side
            quantity = 4;
        }
        if (length > 500 && length < 750) {
            // 3 is needed for each side
            quantity = 6;
        }
        return quantity;
    }

    public int calculateQuantityOfOversternbrædderForFront(int width) {
        //divided by 2 because only the front is needed, not the back
        return calculateQuantityOfUndersternbrædderForFrontAndBack(width) / 2;
    }

    public int calculateQuantityOfOversternbrædderForSides(int length) {
        //amount of oversternbrædder is eqaul to the amount of understernbrædder
        return calculateQuantityOfUndersternbrædderForSides(length);
    }

    public int calculateQuantityOfVandbrætForFront(int width) {
        //divided by 2 because only the front is needed, not the back
        return calculateQuantityOfUndersternbrædderForFrontAndBack(width) / 2;
    }

    public int calculateQuantityOfVandbrædtForSides(int length) {
        //amount of oversternbrædder is eqaul to the amount of understernbrædder
        return calculateQuantityOfUndersternbrædderForSides(length);
    }

    /**
     *
     * @param shed
     * @param shedLength
     * @param length
     * @return
     */
    public int calculateQuantityOfStolper(int lengthOfCarport, int widthOfCarport, Shed shed) {

        //Substraicing spaceBetweenSpær times 2 because stolper cant appear at the front spear and back spear
        float lengthAvaiableForStolper = lengthOfCarport - spaceBetweenSpær(calculateQuantityOFSpærExcluedBackSpær(lengthOfCarport, 60), lengthOfCarport, 60) * 2;
        // four corner stolper
        int quantity = 4;

        if (shed != null) {
            lengthAvaiableForStolper -= shed.getLength();
            
            if(widthOfCarport - shed.getWidth() == 30)
            {
                //A shed consists of 6 stolper, the 2 back corner of the carport, aswell as two front stolper for the shed abd two middle stolper for the shed
                quantity += 4;
            }
            else
            {
                //one extra stolpe is needed to function as a corner stolpe for the shed, as the top right corner stolpe for the carport can no longer be used
                quantity += 5;
            }
            
        }

        //check if carport is to small to have other stolper than the corner stolpe and eventual stolper added for the shed
        if (lengthAvaiableForStolper <= 250) {
            //the amount of cornerStolper( & shedstolper)
            return quantity;
        }

        float widthOfStolpe = 9.7f;
        int numberOfSpacesBewteenStolpe = 1;

        //get the space between stolpe at one of the sides (in cm)
        float SpaceBetweenStolpe = lengthAvaiableForStolper - (widthOfStolpe * (quantity / 2));
        //250 is the limit of how much space must be between stolper
        while (SpaceBetweenStolpe > 250) {
            // add a stolpe for each side
            quantity = quantity + 2;
            numberOfSpacesBewteenStolpe = numberOfSpacesBewteenStolpe * 2;

            SpaceBetweenStolpe = (lengthAvaiableForStolper - (widthOfStolpe * (quantity / 2))) / numberOfSpacesBewteenStolpe;
        }
        return quantity;
    }

    /**
     * Calcs amount of bands needed by dividing length of a single band (10m)
     * with the square root of length^2 + width^2 (hypothesis) and multiplying
     * this with 2 as 2 bands are needed.
     *
     * @param req
     * @return the amount of peforated bands needed for the carport.
     */
    public double hulbåndAntal(Request req) {
        double lengthM;
        // if there is a toolshed, the length of the toolshed must be subtracted from the carport length. 
        if (req.getCarport().getShed_()!=null) {
            lengthM = req.getCarport().getLength() - req.getCarport().getShed_().getLength() - spaceBetweenSpær(calculateQuantityOFSpærExcluedBackSpær(req.getCarport().getLength(), 60), req.getCarport().getLength(), 60) / 100;
        } else {
            //length in meters
            lengthM = (req.getCarport().getLength() - spaceBetweenSpær(calculateQuantityOFSpærExcluedBackSpær(req.getCarport().getLength(), 60), req.getCarport().getLength(), 60)) / 100;
        }
        // width in meters
        double widthM = req.getCarport().getWidth() / 100;
        //Math.ceil = round up to nearest integer
        //Math.sqrt = square root
        return Math.ceil((Math.sqrt((lengthM * lengthM) + (widthM * widthM)) * 2) / 10);
    }

    static int tagpladeAntal(int length) {
        return length / 30; // widt of a single roof tile is 30 cm. 
        
    }
    public int calculateQuantityOFSpærExcluedBackSpær(int lengthOfCarport, int limit)
    {
        int quantity = lengthOfCarport / limit;
        
        float spaceBetweenSpær = calculateSpaceBetweenSpær(quantity, lengthOfCarport);
        
        if(spaceBetweenSpær > limit)
        {
            quantity++;
        }
        
        return quantity ;
    }
    public int calculateQuantityOfSpærIncludedBackSpær(int lengthOfCarport, int limit)
    {
        int quantity = calculateQuantityOFSpærExcluedBackSpær(lengthOfCarport, limit) + 1;
        return quantity;
    }
    
    public float spaceBetweenSpær(int quantityOfSpærExcludedBackSpær,int lengthOfCarport, int limit)
    {
        float spaceBetweenSpær = calculateSpaceBetweenSpær(quantityOfSpærExcludedBackSpær, lengthOfCarport);
        
        if(spaceBetweenSpær > limit)
        {
            quantityOfSpærExcludedBackSpær++;
            spaceBetweenSpær = calculateSpaceBetweenSpær(quantityOfSpærExcludedBackSpær, lengthOfCarport);
        }
        
        return spaceBetweenSpær;
    }
    private float calculateSpaceBetweenSpær(int quantityOfSpær, int lengthOfCarport)
    {
        float widthOfSpær = 4.5f;
        
        float spaceOfAllSpær = quantityOfSpær * widthOfSpær;
        float spaceAviable = lengthOfCarport - spaceOfAllSpær;
        float spaceBetweenSpær = spaceAviable / quantityOfSpær;
        
        return spaceBetweenSpær;
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

    public int calculateQuantityOfSpærForRemmen() {
        //1 spær is used for each side of the carport
        return 2;
    }

    public LineItem beslagskruer(int length) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("4.0x50mm beslagskruer 250 stk");
        return new LineItem(m, calculateQuantityOfBeslagskruer(length), "Til montering af universalbeslag + hulbånd", m.getPrice() * calculateQuantityOfBeslagskruer(length), Type.NOLENGTH);
    }

    public LineItem brædderbolt(int length, int width, Shed shed) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("10x120mm brædderbolt");
        return new LineItem(m, calculateQuantityOfBrædderbolt(length, width, shed), "Til montering af rem på stolper", m.getPrice() * calculateQuantityOfBrædderbolt(length,width, shed), Type.NOLENGTH);
    }

    public LineItem højrebeslag(int length) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("universal 190mm højre");
        return new LineItem(m, calculateQuantityOfHøjreBeslag(length), "Til montering af spær på rem", m.getPrice() * calculateQuantityOfHøjreBeslag(length), Type.NOLENGTH);
    }
//    

    public LineItem venstrebeslag(int length) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("universal 190mm venstre");
        return new LineItem(m, calculateQuantityOfVenstrebeslag(length), "Til montering af spær på rem", m.getPrice() * calculateQuantityOfVenstrebeslag(length), Type.NOLENGTH);
    }
//    

    public LineItem stolpe(int length,int width, Shed shed) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("97x97mm trykimp. Stolpe");
        return new LineItem(m, calculateQuantityOfStolper(length,width, shed), "Stolper, nedgraves 90cm i jord", m.getPrice() * calculateQuantityOfStolper(length,width, shed), Type.LENGTH);

    }

    public LineItem spær(int lengthOfCarport, int width) throws NoSuchMaterialException {
        LineItem l = null;
        Material m;

        String desc = "Spær, monteres på rem";

        for (int i = 240; i <= 750; i += 30) {
            if (lengthOfCarport == i) {
                m = LogicFacade.getInstance().getMaterial("45x195mm spærtræ. ubh. ", i);
                l = new LineItem(m, calculateQuantityOfSpærIncludedBackSpær(lengthOfCarport, 60), desc, m.getPrice() * calculateQuantityOfSpærIncludedBackSpær(lengthOfCarport, 60), Type.LENGTH);
            }
        }
        return l;
    }

    public LineItem roof(int length, Roof roof) {
        LineItem l = null;
        String desc = "Tagplader monteres på spær";
        for (int i = 260; i <= 770; i += 30) {
            if (length + 20 == i) {
                l = new LineItem(roof, tagpladeAntal(length), desc, roof.getPrice()*tagpladeAntal(length));
            }
        }
        return l;
    }

    public LineItem spærForRemmen(int length) throws NoSuchMaterialException {
        LineItem l = null;
        Material m;

        String desc = "Remme i sider, sadles ned i stolper";

        for (int i = 240; i <= 750; i += 30) {
            if (length == i) {
                m = LogicFacade.getInstance().getMaterial("45x195mm spærtræ. ubh. ", i);
                l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice() * calculateQuantityOfSpærForRemmen(), Type.LENGTH);
            }
        }
        return l;
    }

    public LineItem understernbrædderForFrontAndBack(int width) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("25x200mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfUndersternbrædderForFrontAndBack(width), "understernbrædder til forende og bagende", m.getPrice() * calculateQuantityOfUndersternbrædderForFrontAndBack(width), Type.LENGTH);
    }

    public LineItem understernBrædderForSides(int length) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("25x200mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfUndersternbrædderForSides(length), "understernbrædder til siderne", m.getPrice() * calculateQuantityOfUndersternbrædderForSides(length), Type.LENGTH);
    }

    public LineItem oversternbrædderForFront(int width) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("25x125m trykimp. brædt");
        return new LineItem(m, calculateQuantityOfOversternbrædderForFront(width), "oversternbrædder for siderne", m.getPrice() * calculateQuantityOfOversternbrædderForFront(width), Type.LENGTH);
    }

    public LineItem oversternbrædderForSides(int length) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("25x125m trykimp. brædt");
        return new LineItem(m, calculateQuantityOfOversternbrædderForSides(length), "oversternbrædder for siderne", m.getPrice() * calculateQuantityOfOversternbrædderForSides(length), Type.LENGTH);
    }

    public LineItem vandbrætForFront(int width) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("19x100mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfVandbrætForFront(width), "vandbrædt på stern i forenden", m.getPrice() * calculateQuantityOfVandbrætForFront(width), Type.LENGTH);
    }

    public LineItem vandbrætForSides(int length) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("19x100mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfVandbrædtForSides(length), "vandbrædt på stern i siderne", m.getPrice() * calculateQuantityOfVandbrædtForSides(length), Type.LENGTH);
    }

    public LineItem hulbånd(Request req) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("hulbånd 1x20 mm. 10 mtr.");
        return new LineItem(m, (int) hulbåndAntal(req), "Til vindkryds på spær", m.getPrice() * (int) hulbåndAntal(req), Type.NOLENGTH);
    }
}
