/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.NoSuchRoofException;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public class DataInsertion {
    public static void main(String[] args) throws NoSuchRoofException {
        List<Roof> roofs = IRequestMapper.instance().getRoofs();
        
        for(Roof r : roofs)
        {
            for(int i = 240; i<= 750; i+=30)
            {
                IRequestMapper.instance().insertDimensions(r.getRoof_id(), i);
            }
        }
    }
}
