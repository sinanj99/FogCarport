/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.LineItem;
import Data.Material;
import Data.IMaterialMapper;
import Data.Request;
import Data.Roof;
import java.util.ArrayList;

/**
 *
 * @author Kasper Jeppesen
 */
public class CalculateBOM
{
    //Dette er en test
    //dette er en test mere
    //en trejde test
    public ArrayList<LineItem> generateFlatRoofCarportBOM(boolean inclined, int width, int length, Roof rooftype, boolean shed, int shedWidth, int shedLength) throws NoSuchMaterialException
    {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        ArrayList listOfLineItems = new ArrayList();
        Request r = new Request(inclined, width, length, rooftype, shed, shedWidth, shedLength);
        listOfLineItems.add(f.beslagskruer(length));
        listOfLineItems.add(f.brædderbolt(length));
        listOfLineItems.add(f.højrebeslag(length));
        listOfLineItems.add(f.venstrebeslag(length));
        listOfLineItems.add(f.oversternbrædderForFront(width));
        listOfLineItems.add(f.oversternbrædderForSides(length));
        listOfLineItems.add(f.spær(length, width));
        listOfLineItems.add(f.spærForRemmen(length));
        listOfLineItems.add(f.stolpe(length));
        listOfLineItems.add(f.understernBrædderForSides(length));
        listOfLineItems.add(f.understernbrædderForFrontAndBack(width));
        listOfLineItems.add(f.vandbrætForFront(width));
        listOfLineItems.add(f.vandbrætForSides(length));
        listOfLineItems.add(f.hulbåndAntal(r));
        
        //mangler lægter, galjer, tagpplader
        
        return listOfLineItems;
    }
    public ArrayList<LineItem> generateFlatRoofWihtToolShedBOM(int widthOfCarport, int lengthOfCarport, int widthOfToolShed, int lengthOfToolShed) throws NoSuchMaterialException
    {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        ToolShedBOM b = new ToolShedBOM();
        
        ArrayList listOfLineItems = new ArrayList();
        
        //Adds material related to the carport
        listOfLineItems.add(f.beslagskruer(lengthOfCarport));
        listOfLineItems.add(f.brædderbolt(lengthOfCarport));
        listOfLineItems.add(f.højrebeslag(lengthOfCarport));
        listOfLineItems.add(f.venstrebeslag(lengthOfCarport));
        listOfLineItems.add(f.oversternbrædderForFront(widthOfCarport));
        listOfLineItems.add(f.oversternbrædderForSides(lengthOfCarport));
        listOfLineItems.add(f.spær(lengthOfCarport, widthOfCarport));
        listOfLineItems.add(f.spærForRemmen(lengthOfCarport));
        listOfLineItems.add(f.stolpe(lengthOfCarport));
        listOfLineItems.add(f.understernBrædderForSides(lengthOfCarport));
        listOfLineItems.add(f.understernbrædderForFrontAndBack(widthOfCarport));
        listOfLineItems.add(f.vandbrætForFront(widthOfCarport));
        listOfLineItems.add(f.vandbrætForSides(lengthOfCarport));
        //mangler hulbånd, lægter, gajler, tagpplader
        
        //Adds materials related to tool shed
        listOfLineItems.add(b.beklædning(widthOfToolShed, lengthOfToolShed));
        listOfLineItems.add(b.lægteForDoor());
        listOfLineItems.add(b.løsholterForSides(lengthOfToolShed));
        listOfLineItems.add(b.løsholterGalve(widthOfToolShed));
        listOfLineItems.add(b.stalddørsgreb());
        listOfLineItems.add(b.tHængsel());
        listOfLineItems.add(b.vinkelbeslag());
        
        return listOfLineItems;
    }
//    public ArrayList<LineItem> generateInclinedRoorBOM()
//    {
//        
//    }
//    public ArrayList<LineItem> generateInclinedRoofWithToolShedBOM()
//    {
//        
//    }
    
}
