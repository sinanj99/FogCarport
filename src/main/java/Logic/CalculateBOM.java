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
    public ArrayList<LineItem> generateFlatRoofCarportBOM(int user_id, boolean inclined, int width, int length, Roof rooftype, boolean shed, int shedWidth, int shedLength) throws NoSuchMaterialException
    {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        ArrayList listOfLineItems = new ArrayList();
        Request r = new Request(user_id, inclined, width, length, rooftype, shed, shedWidth, shedLength);
        listOfLineItems.add(f.beslagskruer(length));
        listOfLineItems.add(f.brædderbolt(r));
        listOfLineItems.add(f.højrebeslag(length));
        listOfLineItems.add(f.venstrebeslag(length));
        listOfLineItems.add(f.oversternbrædderForFront(width));
        listOfLineItems.add(f.oversternbrædderForSides(length));
        listOfLineItems.add(f.spær(length, width));
        listOfLineItems.add(f.spærForRemmen(length));
        listOfLineItems.add(f.stolpe(r));
        listOfLineItems.add(f.understernBrædderForSides(length));
        listOfLineItems.add(f.understernbrædderForFrontAndBack(width));
        listOfLineItems.add(f.vandbrætForFront(width));
        listOfLineItems.add(f.vandbrætForSides(length));
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
        listOfLineItems.add(f.brædderbolt(r));
        listOfLineItems.add(f.højrebeslag(r.getLength()));
        listOfLineItems.add(f.venstrebeslag(r.getLength()));
        listOfLineItems.add(f.oversternbrædderForFront(r.getWidth()));
        listOfLineItems.add(f.oversternbrædderForSides(r.getLength()));
        listOfLineItems.add(f.spær(r.getLength(), r.getWidth()));
        listOfLineItems.add(f.spærForRemmen(r.getLength()));
        listOfLineItems.add(f.stolpe(r));
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
