/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import Data.Entity.Roof;
import Data.Mappers.IMaterialMapper;
import Data.Mappers.IRequestMapper;
import Logic.Exceptions.NoSuchRoofException;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public class DataInsertion {
    public static void main(String[] args) throws NoSuchRoofException {
        
        /* insertion of roofs */
        List<Roof> roofs = IRequestMapper.instance().getRoofs(0);
        
        for(Roof r : roofs)
        {
            int price = 50;
            
            for(int i = 240; i<= 750; i+=30)
            {
                IRequestMapper.instance().insertDimensions(r.getRoof_id(), i, price);
                price += 50;
            }
        }
        int price = 40;
        /* insertion of materialw*/
        for(int i = 270; i <= 780; i+= 30) {
            
            IMaterialMapper.instance().insertMaterialDim(1, i, price, 1000);
            price+=40;
        }
        price = 40;
        for(int i = 210; i <= 750; i+=30) {
            IMaterialMapper.instance().insertMaterialDim(3, i, price, 1000);
            price+=40;
        }
    }
}
