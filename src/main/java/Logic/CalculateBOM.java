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
        
        listOfLineItems.add(f.beslagskruer(r.getCarport().getLength()));
        listOfLineItems.add(f.brædderbolt(r.getCarport().getLength(), r.getCarport().isShed(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(f.højrebeslag(r.getCarport().getLength()));
        listOfLineItems.add(f.venstrebeslag(r.getCarport().getLength()));
        listOfLineItems.add(f.oversternbrædderForFront(r.getCarport().getWidth()));
        listOfLineItems.add(f.oversternbrædderForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.spær(r.getCarport().getLength(), r.getCarport().getWidth()));
        listOfLineItems.add(f.spærForRemmen(r.getCarport().getLength()));
        listOfLineItems.add(f.stolpe(r.getCarport().getLength(), r.getCarport().isShed(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(f.understernBrædderForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.understernbrædderForFrontAndBack(r.getCarport().getWidth()));
        listOfLineItems.add(f.vandbrætForFront(r.getCarport().getWidth()));
        listOfLineItems.add(f.vandbrætForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.hulbånd(r));
        listOfLineItems.add(f.roof(r.getCarport().getLength(), r.getCarport().getRoof()));        
        
        //mangler lægter, galjer
        
        return listOfLineItems;
    }
    public ArrayList<LineItem> generateFlatRoofWihtToolShedBOM(Request r) throws NoSuchMaterialException
    {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        ToolShedBOM b = new ToolShedBOM();
        
        ArrayList listOfLineItems = new ArrayList();
        
        //Adds material related to the carport
        listOfLineItems.add(f.beslagskruer(r.getCarport().getLength()));
        listOfLineItems.add(f.brædderbolt(r.getCarport().getLength(), r.getCarport().isShed(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(f.højrebeslag(r.getCarport().getLength()));
        listOfLineItems.add(f.venstrebeslag(r.getCarport().getLength()));
        listOfLineItems.add(f.oversternbrædderForFront(r.getCarport().getWidth()));
        listOfLineItems.add(f.oversternbrædderForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.spær(r.getCarport().getLength(), r.getCarport().getWidth()));
        listOfLineItems.add(f.spærForRemmen(r.getCarport().getLength()));
        listOfLineItems.add(f.stolpe(r.getCarport().getLength(), r.getCarport().isShed(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(f.understernBrædderForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.understernbrædderForFrontAndBack(r.getCarport().getWidth()));
        listOfLineItems.add(f.vandbrætForFront(r.getCarport().getWidth()));
        listOfLineItems.add(f.vandbrætForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.hulbånd(r));
        listOfLineItems.add(f.roof(r.getCarport().getLength(), r.getCarport().getRoof()));
        //mangler lægter, gajler, tagpplader
        
        //Adds materials related to tool shed
        listOfLineItems.add(b.beklædning(r.getCarport().getShed_().getWidth(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(b.lægteForDoor());
        listOfLineItems.add(b.løsholterForSides(r.getCarport().getShed_().getLength()));
        listOfLineItems.add(b.løsholterGalve(r.getCarport().getShed_().getWidth()));
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
