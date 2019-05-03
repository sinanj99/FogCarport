/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.BOM;
import Data.Entity.LineItem;
import Data.Entity.Material;
import Data.Mappers.IMaterialMapper;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.Shed;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRoofException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kasper Jeppesen
 */
public class CalculateBOM {

    //Dette er en test
    //dette er en test mere
    //en trejde test
    public BOM generateFlatRoofCarportBOM(Request r) throws NoSuchMaterialException {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        ArrayList listOfLineItems = new ArrayList();

        listOfLineItems.add(f.beslagskruer(r.getCarport().getLength()));
        listOfLineItems.add(f.brædderbolt(r.getCarport().getLength(), r.getCarport().getShed_(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(f.højrebeslag(r.getCarport().getLength()));
        listOfLineItems.add(f.venstrebeslag(r.getCarport().getLength()));
        listOfLineItems.add(f.oversternbrædderForFront(r.getCarport().getWidth()));
        listOfLineItems.add(f.oversternbrædderForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.spær(r.getCarport().getLength(), r.getCarport().getWidth()));
        listOfLineItems.add(f.spærForRemmen(r.getCarport().getLength()));
        listOfLineItems.add(f.stolpe(r.getCarport().getLength(), r.getCarport().getShed_(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(f.understernBrædderForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.understernbrædderForFrontAndBack(r.getCarport().getWidth()));
        listOfLineItems.add(f.vandbrætForFront(r.getCarport().getWidth()));
        listOfLineItems.add(f.vandbrætForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.hulbånd(r));
        //listOfLineItems.add(f.roof(r.getCarport().getLength(), r.getCarport().getRoof()));        

        //mangler lægter, galjer
        BOM b = new BOM(listOfLineItems);

        return b;
    }

    public BOM generateFlatRoofWihtToolShedBOM(Request r) throws NoSuchMaterialException {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        ToolShedBOM b = new ToolShedBOM();

        ArrayList listOfLineItems = new ArrayList();

        //Adds material related to the carport
        listOfLineItems.add(f.beslagskruer(r.getCarport().getLength()));
        listOfLineItems.add(f.brædderbolt(r.getCarport().getLength(), r.getCarport().getShed_(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(f.højrebeslag(r.getCarport().getLength()));
        listOfLineItems.add(f.venstrebeslag(r.getCarport().getLength()));
        listOfLineItems.add(f.oversternbrædderForFront(r.getCarport().getWidth()));
        listOfLineItems.add(f.oversternbrædderForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.spær(r.getCarport().getLength(), r.getCarport().getWidth()));
        listOfLineItems.add(f.spærForRemmen(r.getCarport().getLength()));
        listOfLineItems.add(f.stolpe(r.getCarport().getLength(), r.getCarport().getShed_(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(f.understernBrædderForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.understernbrædderForFrontAndBack(r.getCarport().getWidth()));
        listOfLineItems.add(f.vandbrætForFront(r.getCarport().getWidth()));
        listOfLineItems.add(f.vandbrætForSides(r.getCarport().getLength()));
        listOfLineItems.add(f.hulbånd(r));
        //listOfLineItems.add(f.roof(r.getCarport().getLength(), r.getCarport().getRoof()));
        //mangler lægter, gajler, tagpplader

        //Adds materials related to tool shed
        listOfLineItems.add(b.beklædning(r.getCarport().getShed_().getWidth(), r.getCarport().getShed_().getLength()));
        listOfLineItems.add(b.lægteForDoor());
        listOfLineItems.add(b.løsholterForSides(r.getCarport().getShed_().getLength()));
        listOfLineItems.add(b.løsholterGalve(r.getCarport().getShed_().getWidth()));
        listOfLineItems.add(b.stalddørsgreb());
        listOfLineItems.add(b.tHængsel());
        listOfLineItems.add(b.vinkelbeslag());

        BOM bo = new BOM(listOfLineItems);

        return bo;
    }
//    public ArrayList<LineItem> generateInclinedRoorBOM()
//    {
//        
//    }
//    public ArrayList<LineItem> generateInclinedRoofWithToolShedBOM()
//    {
//        
//    }

    public BOM inclineRoofBOM(Request r) throws NoSuchMaterialException, NoSuchRoofException {
        
        InclineRoofCarportBOM calc = new InclineRoofCarportBOM();
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        ToolShedBOM b = new ToolShedBOM();
        List<LineItem> list = new ArrayList();

        int clength = r.getCarport().getLength();
        int cwidth = r.getCarport().getWidth();
        int slength = r.getCarport().getShed_().getLength();
        int swidth = r.getCarport().getShed_().getWidth();
        Shed shed = r.getCarport().getShed_();
        int roofid = r.getCarport().getRoof().getRoof_id();
        int inclination = (int) r.getCarport().getInclination();

        if (shed != null) {
            //with length index 0-12
            list.add(calc.soffits(cwidth, inclination));
            list.add(calc.fasciaCarport(clength, shed, slength));
            list.add(calc.fasciaShed(slength));//shed
            list.add(f.stolpe(clength, shed, slength));
            list.add(calc.beamsCarport(clength, shed, slength));
            list.add(calc.beamsShed(slength));//shed
            list.add(calc.intertiesSides(slength));//shed
            list.add(calc.intertiesGables(swidth));//shed
            list.add(calc.rainboards(cwidth, inclination));
            list.add(b.beklædning(swidth, slength));//shed
            list.add(b.lægteForDoor());//shed
            list.add(calc.laths(cwidth, inclination, clength, slength));
            list.add(calc.toplaths(clength, slength, cwidth));

            //roof package index 13-17
            list.add(calc.roofTiles(roofid, cwidth, clength, inclination));
            list.add(calc.ridgeTiles(roofid, clength));
            list.add(calc.lathHolders(clength, shed, slength));
            list.add(calc.ridgeTileBrackets(clength));
            list.add(calc.roofTileBinders());

            //brackets&screws index 18-
            list.add(calc.rafters(r.getCarport().getLength(), r.getCarport().getShed_(), r.getCarport().getShed_().getLength()));
            list.add(calc.rigthBracketRafters(clength, shed, slength));
            list.add(calc.leftBracketRafters(clength, shed, slength));
            list.add(b.stalddørsgreb());//shed
            list.add(b.tHængsel());//shed
            list.add(calc.bracketInterties());//shed
            list.add(calc.fasciaAndSoffitScrews(clength, cwidth, shed, slength, inclination));
            list.add(calc.screwsLathHolders(clength, shed, slength));
            list.add(calc.LathScrews(cwidth, inclination));
            list.add(f.brædderbolt(clength, shed, slength));
            list.add(calc.squareDiscs());
            list.add(calc.screwsOuterTimbering(swidth, slength));//shed
            list.add(calc.screwsInnerTimbering(swidth, slength));//shed
        } else {
            list.add(calc.soffits(cwidth, inclination));
            list.add(calc.fasciaCarport(clength, shed, slength));
            list.add(f.stolpe(clength, shed, slength));
            list.add(calc.beamsCarport(clength, shed, slength));
            list.add(calc.rainboards(cwidth, inclination));
            list.add(calc.laths(cwidth, inclination, clength, slength));
            list.add(calc.toplaths(clength, slength, cwidth));
            
            list.add(calc.roofTiles(roofid, cwidth, clength, inclination));
            list.add(calc.ridgeTiles(roofid, clength));
            list.add(calc.lathHolders(clength, shed, slength));
            list.add(calc.ridgeTileBrackets(clength));
            list.add(calc.roofTileBinders());
            
            list.add(calc.rafters(r.getCarport().getLength(), r.getCarport().getShed_(), r.getCarport().getShed_().getLength()));
            list.add(calc.rigthBracketRafters(clength, shed, slength));
            list.add(calc.leftBracketRafters(clength, shed, slength));
            list.add(calc.fasciaAndSoffitScrews(clength, cwidth, shed, slength, inclination));
            list.add(calc.screwsLathHolders(clength, shed, slength));
            list.add(calc.LathScrews(cwidth, inclination));
            list.add(f.brædderbolt(clength, shed, slength));
            list.add(calc.squareDiscs());
        }
        return new BOM(list);
    }
}
