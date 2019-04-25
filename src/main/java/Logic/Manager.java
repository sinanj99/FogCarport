/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.IMaterialMapper;
import Data.IRequestMapper;
import Data.Roof;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public class Manager {

    private final IMaterialMapper materials = IMaterialMapper.instance();
    private final IRequestMapper roofs = IRequestMapper.instance();
    
    public List<Roof> allFlatRoofs() throws NoSuchRoofException {
        return roofs.getRoofs();
    }
}
