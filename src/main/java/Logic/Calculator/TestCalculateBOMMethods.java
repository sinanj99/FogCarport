/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.BOM;
import Data.Entity.Carport;
import Data.Entity.LineItem;
import Data.Entity.PersonalInfo;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.Shed;
import Logic.Exceptions.NoSuchMaterialException;

/**
 *
 * @author Kasper Jeppesen
 */
public class TestCalculateBOMMethods
{
    public static void main(String[] args) throws NoSuchMaterialException
    {
        BOMFlatRoof f = new BOMFlatRoof();
        BOMInclineRoof i = new BOMInclineRoof();
        CalculateBOM c = new CalculateBOM();
        
//        int quan = f.calculateQuantityOfBeslagskruer(450);
//        System.out.println(c.calculateQuantityOfSpær(450));
//        System.out.println(quan);
        

//        LineItem l = f.beslagskruer(720);
//        
//        System.out.println(l.getMaterial());
//        System.out.println(l.getQty());
//        System.out.println(l.getDescription());
//        System.out.println(l.getPrice());

         

//            ToolShedBOM t = new ToolShedBOM();
//            LineItem li = t.løsholterGalve(510);
//            System.out.println(li.getMaterial());
//            System.out.println(li.getQty());
//            System.out.println(li.getDescription());
//Request req = new Request(1, "", "", 10, new Carport(1, false, 500, 600, false, null));
//
//            System.out.println(f.venstrebeslag(req.getCarport().getLength()));
            
            
//String str = "/jsp/flatroof.jsp";
//        System.out.println(str.substring(1).split("/")[1]);

//        System.out.println(f.calculateQuantityOfSpær(780));
//        System.out.println(f.calculateQuantityOfSpær(300));
        


            
        
        
        
        
        
    }
}
