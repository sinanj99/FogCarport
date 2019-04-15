/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.BOM;
import Data.LineItem;

/**
 *
 * @author sinanjasar
 */
public class CalculatePrice {
    public static int buyPrice(BOM bom) {
        int fullPrice = 0;
        for(LineItem l : bom.getLineitems()) {
            fullPrice+=l.getPrice();
        }
        return fullPrice;
    }
    public static int sellPrice(BOM bom, int profit) {
        return buyPrice(bom)*profit;
    }
}
