/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import Data.Entity.Roof;
import Data.Mappers.DataFacade;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import java.util.List;

/**
 * Inserts data into database material and roof tables in database
 * @author sinanjasar
 */
public class DataInsertion {

    public static void main(String[] args) throws NoSuchRoofException, SystemErrorException {
        /* insertion of materials */
        DataFacade d = DataFacade.getInstance();
        
        int price1 = 30, price2 = 15;
        for(int i = 240; i <= 750; i+= 30) {
            d.insertMaterialDim(1, i, price1, 300);
            price1+=3;
        }
        
        for(int i = 240; i <= 750; i+= 30) {
            d.insertMaterialDim(2, i, price2, 300);
            price2+=3;
        }
        
        d.insertMaterialDim(3, 300, 50, 300);
        d.insertMaterialDim(4, 360, 15, 300);
        d.insertMaterialDim(4, 540, 25, 300);
        d.insertMaterialDim(5, 540, 32, 300);
        d.insertMaterialDim(6, 360, 22, 300);
        d.insertMaterialDim(6, 540, 38, 300);
        d.insertMaterialDim(7, 210, 17, 300);
        d.insertMaterialDim(7, 360, 22, 300);
        d.insertMaterialDim(7, 540, 22, 300);
        d.insertMaterialDim(8, 420, 29, 300);
        
        price2 = 15;
        for(int i = 150; i <= 720; i+= 30) {
            d.insertMaterialDim(9, i, price2, 300);
            price2+=3;
        }
        
        
        price2 = 60;
        for(int i = 240; i <= 780; i+= 30) {
            d.insertMaterialDim(10, i, price2, 300);
            price2+=10;
        }
        
        
        /* insertion of roofs */

        List<Roof> roofs = d.getRoofs();
        System.out.println(roofs);
        for (Roof roof : roofs) {
            int price = 50;
            if (roof.isInclined() == false) {
//                for (int i = 240; i <= 750; i += 30) {
//
//                    d.insertDimensions(roof.getRoof_id(), i, price, 300);
//                    price += 50;
//
//                }
            d.insertDimensions(roof.getRoof_id(), 30, price, 300);
            } else if (roof.isInclined() == true){
                price = 50;
                d.insertDimensions(roof.getRoof_id(), 34, price, 300);
                price = 30;
                d.insertDimensions(roof.getRoof_id(), 6, price, 300);
            }
        }
   }
}
