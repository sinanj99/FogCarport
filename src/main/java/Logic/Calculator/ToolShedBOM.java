/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Mappers.IMaterialMapper;
import Data.Entity.LineItem;
import Data.Entity.Material;
import Data.Entity.Type;
import Logic.Exceptions.NoSuchMaterialException;

/**
 *
 * @author Kasper Jeppesen
 */
public class ToolShedBOM
{
    public int calculateQuantityForBeklædning(int widthOfShed, int lengthOfShed)
    {
        return calculateQuantityForBeklædning1(widthOfShed, lengthOfShed) + 
                calculateQuantityForBeklædning2(widthOfShed, lengthOfShed);
        
    }
    
    public int calculateQuantityForBeklædning1(int widthOfShed, int lengthOfShed)
    {
        int widthOfBrædt = 10;
        int spaceBetweenBrædt = 6;
        
        // --- calculate amount of brædt needed for beklædning of first lag --- 
        
        // one brædt for every 15cm for each side.
        int brædtNeededForWidth = (widthOfShed / (widthOfBrædt + spaceBetweenBrædt)) * 2;
        int brædtNeededForLength = (lengthOfShed / (widthOfBrædt + spaceBetweenBrædt)) * 2;
        
        int quantityOfBrædtNeededForFirstLag = brædtNeededForWidth + brædtNeededForLength;
        
        return quantityOfBrædtNeededForFirstLag;
        
    }
        public int calculateQuantityForBeklædning2(int widthOfShed, int lengthOfShed)
    {
        int widthOfBrædt = 10;
        int spaceBetweenBrædt = 6;
        
        // --- calculate amount of brædt needed for beklædning of second lag ---
        
        //The starting point for the first brædt for this lag is widthOfBrædt - spaceBetweenbrædt / 2.
        int widthOfSecondLag = widthOfShed - (widthOfBrædt - spaceBetweenBrædt / 2); // widthOfShed - 7,50 
        int lengthOfSecondLag = lengthOfShed - (widthOfBrædt - spaceBetweenBrædt / 2); // lenghtOfShed - 7,50
        
        int brædtNeededForSecondLagWidth = (widthOfSecondLag / (widthOfBrædt + spaceBetweenBrædt)) * 2;
        int brædtNeededForSecondLagLength = (lengthOfSecondLag / (widthOfBrædt + spaceBetweenBrædt)) * 2;
                
        int quantityOfBrædtNeededForSecondLag = brædtNeededForSecondLagWidth + brædtNeededForSecondLagLength;

        return quantityOfBrædtNeededForSecondLag;
        
    }
    
    public LineItem beklædning(int widthOfShed, int lengthOfShed) throws NoSuchMaterialException
    {
         Material m = IMaterialMapper.instance().getMaterial("19x100mm trykimp. brædt", 210);
         return new LineItem(m, calculateQuantityForBeklædning(widthOfShed, lengthOfShed), "Til beklædning af skur 1 på 2", m.getPrice()*calculateQuantityForBeklædning(widthOfShed, lengthOfShed), Type.LENGTH);
    }
    
    public LineItem løsholterGalve(int widthOfToolShed) throws NoSuchMaterialException
    {
        LineItem l = null;
        Material m;
        
        for(int i = 150; i <= 510; i += 30)
        {
            if(widthOfToolShed == i)
            {
                m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", i);
                l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12, Type.LENGTH);
            }
        }
        return l;
    }
    
    public LineItem løsholterForSides(int lengthOfToolShed) throws NoSuchMaterialException
    {
        LineItem l = null;
        Material m;
        
        for(int i = 150; i < 540; i += 30)
        {
            if(lengthOfToolShed == i)
            {
                m = IMaterialMapper.instance().getMaterial("45x95mm reglar. ub.", i);
                l = new LineItem(m, 4, "løsholter til skur siderne", m.getPrice()*4, Type.LENGTH);
            }
        }
        return l;
    }
    
    public LineItem lægteForDoor() throws NoSuchMaterialException
    {
         Material m = IMaterialMapper.instance().getMaterial_("38x73mm lægte. ubh.");
         return new LineItem(m, 1, "Til z på bagside af dør", m.getPrice(), Type.LENGTH);
    }
    public LineItem stalddørsgreb() throws NoSuchMaterialException
    {
         Material m = IMaterialMapper.instance().getMaterial_("50x75mm stalddørsgreb");
         return new LineItem(m, 1, "Til løs på dør i skur", m.getPrice(), Type.NOLENGTH);
    }
    public LineItem tHængsel() throws NoSuchMaterialException
    {
         Material m = IMaterialMapper.instance().getMaterial_("390mm t-hængsel");
         return new LineItem(m, 2, "Til dør i skur", m.getPrice()*2, Type.NOLENGTH);
    }
    public LineItem vinkelbeslag() throws NoSuchMaterialException
    {
        Material m = IMaterialMapper.instance().getMaterial_("vinkelbeslag 35");
        return new LineItem(m, 32, "Til montering af løsholter til skur", m.getPrice()*32, Type.NOLENGTH);
    }
  }
