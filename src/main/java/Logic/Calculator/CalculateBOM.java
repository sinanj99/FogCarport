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
    
    public BOM generateFlatRoofCarportBOM(Request r) throws NoSuchMaterialException, SystemErrorException {
        BOMFundament f = new BOMFundament();
        ArrayList listOfLineItems = new ArrayList();
        BOMToolshed b = new BOMToolshed();
        Carport c = r.getCarport();
        
        if (r.getCarport().getShed() == null) 
        {
            //the fundament
            listOfLineItems.add(f.bracketScrews(c));
            listOfLineItems.add(f.boardBolts(c));
            listOfLineItems.add(f.squareDiscs(c));
            listOfLineItems.add(f.rightBracketInteries(c));
            listOfLineItems.add(f.leftBracketInteries(c));
            listOfLineItems.add(f.outerBoardForFront(c));
            listOfLineItems.add(f.outerBoardForSides(c));
            listOfLineItems.add(f.rafter(c));
            listOfLineItems.add(f.RafterForStrap(c));
            listOfLineItems.add(f.post(c));
            listOfLineItems.add(f.underneathBoardForSides(c));
            listOfLineItems.add(f.underneathBoardForFrontAndBack(c));
            listOfLineItems.add(f.waterBoardForFront(c));
            listOfLineItems.add(f.waterBoardForSides(c));
            listOfLineItems.add(f.perforatedBands(c));
            listOfLineItems.add(f.roof(c)); //this should be moved
            //Still missing some methods

        } 
        else 
        {
            //The fundament
            listOfLineItems.add(f.bracketScrews(c));
            listOfLineItems.add(f.boardBolts(c));
            listOfLineItems.add(f.squareDiscs(c));
            listOfLineItems.add(f.rightBracketInteries(c));
            listOfLineItems.add(f.leftBracketInteries(c));
            listOfLineItems.add(f.outerBoardForFront(c));
            listOfLineItems.add(f.outerBoardForSides(c));
            listOfLineItems.add(f.rafter(c));
            listOfLineItems.add(f.RafterForStrap(c));
            listOfLineItems.add(f.post(c));
            listOfLineItems.add(f.underneathBoardForSides(c));
            listOfLineItems.add(f.underneathBoardForFrontAndBack(c));
            listOfLineItems.add(f.waterBoardForFront(c));
            listOfLineItems.add(f.waterBoardForSides(c));
            listOfLineItems.add(f.perforatedBands(c));
            listOfLineItems.add(f.roof(c)); //this should be moved
            //Still missing some methods

            //the shed
            listOfLineItems.add(b.shedClothing(c));
            listOfLineItems.add(b.endCapsForSides(c));
            listOfLineItems.add(b.endCapsForGables(c));
            listOfLineItems.add(b.bracket());
            listOfLineItems.add(b.lathForDoor());
            listOfLineItems.add(b.farmgateGrip());
            listOfLineItems.add(b.tHinge());
            //Still missing some methods
            
        }
        return new BOM(listOfLineItems);
    }

    public BOM inclineRoofBOM(Request r) throws NoSuchMaterialException, NoSuchRoofException, SystemErrorException 
    {

        BOMRoofPackage calc = new BOMRoofPackage();
        BOMFundament f = new BOMFundament();
        BOMToolshed b = new BOMToolshed();
        List<LineItem> listOfLineItems = new ArrayList();
        Carport c = r.getCarport();

        

        if (c.getShed() != null) 
        {
            //The fundament
            listOfLineItems.add(f.bracketScrews(c));
            listOfLineItems.add(f.boardBolts(c));
            listOfLineItems.add(f.squareDiscs(c));
            listOfLineItems.add(f.rightBracketInteries(c));
            listOfLineItems.add(f.leftBracketInteries(c));
            listOfLineItems.add(f.outerBoardForFront(c));
            listOfLineItems.add(f.outerBoardForSides(c));
            listOfLineItems.add(f.rafter(c));
            listOfLineItems.add(f.RafterForStrap(c));
            listOfLineItems.add(f.post(c));
            listOfLineItems.add(f.underneathBoardForSides(c));
            listOfLineItems.add(f.underneathBoardForFrontAndBack(c));
            listOfLineItems.add(f.waterBoardForFront(c));
            listOfLineItems.add(f.waterBoardForSides(c));
            listOfLineItems.add(f.perforatedBands(c)); 
            //still mising some methods
            
            //The shed
            listOfLineItems.add(b.shedClothing(c));
            listOfLineItems.add(b.endCapsForSides(c));
            listOfLineItems.add(b.endCapsForGables(c));
            listOfLineItems.add(b.bracket());
            listOfLineItems.add(b.lathForDoor());
            listOfLineItems.add(b.farmgateGrip());
            listOfLineItems.add(b.tHinge());
            //Still missing some methods
            
            //The roof
            listOfLineItems.add(calc.screwsLathHolders(c));
            listOfLineItems.add(calc.LathScrews(c));
            listOfLineItems.add(calc.laths(c));
            listOfLineItems.add(calc.toplaths(c));
            listOfLineItems.add(calc.soffits(c));
            listOfLineItems.add(calc.lathHolders(c));
            listOfLineItems.add(calc.roofTiles(c));
            listOfLineItems.add(calc.roofTileBinders());
            listOfLineItems.add(calc.ridgeTiles(c));
            listOfLineItems.add(calc.ridgeTileBrackets(c));
            
            } 
        else 
        {
            //The fundament 
            listOfLineItems.add(f.bracketScrews(c));
            listOfLineItems.add(f.boardBolts(c));
            listOfLineItems.add(f.squareDiscs(c));
            listOfLineItems.add(f.rightBracketInteries(c));
            listOfLineItems.add(f.leftBracketInteries(c));
            listOfLineItems.add(f.outerBoardForFront(c));
            listOfLineItems.add(f.outerBoardForSides(c));
            listOfLineItems.add(f.rafter(c));
            listOfLineItems.add(f.RafterForStrap(c));
            listOfLineItems.add(f.post(c));
            listOfLineItems.add(f.underneathBoardForSides(c));
            listOfLineItems.add(f.underneathBoardForFrontAndBack(c));
            listOfLineItems.add(f.waterBoardForFront(c));
            listOfLineItems.add(f.waterBoardForSides(c));
            listOfLineItems.add(f.perforatedBands(c)); 
            //still mising some methods
            
            //The roof
           listOfLineItems.add(calc.screwsLathHolders(c));
           listOfLineItems.add(calc.LathScrews(c));
           listOfLineItems.add(calc.laths(c));
           listOfLineItems.add(calc.toplaths(c));
           listOfLineItems.add(calc.soffits(c));
           listOfLineItems.add(calc.lathHolders(c));
           listOfLineItems.add(calc.roofTiles(c));
           listOfLineItems.add(calc.roofTileBinders());
           listOfLineItems.add(calc.ridgeTiles(c));
           listOfLineItems.add(calc.ridgeTileBrackets(c));
            
        }
        return new BOM(listOfLineItems);
    }
}
