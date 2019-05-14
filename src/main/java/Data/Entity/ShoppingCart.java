/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

import java.util.ArrayList;

/**
 * Instantiated in ShopCommand, where a session containing a shoppingCart is created
 * 
 * @author Kasper Jeppesen
 */
public class ShoppingCart
{
    ArrayList<PrebuiltCarport> prebuiltCarports = new ArrayList<>();
    
    public ArrayList<PrebuiltCarport> getPrebuiltCarports()
    {
        return prebuiltCarports;
    }

    
    
}
