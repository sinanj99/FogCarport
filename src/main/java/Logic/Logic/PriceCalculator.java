/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Logic;

import Data.Entity.BOM;
import Data.Entity.LineItem;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.SystemErrorException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author sinanjasar
 */
class PriceCalculator {

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
     * @param id
     * @param prices a linkedHashMap including all lengths as keys and its price
     * as the value.
     * @param type
     * @return a linked hash map including all lengths and their respective
     * prices where all the prices have been updated.
     * @throws Presentation.Exceptions.InvalidInputException if there is no
     * difference between the new and old price.
     * @throws Presentation.Exceptions.SystemErrorException
     * @throws Presentation.Exceptions.NoSuchMaterialException
     */
    public void updatePrices(int price, int id, String type) throws InvalidInputException, SystemErrorException, NoSuchMaterialException {
        LinkedHashMap<Integer, Integer> prices;
        if (type.equals("length")) {
            prices = LogicFacade.getInstance().getPricesWithLength(id);
        } else {
            prices = LogicFacade.getInstance().getRoofLengthPrices(id);
        }

        //find old price
        int oldPrice = prices.entrySet().iterator().next().getValue();
        //find difference between new and old price
        int dif = oldPrice - price;
        //if there is no change, throw exception
        if (dif == 0) {
            throw new InvalidInputException("FrontController?command=show_prices", "Indtast venligst en ny pris!");
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
        if (type.equals("length")) {
            LogicFacade.getInstance().updatePriceWithLength(prices, id);
        } else {
            LogicFacade.getInstance().updatePricesRoof(prices, id);
        }
    }
}
