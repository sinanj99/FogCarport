/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.LineItem;
import Data.Material;
import Data.MaterialDAO;

/**
 *
 * @author Kasper Jeppesen
 */
public class CalculateBOM
{
    
    
    public int calculateQuantityOfSpær(int length)
    {
        //Convert the input containingen meter as the unit to centimeter
        int lengthInCM = length * 100;
        int quantity = 2;
        float widthOfSpær = 4.5f;
        
        //gets the space between the front Spær and the last Spær
        float spaceBetweenSpær = lengthInCM - (widthOfSpær * quantity);
        
        // This is the first spær which get added between the front spær and back spær
        int lastAddedSpær = 1;
        quantity = quantity + lastAddedSpær;
        // 2 = There is a space between the middle spær to the front spær, and a space between the middle spær and back spær
        int numberOfSpacesBetweenSpær = 2;
        spaceBetweenSpær = (lengthInCM - (widthOfSpær * quantity)) / numberOfSpacesBetweenSpær;
        
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
    
    
    public LineItem spær(int length, int width)
    {
        LineItem l = null;
        Material m;
        
        //width of the carport less than 2, get the material with length 2
        if(width <= 2)
        {
             m = MaterialDAO.getMaterial("45x195mm spærtræ. ubh. 2m");
            l = new LineItem(m.getMaterialName(), m.getLength(), m.getUnit(), m.getDescription(), calculateQuantityOfSpær(length));
        }
        //width of the carport less than 4, get the material with length 4
        if(width > 2 && width <= 4)
        {
            m = MaterialDAO.getMaterial("45x195mm spærtræ. ubh. 4m");
            l = new LineItem(m.getMaterialName(), m.getLength(), m.getUnit(), m.getDescription(), calculateQuantityOfSpær(length));
        }
         //width of the carport less than 6, get the material with length 6
        if(width > 4 && width <=6)
        {
            m = MaterialDAO.getMaterial("45x195mm spærtræ. ubh. 6m");
            l = new LineItem(m.getMaterialName(), m.getLength(), m.getUnit(), m.getDescription(), calculateQuantityOfSpær(length));
        }
        //width of the carport less than 8, get the material with length 8
        if(width > 6 && width <=8)
        {
            m = MaterialDAO.getMaterial("45x195mm spærtræ. ubh. 8m");
            l = new LineItem(m.getMaterialName(), m.getLength(), m.getUnit(), m.getDescription(), calculateQuantityOfSpær(length));
        }
        return l;
    }
}
