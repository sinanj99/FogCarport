/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.BOM;
import Data.Entity.LineItem;
import Presentation.Exceptions.InvalidInputException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author sinanjasar
 */
public class CalculatePrice {

    public static int buyPrice(BOM bom) {
        int fullPrice = 0;
        for (LineItem l : bom.getLineitems()) {
            fullPrice += l.getPrice();
        }
        return fullPrice;
    }

    public static int sellPrice(BOM bom, int profit) {
        return buyPrice(bom) * profit;
    }

    /**
     * Updates prices of all lengths of a specific material type.
     *
     * @param price the new price of a material.
     * @param prices a linkedHashMap including all lengths as keys and its price
     * as the value.
     * @return a linked hash map including all lengths and their respective prices 
     * where all the prices have been updated.
     * @throws Presentation.Exceptions.InvalidInputException if there is no difference between the new and old price.
     */
    public LinkedHashMap<Integer, Integer> updatePrices(int price, LinkedHashMap<Integer, Integer> prices) throws InvalidInputException {
        //find old price
        int oldPrice = prices.entrySet().iterator().next().getValue();
        //find difference between new and old price
        int dif = oldPrice - price;
        //if there is no change, throw exception
        if (dif == 0) {
            throw new InvalidInputException("FrontController?command=show_prices", "Indtast venligst en ny pris!", "priceError");
            //if the difference is positive, substract the difference from all other values
        } else if (dif > 0) {
            for (Map.Entry<Integer, Integer> entry : prices.entrySet()) {
                if (entry.getValue() == oldPrice) {
                    prices.put(entry.getKey(), price);
                } else {
                    prices.put(entry.getKey(), entry.getValue() - dif);
                }
            }
            //if the difference is negative, add the difference to all other values
        } else {
            for (Map.Entry<Integer, Integer> entry : prices.entrySet()) {
                if (entry.getValue() == oldPrice) {
                    prices.put(entry.getKey(), price);
                } else {
                    prices.put(entry.getKey(), entry.getValue() + Math.abs(dif));
                }
            }
        }
        return prices;
    }
}
