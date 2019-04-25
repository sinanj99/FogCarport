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
    public ArrayList<LineItem> generateFlatRoofCarportBOM(Request r) throws NoSuchMaterialException
    {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        ArrayList listOfLineItems = new ArrayList();
        
        listOfLineItems.add(f.beslagskruer(r.getLength()));
        listOfLineItems.add(f.brædderbolt(r.getLength(), r.isShed(), r.getShedLength()));
        listOfLineItems.add(f.højrebeslag(r.getLength()));
        listOfLineItems.add(f.venstrebeslag(r.getLength()));
        listOfLineItems.add(f.oversternbrædderForFront(r.getWidth()));
        listOfLineItems.add(f.oversternbrædderForSides(r.getLength()));
        listOfLineItems.add(f.spær(r.getLength(), r.getWidth()));
        listOfLineItems.add(f.spærForRemmen(r.getLength()));
        listOfLineItems.add(f.stolpe(r.getLength(), r.isShed(), r.getShedLength()));
        listOfLineItems.add(f.understernBrædderForSides(r.getLength()));
        listOfLineItems.add(f.understernbrædderForFrontAndBack(r.getWidth()));
        listOfLineItems.add(f.vandbrætForFront(r.getWidth()));
        listOfLineItems.add(f.vandbrætForSides(r.getLength()));
        listOfLineItems.add(f.hulbåndAntal(r));
        
        //mangler lægter, galjer, tagpplader
        
        return listOfLineItems;
    }
    public ArrayList<LineItem> generateFlatRoofWihtToolShedBOM(Request r) throws NoSuchMaterialException
    {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        ToolShedBOM b = new ToolShedBOM();
        
        ArrayList listOfLineItems = new ArrayList();
        
        //Adds material related to the carport
        listOfLineItems.add(f.beslagskruer(r.getLength()));
        listOfLineItems.add(f.brædderbolt(r.getLength(), r.isShed(), r.getShedLength()));
        listOfLineItems.add(f.højrebeslag(r.getLength()));
        listOfLineItems.add(f.venstrebeslag(r.getLength()));
        listOfLineItems.add(f.oversternbrædderForFront(r.getWidth()));
        listOfLineItems.add(f.oversternbrædderForSides(r.getLength()));
        listOfLineItems.add(f.spær(r.getLength(), r.getWidth()));
        listOfLineItems.add(f.spærForRemmen(r.getLength()));
        listOfLineItems.add(f.stolpe(r.getLength(), r.isShed(), r.getShedLength()));
        listOfLineItems.add(f.understernBrædderForSides(r.getLength()));
        listOfLineItems.add(f.understernbrædderForFrontAndBack(r.getWidth()));
        listOfLineItems.add(f.vandbrætForFront(r.getWidth()));
        listOfLineItems.add(f.vandbrætForSides(r.getLength()));
        //mangler hulbånd, lægter, gajler, tagpplader
        
        //Adds materials related to tool shed
        listOfLineItems.add(b.beklædning(r.getShedWidth(), r.getShedLength()));
        listOfLineItems.add(b.lægteForDoor());
        listOfLineItems.add(b.løsholterForSides(r.getShedLength()));
        listOfLineItems.add(b.løsholterGalve(r.getShedWidth()));
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
