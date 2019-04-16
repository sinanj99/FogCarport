/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.LineItem;
import Data.Material;
import Data.IMaterialMapper;

/**
 *
 * @author Kasper Jeppesen
 */
public class CalculateBOM
{
    public int calculateQuantityOfBrædderbolt(float length)
    {
        //two brædderbolte per stolpe
        return calculateQuantityOfStolper(length)*2;
    }
     public int calculateQuantityOfHøjreBeslag(float length)
    {
        //one venstrebeslag per spær
        return calculateQuantityOfSpær(length);
    }
    public int calculateQuantityOfVenstrebeslag(float length)
    {
        //one venstrebeslag per spær
        return calculateQuantityOfSpær(length);
    }
   
    
    //carport must be of atleast length 2m and max 8m
    public int calculateQuantityOfStolper(float length)
    {
        //Convert the input containingen meter as the unit to centimeter
        float lengthInCM = length * 100;
        
        //check if carport is to small to have other stolper than the corner stolpe
        if(lengthInCM <= 250)
        {
            //the amount of cornerStolper
            return 4;
        }
        
        // foure cornerStolper
        int quantity = 4;
        float widthOfStolpe = 9.7f;
        int numberOfSpacesBewteenStolpe = 1;
        
        //get the space bewteen stolpe at one of the side
        float SpaceBetweenStolpe = lengthInCM - (widthOfStolpe * (quantity/2));
        //250 is the limit og how much space must be between stolper
        while(SpaceBetweenStolpe > 250)
        {
            // add a stolpe for each side
            quantity = quantity + 2;
            numberOfSpacesBewteenStolpe = numberOfSpacesBewteenStolpe * 2;
            
            SpaceBetweenStolpe = (lengthInCM - (widthOfStolpe * (quantity/2))) / numberOfSpacesBewteenStolpe;
        }
        return quantity;
    }
    
    //A carport should be minimun 2m length max 8m
    public int calculateQuantityOfSpær(float length)
    {
        //Convert the input containingen meter as the unit to centimeter
        float lengthInCM = length * 100;
        //the fronst and back spær
        int quantity = 2;
        float widthOfSpær = 4.5f;
        
        // This is the first spær which get added between the front spær and back spær
        int lastAddedSpær = 1;
        quantity = quantity + lastAddedSpær;
        // 2 = There is a space between the middle spær to the front spær, and a space between the middle spær and back spær
        int numberOfSpacesBetweenSpær = 2;
        float spaceBetweenSpær = (lengthInCM - (widthOfSpær * quantity)) / numberOfSpacesBetweenSpær;
        
        // 75 is the limit of how much space must be between spærene
        while(spaceBetweenSpær > 75)
        {
            //number of added spær gets doubled everty time
            lastAddedSpær = lastAddedSpær * 2;
            quantity = quantity + lastAddedSpær;
            // as a result the amount of spaces between spær also gets doubled every time
            numberOfSpacesBetweenSpær = numberOfSpacesBetweenSpær*2;
            spaceBetweenSpær = (lengthInCM - (widthOfSpær * quantity)) / numberOfSpacesBetweenSpær;
        }
        return quantity;
    }
    public int calculateQuantityOfSpærForRemmen()
    {
        //1 spær is used for each side of the carport
        return 2;
    }
    
//    public LineItem brædderbolt(float length) throws NoSuchMaterialException
//    {
//        Material m = IMaterialMapper.instance().getMaterial("10x120mm brædderbolt");
//        return new LineItem(m, calculateQuantityOfBrædderbolt(length), m.getPrice()*calculateQuantityOfBrædderbolt(length));
//    }
//    
//    public LineItem højrebeslag(float length) throws NoSuchMaterialException
//    {
//        Material m = IMaterialMapper.instance().getMaterial("universal 190mm højre");
//        return new LineItem(m, calculateQuantityOfBrædderbolt(length), m.getPrice()*calculateQuantityOfBrædderbolt(length));
//    }
//    
//    public LineItem venstrebeslag(float length) throws NoSuchMaterialException
//    {
//        Material m = IMaterialMapper.instance().getMaterial("universal 190mm venstre");
//        return new LineItem(m, calculateQuantityOfBrædderbolt(length), m.getPrice()*calculateQuantityOfBrædderbolt(length));
//    }
//    
//    public LineItem stolpe(float length) throws NoSuchMaterialException
//    {
//        Material m = IMaterialMapper.instance().getMaterial("97x97mm trykimp. Stolpe");
//        return new LineItem(m, calculateQuantityOfStolper(length), m.getPrice()*calculateQuantityOfStolper(length));
//        
//    }
    
//    public LineItem spær(float length, float width) throws NoSuchMaterialException
//    {
//        LineItem l = null;
//        Material m;
//        
//        //width of the carport less than 2, get the material with length 2
//        if(width <= 2)
//        {
//            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. 2m");
//            l = new LineItem(m, calculateQuantityOfSpær(length), m.getPrice()*calculateQuantityOfSpær(length));
//        }
//        //width of the carport less than 4, get the material with length 4
//        if(width > 2 && width <= 4)
//        {
//            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. 4m");
//            l = new LineItem(m, calculateQuantityOfSpær(length), m.getPrice()*calculateQuantityOfSpær(length));
//        }
//         //width of the carport less than 6, get the material with length 6
//        if(width > 4 && width <=6)
//        {
//            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. 6m");
//            l = new LineItem(m, calculateQuantityOfSpær(length), m.getPrice()*calculateQuantityOfSpær(length));
//        }
//        //width of the carport less than 8, get the material with length 8
//        if(width > 6 && width <=8)
//        {
//            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. 8m");
//            l = new LineItem(m, calculateQuantityOfSpær(length), m.getPrice()*calculateQuantityOfSpær(length));
//        }
//        return l;
//    }
    public LineItem spærForRemmen(float length) throws NoSuchMaterialException
    {
        LineItem l = null;
        Material m;
        
        String desc = "Remme i sider, sadles ned i stolper";
        
        if(length > 6)
        {
             m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. 8m", 800);
             m.setDescription(desc);
             l = new LineItem(m, calculateQuantityOfSpærForRemmen(), m.getPrice()*calculateQuantityOfSpærForRemmen());
        }
//        if(length > 4 && length <= 6)
//        {
//            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. 6m");
//            m.setDescription(desc);
//            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), m.getPrice()*calculateQuantityOfSpærForRemmen());
//        }
//        if(length > 2 && length <= 4)
//        {
//            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. 4m");
//            m.setDescription(desc);
//            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), m.getPrice()*calculateQuantityOfSpærForRemmen());
//        }
//        if(length == 2)
//        {
//            m = IMaterialMapper.instance().getMaterial("45x195mm spærtræ. ubh. 2m");
//            m.setDescription(desc);
//            l = new LineItem(m, calculateQuantityOfSpærForRemmen(), m.getPrice()*calculateQuantityOfSpærForRemmen());
//        }
        return l;
    }
}
