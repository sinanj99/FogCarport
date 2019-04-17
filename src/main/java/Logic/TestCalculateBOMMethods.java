/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.LineItem;

/**
 *
 * @author Kasper Jeppesen
 */
public class TestCalculateBOMMethods
{
    public static void main(String[] args) throws NoSuchMaterialException
    {
        CalculateBOM c = new CalculateBOM();
        
//        int quan = c.calculateQuantityOfBrædderbolt(7.7f);
//        System.out.println(quan);
        

        LineItem l = c.vandbrætForSides(510);
        
        System.out.println(l.getMaterial());
        System.out.println(l.getQty());
        System.out.println(l.getDescription());
        System.out.println(l.getPrice());
        
        
        
        
        
    }
}
