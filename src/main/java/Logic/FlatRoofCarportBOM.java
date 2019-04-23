/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.IMaterialMapper;
import Data.LineItem;
import Data.Material;

/**
 *
 * @author Kasper Jeppesen
 */
public class FlatRoofCarportBOM
{
    /**
     * 
     * @param length
     * @return the amount of packages of bracket screws needed for the carport. 
     */
    public int calculateQuantityOfBeslagskruer(int length)
    {
        //Der skal som minimum bruges 1 pakke. 
        int packsNeeded = 1;
        int quantity = 0;
        
        // Tre sider per beslag. 
        int sidesOfVenstreBeslag = calculateQuantityOfVenstrebeslag(length)*3;
        int sidesOfHøjreBeslag = calculateQuantityOfHøjreBeslag(length)*3;
        
        //Tre beslagsskruer per side.
        quantity = (sidesOfHøjreBeslag + sidesOfVenstreBeslag)*3;
        
        //2 beslagskruer for each spær the hulbånd crosses and the hulbånd does not croos front or back spær, therefor minus 2
        int quantityOfSpær = calculateQuantityOfSpær(length)-2;
        
        //*2 fordi der er to hulbånd og *2 fordi der er to skruer per beslag. 
        quantity += quantityOfSpær*2 * 2;
        
        //250 is the amount of beslagskruer in a pack
        while(quantity > 250)
        {
            packsNeeded++;
            quantity -= 250;
        }
        return packsNeeded;
    }
    public int calculateQuantityOfBrædderbolt(int length)
    {
        //two brædderbolte per stolpe
        return calculateQuantityOfStolper(length)*2;
    }
    
     public int calculateQuantityOfHøjreBeslag(int length)
    {
        //one venstrebeslag per spær
        return calculateQuantityOfSpær(length);
    }
    public int calculateQuantityOfVenstrebeslag(int length)
    {
        //one venstrebeslag per spær
        return calculateQuantityOfSpær(length);
    }
   
    public int calculateQuantityOfUndersternbrædderForFrontAndBack(int width)
    {
        int quantity = 0;
        
        if(width < 250)
        {
            // 1 is enough for each side
            quantity = 2;
        }
        if(width >= 250 && width <= 500)
        {
            // 2 is needed for each side
            quantity =4;
        }
        if(width > 500 && width < 750)
        {
            quantity = 6;
        }
        return quantity;
    }
    /**
     * 
     * @param length
     * @return 
     */
    public int calculateQuantityOfUndersternbrædderForSides(int length)
    {
        int quantity = 0;
        
        if(length < 250)
        {
            // 1 is enough for each side
            quantity = 2;
        }
        if(length >= 250 && length <= 500)
        {
            // 2 is needed for each side
            quantity =4;
        }
        if(length > 500 && length < 750)
        {
            // 3 is needed for each side
            quantity = 6;
        }
        return quantity;
    }
    public int calculateQuantityOfOversternbrædderForFront(int width)
    {
        //divided by 2 because only the front is needed, not the back
        return calculateQuantityOfUndersternbrædderForFrontAndBack(width) / 2;
    }
    public int calculateQuantityOfOversternbrædderForSides(int length)
    {
        //amount of oversternbrædder is eqaul to the amount of understernbrædder
        return calculateQuantityOfUndersternbrædderForSides(length);
    }
    public int calculateQuantityOfVandbrætForFront(int width)
    {
        //divided by 2 because only the front is needed, not the back
        return calculateQuantityOfUndersternbrædderForFrontAndBack(width) / 2;
    }
    public int calculateQuantityOfVandbrædtForSides(int length)
    {
        //amount of oversternbrædder is eqaul to the amount of understernbrædder
        return calculateQuantityOfUndersternbrædderForSides(length);
    }
    
    /**
     * 
     * @param length
     * @return 
     */
    public int calculateQuantityOfStolper(int length)
    {
        //check if carport is to small to have other stolper than the corner stolpe
        if(length <= 250)
        {
            //the amount of cornerStolper
            return 4;
        }
        
        // foure cornerStolper
        int quantity = 4;
        float widthOfStolpe = 9.7f;
        int numberOfSpacesBewteenStolpe = 1;
        
        //get the space between stolpe at one of the sides (in cm)
        float SpaceBetweenStolpe = length - (widthOfStolpe * (quantity/2)); 
        //250 is the limit of how much space must be between stolper
        while(SpaceBetweenStolpe > 250)
        {
            // add a stolpe for each side
            quantity = quantity + 2;
            numberOfSpacesBewteenStolpe = numberOfSpacesBewteenStolpe * 2;
            
            SpaceBetweenStolpe = (length - (widthOfStolpe * (quantity/2))) / numberOfSpacesBewteenStolpe;
        }
        return quantity;
    }
    
    
    public int calculateQuantityOfSpær(int length)
    {
        //the front and back spær
        int quantity = 2;
        float widthOfSpær = 4.5f;
        
        // This is the first spær which get added between the front spær and back spær
        int lastAddedSpær = 1;
        quantity += lastAddedSpær;
        // 2 = There is a space between the middle spær to the front spær, and a space between the middle spær and back spær
        int numberOfSpacesBetweenSpær = 2;
        float spaceBetweenSpær = (length - (widthOfSpær * quantity)) / numberOfSpacesBetweenSpær;
        
        // 75 is the limit of how much space must be between spærene
        while(spaceBetweenSpær > 75)
        {
            //number of added spær gets doubled everty time
            lastAddedSpær *= 2;
            quantity += lastAddedSpær;
            // as a result the amount of spaces between spær also gets doubled every time
            numberOfSpacesBetweenSpær = numberOfSpacesBetweenSpær*2;
            spaceBetweenSpær = (length - (widthOfSpær * quantity)) / numberOfSpacesBetweenSpær;
        }
        return quantity;
    }
    public int calculateQuantityOfSpærForRemmen()
    {
        //1 spær is used for each side of the carport
        return 2;
    }
    
