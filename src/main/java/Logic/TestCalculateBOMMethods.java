/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.Carport;
import Data.LineItem;
import Data.Request;
import Data.Shed;

/**
 *
 * @author Kasper Jeppesen
 */
public class TestCalculateBOMMethods
{
    public static void main(String[] args) throws NoSuchMaterialException
    {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
       
        
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
//            System.out.println(li.getPrice());
//        double i = f.hulbåndAntal(630, 720);
//        System.out.println(i);
//            CalculateBOM b = new CalculateBOM();
            Request r = new Request(1, "", "", 0, new Carport(2, true, 300, 270, true, new Shed(240, 150)));
//            
//            for(LineItem l : b.generateFlatRoofCarportBOM(r))
//            {
//                System.out.println(l);
//            }

            System.out.println(f.hulbåndAntal(r));
            
            
            



            
        
        
        
        
        
    }
}
