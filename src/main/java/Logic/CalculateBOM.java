/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.LineItem;
import Data.Material;
import Data.IMaterialMapper;
import java.util.ArrayList;

/**
 *
 * @author Kasper Jeppesen
 */
public class CalculateBOM
{
    //Dette er en test
    public ArrayList<LineItem> generateFlatRoofCarportBOM(int length, int width) throws NoSuchMaterialException
    {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        
        ArrayList listOfLineItems = new ArrayList();
        
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
        //mangler hulbånd, lægter, gajler, tagpplader
        
        return listOfLineItems;
    }
//    public ArrayList<LineItem> generateFlatRoofWihtToolShedBOM()
//    {
//        
//    }
//    public ArrayList<LineItem> generateInclinedRoorBOM()
//    {
//        
//    }
//    public ArrayList<LineItem> generateInclinedRoofWithToolShedBOM()
//    {
//        
//    }
    
}