    public LineItem beslagskruer(int length) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("4.0x50mm beslagskruer 250 stk");
        return new LineItem(m, calculateQuantityOfBeslagskruer(length), "Til montering af universalbeslag + hulbånd", m.getPrice()*calculateQuantityOfBeslagskruer(length));
    }
    
    public LineItem brædderbolt(int length) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("10x120mm brædderbolt");
        return new LineItem(m, calculateQuantityOfBrædderbolt(length), "Til montering af rem på stolper", m.getPrice()*calculateQuantityOfBrædderbolt(length));
    }
 
    public LineItem højrebeslag(int length) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("universal 190mm højre");
        return new LineItem(m, calculateQuantityOfHøjreBeslag(length), "Til montering af spær på rem", m.getPrice()*calculateQuantityOfHøjreBeslag(length));
    }
//    
    public LineItem venstrebeslag(int length) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("universal 190mm venstre");
        return new LineItem(m, calculateQuantityOfVenstrebeslag(length), "Til montering af spær på rem", m.getPrice()*calculateQuantityOfVenstrebeslag(length));
    }
//    
    public LineItem stolpe(int length) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("97x97mm trykimp. Stolpe");
        return new LineItem(m, calculateQuantityOfStolper(length), "Stolper, nedgraves 90cm i jord", m.getPrice()*calculateQuantityOfStolper(length));
        
    }
    
    
    public LineItem spær(int length, int width) throws NoSuchMaterialException
    {
        LineItem l = null;
        Material m;
        
        String desc = "Spær, monteres på rem";
        
         if(width == 240)
        {
             m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 240);
             l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 270)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 270);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 300)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 300);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 330)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 330);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 360)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 360);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 390)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 390);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 420)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 420);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 450)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 450);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 480)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 480);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 510)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 510);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 540)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 540);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 570)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 570);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 600)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 600);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 630)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 630);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 660)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 660);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 690)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 690);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 720)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 720);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        if(width == 750)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 750);
            l = new LineItem(m, calculateQuantityOfSpær(length), desc, m.getPrice()*calculateQuantityOfSpær(length));
        }
        
      
        return l;
    }
    public LineItem spærForRemmen(int length) throws NoSuchMaterialException
    {
        LineItem l = null;
        Material m;
        
        String desc = "Remme i sider, sadles ned i stolper";
        
        if(length == 240)
        {
             m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 240);
             l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 270)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 270);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 300)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 300);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 330)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 330);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 360)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 360);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 390)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 390);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 420)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 420);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 450)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 450);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 480)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 480);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 510)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 510);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 540)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 540);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 570)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 570);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 600)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 600);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 630)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 630);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 660)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 660);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 690)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 690);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 720)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 720);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
        if(length == 750)
        {
            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. ", 750);
            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), desc, m.getPrice()*calculateQuantityOfSpærForRemmen());
        }

        return l;
    }
    public LineItem understernbrædderForFrontAndBack(int width) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("25x200mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfUndersternbrædderForFrontAndBack(width), "understernbrædder til forende og bagende", m.getPrice()*calculateQuantityOfUndersternbrædderForFrontAndBack(width));
    }
    public LineItem understernBrædderForSides(int length) throws NoSuchMaterialException
    {
         Material m = IMaterialMapper.instance().getMaterial_("25x200mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfUndersternbrædderForSides(length), "understernbrædder til siderne", m.getPrice()*calculateQuantityOfUndersternbrædderForSides(length));
    }
    public LineItem oversternbrædderForFront(int width) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("25x125m trykimp. brædt");
        return new LineItem(m, calculateQuantityOfOversternbrædderForFront(width), "oversternbrædder for siderne", m.getPrice()*calculateQuantityOfOversternbrædderForFront(width));
    }
    public LineItem oversternbrædderForSides(int length) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("25x125m trykimp. brædt");
        return new LineItem(m, calculateQuantityOfOversternbrædderForSides(length), "oversternbrædder for siderne", m.getPrice()*calculateQuantityOfOversternbrædderForSides(length));
    }
    public LineItem vandbrætForFront(int width) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("19x100mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfVandbrætForFront(width), "vandbrædt på stern i forenden", m.getPrice()*calculateQuantityOfVandbrætForFront(width));
    }
    public LineItem vandbrætForSides(int length) throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("19x100mm trykimp. brædt");
        return new LineItem(m, calculateQuantityOfVandbrædtForSides(length), "vandbrædt på stern i siderne", m.getPrice()*calculateQuantityOfVandbrædtForSides(length));
    }
}
