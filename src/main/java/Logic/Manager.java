/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.IMaterialMapper;
import Data.IRoofMapper;
import Data.Roof;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public class Manager {
    private final IMaterialMapper mapper = IMaterialMapper.instance();
    private final IRoofMapper mapper2 = IRoofMapper.instance();
    
    public List<Roof> allFlatRoofs() throws NoSuchRoofException {
        return mapper2.getRoofs();
    }
}
