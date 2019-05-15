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
import Logic.Exceptions.NoSuchMaterialException;

/**
 *
 * @author Kasper Jeppesen
 */
public class BOMFlatRoof {

    //------------------------------- calculate methods for fittings and screws ------------------------------------------------------------------------
    
    /**
     * @param c
     * @return the amount of packages of bracket screws (beslag skruer) needed for the carport.
     */
    public int calculateQuantityOfBeslagskruer(Carport c) 
    {
        //At minimun one package is needed
        int packsNeeded = 1;
        int quantity = 0;
        
        //---- bracket screws (beslagskruer) used for bracket interties (beslag) -------
        
        // Three sides per brackets Interties (beslag)
        int sidesOfVenstreBeslag = calculateQuantityOfVenstrebeslag(c) * 3;
        int sidesOfHøjreBeslag = calculateQuantityOfHøjreBeslag(c) * 3;
        
        //Three brackes screws for each side
        quantity = (sidesOfHøjreBeslag + sidesOfVenstreBeslag) * 3;
        
        //--- bracket screws (beslagskruer) used for perforated band (hulbånd) -----
        
        //Two bracket screws (beslagskruer) for each rafter (spær) the perforated band (hulbånd) crosses
        //The perforated band (hulb¨nd) does not cross front or back rafter (spær) therefor minus 2
        int quantityOfSpær = calculateQuantityOfSpærIncludedBackSpær(c, 60) - 2;

        //*2 because there is two perforated bands (hulbånd) and *2 because two bracket screws (beslagskruer) for each bracket interties (beslag)
        quantity += quantityOfSpær * 2 * 2;
        
        //250 is the amount of beslagskruer in a pack
        while (quantity > 250) 
        {
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
    public int calculateQuantityOfBrædderbolt(Carport c) 
    {
        //two boards bolts (brædderbolte) for each post (stolpe)
        return calculateQuantityOfStolper(c) * 2;
    }

    /**
     * 
     * @param c
     * @return the amount of right bracket interties (højre beslag) needed for the carport
     */
    public int calculateQuantityOfHøjreBeslag(Carport c) 
    {
        //one right bracket interties (højre beslag) per rafter (spær)
        return calculateQuantityOfSpærIncludedBackSpær(c, 60);
    }

    /**
     * 
     * @param c
     * @return the amount of left bracket interties (venstre beslag) needed for the carport
     */
    public int calculateQuantityOfVenstrebeslag(Carport c) 
    {
        //one left bracket interties (venstre beslag) per rafter (spær)
        return calculateQuantityOfSpærIncludedBackSpær(c, 60);
    }
    
    //--------------------- methods for returning items in the category fittins and screw -----------------------------------------------------------

    /**
     * 
     * @param c
     * @return a LineItem of bracket screws (beslagskruer)
     * @throws NoSuchMaterialException 
     */
    public LineItem beslagskruer(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("4.0x50mm beslagskruer 250 stk");
        return new LineItem(m, calculateQuantityOfBeslagskruer(c), "Til montering af universalbeslag + hulbånd", m.getPrice() * calculateQuantityOfBeslagskruer(c), Type.NOLENGTH);
    }
     
    /**
     * 
     * @param c
     * @return LineItem of boards bolts (brædderbolte) 
     * @throws NoSuchMaterialException 
     */
    public LineItem brædderbolt(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("10x120mm brædderbolt");
        return new LineItem(m, calculateQuantityOfBrædderbolt(c), "Til montering af rem på stolper", m.getPrice() * calculateQuantityOfBrædderbolt(c), Type.NOLENGTH);
    }
    
    /**
     * 
     * @param c
     * @return LineItem of right bracket interties (højre beslag)
     * @throws NoSuchMaterialException 
     */
    public LineItem højrebeslag(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("universal 190mm højre");
        return new LineItem(m, calculateQuantityOfHøjreBeslag(c), "Til montering af spær på rem", m.getPrice() * calculateQuantityOfHøjreBeslag(c), Type.NOLENGTH);
    }
    
    /**
     * 
     * @param c
     * @return LineItem of left bracket interties (venstre beslag)
     * @throws NoSuchMaterialException 
     */
    public LineItem venstrebeslag(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("universal 190mm venstre");
        return new LineItem(m, calculateQuantityOfVenstrebeslag(c), "Til montering af spær på rem", m.getPrice() * calculateQuantityOfVenstrebeslag(c), Type.NOLENGTH);
    }
    
    
    
    //------------------------------------- calculate methods for tree-fundament ------------------------------------------------------------
    
    /**
     * 
     * @param c
     * @param limit - the limit of how much space must be between each rafter (spær)
     * @return amount of rater (spær) needed for the carport
     */
    public int calculateQuantityOfSpærIncludedBackSpær(Carport c, int limit)
    {
        int quantity = calculateQuantityOFSpærExcluedBackSpær(c, limit) + 1;
        return quantity;
    }
    
    /**
     * Used by calculateQuantityOfSpærExcludedBackSpær() and spaceBetweenSpær() to calculate the space between spær
     * @param c
     * @param quantityOfSpær
     * @return 
     */
    private float calculateSpaceBetweenSpær(Carport c ,int quantityOfSpær)
    {
        float widthOfSpær = 4.5f;
        
        float spaceOfAllSpær = quantityOfSpær * widthOfSpær;
        float spaceAviable = c.getLength() - spaceOfAllSpær;
        float spaceBetweenSpær = spaceAviable / quantityOfSpær;
        
        return spaceBetweenSpær;
    }
    
    /**
     * This method is used to DrawSVG as amount of raters (spær) excluded the back raft is eqaul to amount of spaces between rafter (spær)
     * @param c
     * @param limit - the limit of how much space must be between each rafter (spær)
     * @return amount of rafter (spær) minus the back rafter (bagspæret) 
     */
    public int calculateQuantityOFSpærExcluedBackSpær(Carport c, int limit)
    {
        //Amount of rafter (spær) needed - Integer cut of any decimal, therefor if the division eqauls example 7,5, then the interger hold 7
        int quantity = c.getLength() / limit;
        
        float spaceBetweenSpær = calculateSpaceBetweenSpær(c, quantity);
        //Since the interget cut of any decimal, the quantity used to calculate the space between rafter (spær) could lead to a case where the space
        // between rafter (spær) was too high, and if so the quantity get added by 1. (so in the example instead of 7, we want it to be 8)
        if(spaceBetweenSpær > limit)
        {
            quantity++;
        }
        
        return quantity ;
    }
    
    /**
     * This method is used to drawSVG 
     * @param c
     * @param quantityOfSpærExcludedBackSpær
     * @param limit
     * @return the space between spær
     */
    public float spaceBetweenSpær(Carport c,  int limit)
    {
        float spaceBetweenSpær = calculateSpaceBetweenSpær(c, calculateQuantityOFSpærExcluedBackSpær(c, limit));
        
        
        if(spaceBetweenSpær > limit)
        {
            int quantityOfSpærExcludedBackSpær = calculateQuantityOFSpærExcluedBackSpær(c, limit) + 1;
            spaceBetweenSpær = calculateSpaceBetweenSpær(c, quantityOfSpærExcludedBackSpær);
        }
        
        return spaceBetweenSpær;
    }
    
    /**
     * 
     * @param c
     * @return the amount of post(stolper) needed for the carport
     */
    public int calculateQuantityOfStolper(Carport c) 
    {
        //Substraicing spaceBetweenSpær times 2 because post (stolper) cant appear at the front spear and back spear
        float lengthAvaiableForPost = c.getLength() - spaceBetweenSpær(c, 60) * 2;
        // four corner stolper
        int quantity = 4;

        if (c.getShed_() != null) 
        {
            
            lengthAvaiableForPost -= c.getShed_().getLength();
            
            if(c.getWidth() - c.getShed_().getWidth() == 30)
            {
                //A shed consists of 6 post(stolper), the 2 back corner of the carport, aswell as two front post for the shed abd two middle post for the shed
                quantity += 4;
            }
            else
            {
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
     * the length of a underneath borad is 250cm?? so one board can cover up 250 of a carport width
     * @param c
     * @return the amount of underneath board (understern brædder) for the front, is needed for the carport
     */
    public int calculateQuantityOfUndersternbrædderForFrontAndBack(Carport c) 
    {
        int quantity = 0;

        if (c.getWidth() < 250) 
        {
            // 1 is enough for each side
            quantity = 2;
        }
        if (c.getWidth() >= 250 && c.getWidth() <= 500) 
        {
            // 2 is needed for each side
            quantity = 4;
        }
        if (c.getWidth() > 500 && c.getWidth() < 750) 
        {
            //3 is needed for each side
            quantity = 6;
        }
        return quantity;
    }

    /**
     * the length of a underneath  board (understern brædt) is 250cm?? so one board can cover 250cm of the carport length
     * @param c
     * @return the amount of underneath boards (understern brædder) for the sides, needed for the carport
     */
    public int calculateQuantityOfUndersternbrædderForSides(Carport c) 
    {
        int quantity = 0;

        if (c.getLength() < 250) 
        {
            // 1 is enough for each side
            quantity = 2;
        }
        if (c.getLength() >= 250 && c.getLength() <= 500) 
        {
            // 2 is needed for each side
            quantity = 4;
        }
        if (c.getLength() > 500 && c.getLength() < 750) 
        {
            // 3 is needed for each side
            quantity = 6;
        }
        return quantity;
    }

    /**
     * 
     * @param c
     * @return the amount of outer boards (overstern brædder) for the front, needed for the carport
     */
    public int calculateQuantityOfOversternbrædderForFront(Carport c) 
    {
        /*
        The amount of outer board (overstern brædder) for the front is equal to the amount of underneath board (understern brædder) for the front and back, 
        divided by 2 because outer board is only for the front
        */
        return calculateQuantityOfUndersternbrædderForFrontAndBack(c) / 2;
    }

    /**
     * 
     * @param c
     * @return the amount of outer boards (overstern brædder) for the sides, needed for the carport
     */
    public int calculateQuantityOfOversternbrædderForSides(Carport c) 
    {
        //the amount of outer boards (overstern brædder) is eqaul to the amount of underneath boards (undertern brædder)
        return calculateQuantityOfUndersternbrædderForSides(c);
    }

    /**
     * 
     * @param c
     * @return the amount of water boards (vandbræt) for the front. needed for the carport
     */
    public int calculateQuantityOfVandbrætForFront(Carport c) 
    {
         /*
        The amount of water boards (vandbræt) for the front is equal to the amount of underneath board (understern brædder) for the front and back, 
        divided by 2 because water board (vandbræt) is only for the front
        */
        return calculateQuantityOfUndersternbrædderForFrontAndBack(c) / 2;
    }

    /**
     * 
     * @param c
     * @return the amount of water boards (vandbræt) for the sides, needed for the carport
     */
    public int calculateQuantityOfVandbrædtForSides(Carport c) 
    {
        //amount of water boards (vandbræt) is eqaul to the amount of underneath boards (understernbrædder)
        return calculateQuantityOfUndersternbrædderForSides(c);
    }
    
    //--------------- methods for returning items in the category tree-fundament -------------------------------------------------------------------

    /**
     * 
     * @param c
     * @return  LineItem of rafters (spær)
     * @throws NoSuchMaterialException 
     */
     public LineItem spær(Carport c) throws NoSuchMaterialException 
     {
        LineItem l = null;
        Material m;

        String desc = "Spær, monteres på rem";

        for (int i = 240; i <= 750; i += 30) 
        {
            if (c.getLength() == i) 
            {
                m = LogicFacade.getInstance().getMaterial("45x195mm spærtræ. ubh. ", i);
                l = new LineItem(m, calculateQuantityOfSpærIncludedBackSpær(c, 60), desc, m.getPrice() * calculateQuantityOfSpærIncludedBackSpær(c, 60), Type.LENGTH);
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
    public LineItem stolpe(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("97x97mm trykimp. Stolpe");
        return new LineItem(m, calculateQuantityOfStolper(c), "Stolper, nedgraves 90cm i jord", m.getPrice() * calculateQuantityOfStolper(c), Type.LENGTH);

    }
    
    /**
     * 
     * @param c
     * @return LineItem of underneath boards (understern brædder) for the front
     * @throws NoSuchMaterialException 
     */
    public LineItem understernbrædderForFrontAndBack(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("25x200mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfUndersternbrædderForFrontAndBack(c), "understernbrædder til forende og bagende", m.getPrice() * calculateQuantityOfUndersternbrædderForFrontAndBack(c), Type.LENGTH);
    }

    /**
     * 
     * @param c
     * @return LineItem of underneath boards (understern brædder) for the sides
     * @throws NoSuchMaterialException 
     */
    public LineItem understernBrædderForSides(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("25x200mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfUndersternbrædderForSides(c), "understernbrædder til siderne", m.getPrice() * calculateQuantityOfUndersternbrædderForSides(c), Type.LENGTH);
    }

    /**
     * 
     * @param c
     * @return LineItem of outer boards (overstern brædder) for the front
     * @throws NoSuchMaterialException 
     */
    public LineItem oversternbrædderForFront(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("25x125m trykimp. brædt");
        return new LineItem(m, calculateQuantityOfOversternbrædderForFront(c), "oversternbrædder for siderne", m.getPrice() * calculateQuantityOfOversternbrædderForFront(c), Type.LENGTH);
    }

    /**
     * 
     * @param c
     * @return LineItem of outer boards (overstern brædder) for the sides
     * @throws NoSuchMaterialException 
     */
    public LineItem oversternbrædderForSides(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("25x125m trykimp. brædt");
        return new LineItem(m, calculateQuantityOfOversternbrædderForSides(c), "oversternbrædder for siderne", m.getPrice() * calculateQuantityOfOversternbrædderForSides(c), Type.LENGTH);
    }

    /**
     * 
     * @param c
     * @return LineItem of water boards (vandbræt) for the front
     * @throws NoSuchMaterialException 
     */
    public LineItem vandbrætForFront(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("19x100mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfVandbrætForFront(c), "vandbrædt på stern i forenden", m.getPrice() * calculateQuantityOfVandbrætForFront(c), Type.LENGTH);
    }

    /**
     * 
     * @param c
     * @return LineItem of water boards (vandbræt) for the sides
     * @throws NoSuchMaterialException 
     */
    public LineItem vandbrætForSides(Carport c) throws NoSuchMaterialException 
    {
        Material m = LogicFacade.getInstance().getMaterial_("19x100mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfVandbrædtForSides(c), "vandbrædt på stern i siderne", m.getPrice() * calculateQuantityOfVandbrædtForSides(c), Type.LENGTH);
    }
    
    /**
     * 
     * @param length
     * @return the amount of rater for strap (spær for remmen) 
     * @throws NoSuchMaterialException 
     */
    public LineItem spærForRemmen(Carport c) throws NoSuchMaterialException 
    {
        LineItem l = null;
        Material m;

        String desc = "Remme i sider, sadles ned i stolper";

        for (int i = 240; i <= 750; i += 30) 
        {
            if (c.getLength() == i) 
            {
                m = LogicFacade.getInstance().getMaterial("45x195mm spærtræ. ubh. ", i);
                l = new LineItem(m, 2, desc, m.getPrice() * 2, Type.LENGTH);
            }
        }
        return l;
    }
    
    //------------------------- -----------calculation methods for the roof ---------------------------------------------------------------------

    static int tagpladeAntal(Carport c) 
    {
        return c.getLength() / 30; // widt of a single roof tile is 30 cm. 
        
    }
    
    //------------------------------- methods for returning items of category roof --------------------------------------------------------------
     
    public LineItem roof(Carport c) 
    {
        LineItem l = null;
        String desc = "Tagplader monteres på spær";
        for (int i = 260; i <= 770; i += 30) 
        {
            if (c.getLength() + 20 == i) 
            {
                l = new LineItem(c.getRoof(), tagpladeAntal(c), desc, c.getRoof().getPrice()*tagpladeAntal(c));
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
    public double hulbåndAntal(Carport c) {
        double lengthM;
        // if there is a toolshed, the length of the toolshed must be subtracted from the carport length. 
        if (c.getShed_()!=null) {
            lengthM = c.getLength() - c.getShed_().getLength() - spaceBetweenSpær(c, 60) / 100;
        } else {
            //length in meters
            lengthM = (c.getLength() - spaceBetweenSpær(c, 60)) / 100;
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
     * @return LineItem of bands (hulbånd)
     * @throws NoSuchMaterialException 
     */
     public LineItem hulbånd(Carport c) throws NoSuchMaterialException {
        Material m = LogicFacade.getInstance().getMaterial_("hulbånd 1x20 mm. 10 mtr.");
        return new LineItem(m, (int) hulbåndAntal(c), "Til vindkryds på spær", m.getPrice() * (int) hulbåndAntal(c), Type.NOLENGTH);
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
