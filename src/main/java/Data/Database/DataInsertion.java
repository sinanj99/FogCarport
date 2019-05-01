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

        for (Roof r : roofs) {
            int price = 50;
            if (r.isInclined() == false) {
                for (int i = 240; i <= 750; i += 30) {

                    IRequestMapper.instance().insertDimensions(r.getRoof_id(), i, price);
                    price += 50;

                }
            } else {
                price = 50;
                IRequestMapper.instance().insertDimensions(r.getRoof_id(), 37, price);
            }
        }
        /* insertion of materials */
        int price = 40;
        for (int i = 270; i <= 780; i += 30) {

            IMaterialMapper.instance().insertMaterialDim(1, i, price, 1000);
            price += 40;
        }
        IMaterialMapper.instance().insertMaterialDim(2, 300, 40, 1000);
        price = 40;
        for (int i = 210; i <= 750; i += 30) {
            IMaterialMapper.instance().insertMaterialDim(3, i, price, 1000);
            price += 40;
        }
        price = 20;
        for (int i = 150; i <= 720; i += 30) {
            IMaterialMapper.instance().insertMaterialDim(4, i, price, 1000);
            price += 40;
        }
        price = 40;
        for (int i = 210; i <= 780; i += 30) {

            IMaterialMapper.instance().insertMaterialDim(5, i, price, 1000);
            price += 40;
        }
        price = 80;
        IMaterialMapper.instance().insertMaterialDim(6, 540, price, 1000);
        price = 40;
        for (int i = 240; i <= 810; i += 30) {

            IMaterialMapper.instance().insertMaterialDim(7, i, price, 1000);
            price += 40;
        }

    }
}
