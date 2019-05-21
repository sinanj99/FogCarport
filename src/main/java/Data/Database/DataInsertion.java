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
        
        /* insertion of materials */
        IRequestMapper r = IRequestMapper.instance();
        IMaterialMapper m = IMaterialMapper.instance();
        r.setDataSource(new DataSourceMysql().getDataSource());
        m.setDataSource(new DataSourceMysql().getDataSource());
        
        int price1 = 30, price2 = 15;
        for(int i = 240; i <= 750; i+= 30) {
            m.insertMaterialDim(1, i, price1, 300);
            price1+=3;
        }
        
        for(int i = 240; i <= 750; i+= 30) {
            m.insertMaterialDim(2, i, price2, 300);
            price2+=3;
        }
        
        m.insertMaterialDim(3, 300, 50, 300);
        m.insertMaterialDim(4, 250, 15, 300);
        m.insertMaterialDim(5, 540, 32, 300);
        m.insertMaterialDim(6, 250, 22, 300);
        m.insertMaterialDim(7, 210, 17, 300);
        m.insertMaterialDim(7, 250, 22, 300);
        m.insertMaterialDim(8, 420, 29, 300);
        
        price2 = 15;
        for(int i = 150; i <= 720; i+= 30) {
            m.insertMaterialDim(9, i, price2, 300);
            price2+=3;
        }
        
        
        price2 = 60;
        for(int i = 240; i <= 780; i+= 30) {
            m.insertMaterialDim(10, i, price2, 300);
            price2+=10;
        }
        
        
        /* insertion of roofs */

        List<Roof> roofs = IRequestMapper.instance().getRoofs();
        
        for (Roof roof : roofs) {
            int price = 50;
            if (roof.isInclined() == false) {
                for (int i = 240; i <= 750; i += 30) {

                    r.insertDimensions(roof.getRoof_id(), i, price);
                    price += 50;

                }
            } else if (roof.isInclined() == true){
                price = 50;
                r.insertDimensions(roof.getRoof_id(), 37, price);
                price = 30;
                r.insertDimensions(roof.getRoof_id(), 6, price);
            }
        }
   }
}
