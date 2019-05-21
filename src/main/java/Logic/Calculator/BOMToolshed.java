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
import Data.Entity.Type;
import Logic.Controller.LogicFacade;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.SystemErrorException;

/**
 *
 * @author Kasper Jeppesen
 */
public class BOMToolshed
{
    //---------------------------------------------- calculation methods ----------------------------------------------------------------------
    
    /**
     * This method is used for the method calculateShedClothing
     * @param c
     * @return the amount of planks needed for shed clothing (beklædning) of the first layer of the shed
     */
    private int calculateQuantityForShedClothing1(Carport c)
    {
        //Width of a plank 
        int widthOfPlank = 10;
        //The space, there must be between each plank
        int spaceBetweenPlank = 5;
        
        // one plank for every 15cm for each side (a plank is placed, then there must be a space on 5 before next plank is placed)
        int planksNeededForWidth = (c.getShed_().getWidth() / (widthOfPlank + spaceBetweenPlank)) * 2;
        int planksNeededForLength = (c.getShed_().getLength() / (widthOfPlank + spaceBetweenPlank)) * 2;
        
        return planksNeededForWidth + planksNeededForLength;
    }
    
    /**
     * This method is used for the method calculateShedClothing
     * @param c
     * @return the amount of planks needed for shed clothing (beklædning) of the second layer of the shed
     */
    private int calculateQuantityForShedClothing2(Carport c)
    {
        //width of a plank
        int widthOfPlank = 10;
        //the space, there must be between each plank
        int spaceBetweenPlank = 5;
        
        /*
            substracting widthOfPlank + spaceBetweenPlank from the width and length of the shed because,
            the first plank for this layer start at (widthOfPlank + spaceBetweenPlank) / 2, AKA. 7,50 from the corner of the shed and,
            the last plank for this layer stops at (widthOfPlank + spaceBetweenPæank / 2. AKA 7,50 from the end corner of the shed.
        */
        int widthOfSecondLayer = c.getShed_().getWidth() - (widthOfPlank + spaceBetweenPlank); 
        int lengthOfSecondLayer = c.getShed_().getLength() - (widthOfPlank + spaceBetweenPlank); 
        
        // one plank for every 15cm for each side (a plank is placed, then there must be a space on 5 before next plank is placed)
        int planksNeededForSecondLayerWidth = (widthOfSecondLayer / (widthOfPlank + spaceBetweenPlank)) * 2;
        int planksNeededForSecondLayerLength = (lengthOfSecondLayer / (widthOfPlank + spaceBetweenPlank)) * 2;
                
        return planksNeededForSecondLayerWidth + planksNeededForSecondLayerLength;
    }
    
    /**
     * This methods adds the planks needed for first layer with planks needed for second layer
     * @param c
     * @return the total amount of planks needed for the shed clothing (beklædning)
     */
    private int calculateQuantityForShedClothing(Carport c)
    {
        return calculateQuantityForShedClothing1(c) + calculateQuantityForShedClothing2(c);
    }
    
    
    
    
    //---------------------------------- methods for returning LineItem ----------------------------------------------------------
    
    /**
     * 
     * @param c
     * @return LineItem of shed clothing (beklædning)
     * @throws NoSuchMaterialException 
     * @throws Presentation.Exceptions.SystemErrorException 
     */
    public LineItem shedClothing(Carport c) throws NoSuchMaterialException, SystemErrorException
    {
         Material m = LogicFacade.getInstance().getWoodMaterial(7, 210);
         return new LineItem(m, calculateQuantityForShedClothing(c), "Til beklædning af skur 1 på 2", m.getPrice()*calculateQuantityForShedClothing(c), Type.LENGTH);
    }
    
    /**
     * 
     * @param c
     * @return LineItem of end caps for gables (løsholter for gavle)
     * @throws NoSuchMaterialException 
     * @throws Presentation.Exceptions.SystemErrorException 
     */
    public LineItem endCapsForGables(Carport c) throws NoSuchMaterialException, SystemErrorException
    {
        LineItem l = null;
        Material m;
        // 210 to 720 is the width dimension of a shed
        for(int i = 210; i <= 720; i += 30)
        {
            if(c.getShed_().getWidth() == i)
            {
                m = LogicFacade.getInstance().getWoodMaterial(9, i);
                l = new LineItem(m, 12, "Løsholter til skur gavle", m.getPrice()*12, Type.LENGTH);
            }
        }
        return l;
    }
    
    /**
     * 
     * @param c
     * @return LineItem of end caps for sides (løsholter for siderne)
     * @throws NoSuchMaterialException 
     * @throws Presentation.Exceptions.SystemErrorException 
     */
    public LineItem endCapsForSides(Carport c) throws NoSuchMaterialException, SystemErrorException
    {
        LineItem l = null;
        Material m;
        // 150-540 is the lenth dimension of a shed
        for(int i = 150; i < 540; i += 30)
        {
            if(c.getShed_().getLength() == i)
            {
                m = LogicFacade.getInstance().getWoodMaterial(9, i);
                l = new LineItem(m, 4, "løsholter til skur siderne", m.getPrice()*4, Type.LENGTH);
            }
        }
        return l;
    }
    
    /**
     * 
     * @return LineItem of brackets (beslag)
     * @throws NoSuchMaterialException 
     * @throws Presentation.Exceptions.SystemErrorException 
     */
    public LineItem bracket() throws NoSuchMaterialException, SystemErrorException
    {
        Material m = LogicFacade.getInstance().getFitting(5);
        return new LineItem(m, 32, "Til montering af løsholter til skur", m.getPrice()*32, Type.NOLENGTH);
    }
    
    /**
     * 
     * @return LineItem of bracket screws (skruer til beslag)
     * @throws NoSuchMaterialException 
     * @throws Presentation.Exceptions.SystemErrorException 
     */
    public LineItem bracketScrews() throws NoSuchMaterialException, SystemErrorException
    {
        Material m = LogicFacade.getInstance().getFitting(7);
        return new LineItem(m, (32*3), "Til montering af vinkelbeslag til skur", m.getPrice() * (32*3), Type.NOLENGTH);
    }
    
    /**
     * 
     * @return LineItem of lath for door (lægte for døren)
     * @throws NoSuchMaterialException 
     * @throws Presentation.Exceptions.SystemErrorException 
     */
    public LineItem lathForDoor() throws NoSuchMaterialException, SystemErrorException
    {
         Material m = LogicFacade.getInstance().getWoodMaterial(8, 420);
         return new LineItem(m, 1, "Til z på bagside af dør", m.getPrice(), Type.LENGTH);
    }
    /**
     * 
     * @return LineItem of farmgate grip (stalddørsgreb)
     * @throws NoSuchMaterialException 
     * @throws Presentation.Exceptions.SystemErrorException 
     */
    public LineItem farmgateGrip() throws NoSuchMaterialException, SystemErrorException
    {
         Material m = LogicFacade.getInstance().getFitting(3);
         return new LineItem(m, 1, "Til løs på dør i skur", m.getPrice(), Type.NOLENGTH);
    }
    
    /**
     * 
     * @return LineItem of t-hinge (t-hængsel)
     * @throws NoSuchMaterialException 
     * @throws Presentation.Exceptions.SystemErrorException 
     */
    public LineItem tHinge() throws NoSuchMaterialException, SystemErrorException
    {
         Material m = LogicFacade.getInstance().getFitting(4);
         return new LineItem(m, 2, "Til dør i skur", m.getPrice()*2, Type.NOLENGTH);
    }
   
  }
