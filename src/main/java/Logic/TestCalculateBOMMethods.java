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
        int quan = c.calculateQuantityOfSpær(7);
        
        System.out.println(quan);

        LineItem l = c.spær(7, 2);
        
        System.out.println(l.getName());
        System.out.println(l.getLength());
        System.out.println(l.getUnit());
        System.out.println(l.getDescription());
        System.out.println(l.getQuantity());
        
        
        
        
        
    }
}