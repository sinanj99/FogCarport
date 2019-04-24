/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.NoSuchMaterialException;
import Logic.NoSuchRoofException;
import java.util.List;

/**
 *
 * @author Obaydah Mohamad
 */
public abstract class IRoofMapper {
    public static IRoofMapper instance() {
        return RoofMapper.getInstance();
    }
    
    public abstract Roof getRoof(String name) throws NoSuchRoofException;
    public abstract List<Roof> getRoofs() throws NoSuchRoofException;
    public abstract void updatePrice(int roof_id, int price) throws NoSuchRoofException;
   
}
