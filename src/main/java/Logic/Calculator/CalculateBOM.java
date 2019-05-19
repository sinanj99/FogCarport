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
            //the fundament
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
            listOfLineItems.add(f.roof(c)); //this should be moved
            //Still missing some methods

        } 
        else 
        {
            //The fundament
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
            listOfLineItems.add(f.roof(c)); //this should be moved
            //Still missing some methods

            //the shed
            listOfLineItems.add(b.beklædning(c));
            listOfLineItems.add(b.løsholterForSides(c));
            listOfLineItems.add(b.løsholterGalve(c));
            listOfLineItems.add(b.vinkelbeslag());
            listOfLineItems.add(b.lægteForDoor());
            listOfLineItems.add(b.stalddørsgreb());
            listOfLineItems.add(b.tHængsel());
            //Still missing some methods
            
        }
        return new BOM(listOfLineItems);
    }

    public BOM inclineRoofBOM(Request r) throws NoSuchMaterialException, NoSuchRoofException, SystemErrorException 
    {

        BOMInclineRoof calc = new BOMInclineRoof();
        BOMFlatRoof f = new BOMFlatRoof();
        BOMToolshed b = new BOMToolshed();
        List<LineItem> listOfLineItems = new ArrayList();
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

        if (c.getShed_() != null) 
        {
            //The fundament
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
            //still mising some methods
            
            //The shed
            listOfLineItems.add(b.beklædning(c));
            listOfLineItems.add(b.løsholterForSides(c));
            listOfLineItems.add(b.løsholterGalve(c));
            listOfLineItems.add(b.vinkelbeslag());
            listOfLineItems.add(b.lægteForDoor());
            listOfLineItems.add(b.stalddørsgreb());
            listOfLineItems.add(b.tHængsel());
            //Still missing some methods
            
            //The roof
            listOfLineItems.add(calc.soffits(cwidth, inclination));
            listOfLineItems.add(calc.laths(cwidth, inclination, clength, slength));
            listOfLineItems.add(calc.toplaths(clength, slength, cwidth));
            listOfLineItems.add(calc.roofTiles(roofid, cwidth, clength, inclination));
            listOfLineItems.add(calc.ridgeTiles(roofid, clength));
            listOfLineItems.add(calc.lathHolders(clength, shed, slength));
            listOfLineItems.add(calc.ridgeTileBrackets(clength));
            listOfLineItems.add(calc.roofTileBinders());
            
            } 
        else 
        {
            //The fundament 
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
            //still mising some methods
            
            //The roof
            listOfLineItems.add(calc.soffits(cwidth, inclination));
            listOfLineItems.add(calc.laths(cwidth, inclination, clength, slength));
            listOfLineItems.add(calc.toplaths(clength, slength, cwidth));
            listOfLineItems.add(calc.roofTiles(roofid, cwidth, clength, inclination));
            listOfLineItems.add(calc.ridgeTiles(roofid, clength));
            listOfLineItems.add(calc.lathHolders(clength, shed, slength));
            listOfLineItems.add(calc.ridgeTileBrackets(clength));
            listOfLineItems.add(calc.roofTileBinders());
            
        }
        return new BOM(listOfLineItems);
    }
}
