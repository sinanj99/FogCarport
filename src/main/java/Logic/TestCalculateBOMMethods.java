/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.Material;
import Data.MaterialDAO;

/**
 *
 * @author Kasper Jeppesen
 */
public class TestCalculateBOMMethods
{
    public static void main(String[] args) throws Exception
    {
        CalculateBOM c = new CalculateBOM();
        int quan = c.calculateQuantityOfSpær(7);
        
        System.out.println(quan);
        
        
        
        
    }
}
