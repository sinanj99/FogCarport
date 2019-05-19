/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.BOM;
import Data.Entity.Carport;
import Data.Entity.LineItem;
import Data.Entity.Material;
import Data.Mappers.IMaterialMapper;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.Shed;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kasper Jeppesen
 */
public class CalculateBOM {
    
    public BOM generateFlatRoofCarportBOM(Request r) throws NoSuchMaterialException {
        BOMFlatRoof f = new BOMFlatRoof();
        ArrayList listOfLineItems = new ArrayList();
        BOMToolshed b = new BOMToolshed();
        Carport c = r.getCarport();
        
        if (r.getCarport().getShed_() == null) 
        {
            listOfLineItems.add(f.beslagskruer(c));
            listOfLineItems.add(f.brædderbolt(c));
            listOfLineItems.add(f.højrebeslag(c));
            listOfLineItems.add(f.venstrebeslag(c));
            listOfLineItems.add(f.oversternbrædderForFront(c));
            listOfLineItems.add(f.oversternbrædderForSides(c));
            listOfLineItems.add(f.spær(c));
            listOfLineItems.add(f.spærForRemmen(c));
            listOfLineItems.add(f.stolpe(c));
            listOfLineItems.add(f.understernBrædderForSides(c));
            listOfLineItems.add(f.understernbrædderForFrontAndBack(c));
            listOfLineItems.add(f.vandbrætForFront(c));
            listOfLineItems.add(f.vandbrætForSides(c));
            listOfLineItems.add(f.hulbånd(c));
            listOfLineItems.add(f.roof(c));

            //mangler lægter, galjer

        } else {

            //Adds material related to the carport
            listOfLineItems.add(f.beslagskruer(c));
            listOfLineItems.add(f.brædderbolt(c));
            listOfLineItems.add(f.højrebeslag(c));
            listOfLineItems.add(f.venstrebeslag(c));
            listOfLineItems.add(f.oversternbrædderForFront(c));
            listOfLineItems.add(f.oversternbrædderForSides(c));
            listOfLineItems.add(f.spær(c));
            listOfLineItems.add(f.spærForRemmen(c));
            listOfLineItems.add(f.stolpe(c));
            listOfLineItems.add(f.understernBrædderForSides(c));
            listOfLineItems.add(f.understernbrædderForFrontAndBack(c));
            listOfLineItems.add(f.vandbrætForFront(c));
            listOfLineItems.add(f.vandbrætForSides(c));
            listOfLineItems.add(f.hulbånd(c));
            listOfLineItems.add(f.roof(c));
            //mangler lægter, gajler, tagpplader

            //Adds materials related to tool shed
            listOfLineItems.add(b.beklædning(r.getCarport().getShed_().getWidth(), r.getCarport().getShed_().getLength()));
            listOfLineItems.add(b.lægteForDoor());
            listOfLineItems.add(b.løsholterForSides(r.getCarport().getShed_().getLength()));
            listOfLineItems.add(b.løsholterGalve(r.getCarport().getShed_().getWidth()));
            listOfLineItems.add(b.stalddørsgreb());
            listOfLineItems.add(b.tHængsel());
            listOfLineItems.add(b.vinkelbeslag());
        }
        return new BOM(listOfLineItems);
    }

    public BOM inclineRoofBOM(Request r) throws NoSuchMaterialException, NoSuchRoofException, SystemErrorException {

        BOMInclineRoof calc = new BOMInclineRoof();
        BOMFlatRoof f = new BOMFlatRoof();
        BOMToolshed b = new BOMToolshed();
        List<LineItem> list = new ArrayList();
        Carport c = r.getCarport();

        int clength = r.getCarport().getLength();
        int cwidth = r.getCarport().getWidth();
        int slength = 0;
        int swidth = 0;
        if(r.getCarport().getShed_() != null) {
        slength = r.getCarport().getShed_().getLength();
        swidth = r.getCarport().getShed_().getWidth();
        }
        Shed shed = r.getCarport().getShed_();
        int roofid = r.getCarport().getRoof().getRoof_id();
        int inclination = (int) r.getCarport().getInclination();

        if (shed != null) {
            //with length index 0-12
            list.add(calc.soffits(cwidth, inclination));
            list.add(calc.fasciaCarport(clength, shed));
            list.add(calc.fasciaShed(slength));//shed
            list.add(f.stolpe(c));
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
            list.add(calc.rafters(r.getCarport().getWidth(), r.getCarport().getLength(), r.getCarport().getShed_()));
            list.add(calc.rigthBracketRafters(clength, shed, slength));
            list.add(calc.leftBracketRafters(clength, shed, slength));
            list.add(b.stalddørsgreb());//shed
            list.add(b.tHængsel());//shed
            list.add(calc.bracketInterties());//shed
            list.add(calc.fasciaAndSoffitScrews(clength, cwidth, shed, slength, inclination));
            list.add(calc.screwsLathHolders(clength, shed, slength));
            list.add(calc.LathScrews(cwidth, inclination));
            list.add(f.brædderbolt(c));
            list.add(calc.squareDiscs());
            list.add(calc.screwsOuterTimbering(swidth, slength));//shed
            list.add(calc.screwsInnerTimbering(swidth, slength));//shed
        } else {
            list.add(calc.soffits(cwidth, inclination));
            list.add(calc.fasciaCarport(clength, shed));
            list.add(f.stolpe(c));
            list.add(calc.beamsCarport(clength, shed, slength));
            list.add(calc.rainboards(cwidth, inclination));
            list.add(calc.laths(cwidth, inclination, clength, slength));
            list.add(calc.toplaths(clength, slength, cwidth));

            list.add(calc.roofTiles(roofid, cwidth, clength, inclination));
            list.add(calc.ridgeTiles(roofid, clength));
            list.add(calc.lathHolders(clength, shed, slength));
            list.add(calc.ridgeTileBrackets(clength));
            list.add(calc.roofTileBinders());

            list.add(calc.rafters(r.getCarport().getWidth(), r.getCarport().getLength(), r.getCarport().getShed_()));
            list.add(calc.rigthBracketRafters(clength, shed, slength));
            list.add(calc.leftBracketRafters(clength, shed, slength));
            list.add(calc.fasciaAndSoffitScrews(clength, cwidth, shed, slength, inclination));
            list.add(calc.screwsLathHolders(clength, shed, slength));
            list.add(calc.LathScrews(cwidth, inclination));
            list.add(f.brædderbolt(c));
            list.add(calc.squareDiscs());
        }
        return new BOM(list);
    }
}
