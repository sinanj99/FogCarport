/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import Data.Entity.Roof;
import Data.Mappers.IMaterialMapper;
import Data.Mappers.IRequestMapper;
import Logic.Controller.LogicFacade;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public class DataInsertion {

    public static void main(String[] args) throws NoSuchRoofException, SystemErrorException {

        /* insertion of roofs */
        IRequestMapper rmapper = IRequestMapper.instance();
        IMaterialMapper mmapper = IMaterialMapper.instance();
        rmapper.setDataSource(new DataSourceMysql().getDataSource());
        mmapper.setDataSource(new DataSourceMysql().getDataSource());
        
        List<Roof> roofs = IRequestMapper.instance().getRoofs();
        
        for (Roof r : roofs) {
            int price = 50;
            if (r.isInclined() == false) {
                for (int i = 240; i <= 750; i += 30) {

                    rmapper.insertDimensions(r.getRoof_id(), i, price);
                    price += 50;

                }
            } else if (r.isInclined() == true){
                price = 50;
                rmapper.insertDimensions(r.getRoof_id(), 37, price);
                price = 30;
                rmapper.insertDimensions(r.getRoof_id(), 6, price);
            }
        }
        
        /* insertion of materials */
        int price = 40;
        for (int i = 270; i <= 780; i += 30) {

            mmapper.insertMaterialDim(1, i, price, 1000);
            price += 40;
        }
        mmapper.insertMaterialDim(2, 300, 40, 1000);
        price = 40;
        for (int i = 210; i <= 750; i += 30) {
            mmapper.insertMaterialDim(3, i, price, 1000);
            price += 40;
        }
        price = 20;
        for (int i = 150; i <= 720; i += 30) {
            mmapper.insertMaterialDim(4, i, price, 1000);
            price += 40;
        }
        price = 40;
        for (int i = 210; i <= 780; i += 30) {

            mmapper.insertMaterialDim(5, i, price, 1000);
            price += 40;
        }
        price = 80;
        mmapper.insertMaterialDim(6, 540, price, 1000);
        price = 40;
        for (int i = 240; i <= 810; i += 30) {

            mmapper.insertMaterialDim(7, i, price, 1000);
            price += 40;
        }

    }
}
