/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.PrebuiltCarport;
import Logic.Exceptions.NoSuchPrebuiltCarportException;
import java.util.List;

/**
 *
 * @author Kasper Jeppesen
 */
public abstract class IPrebuiltCarportMapper
{
    public static IPrebuiltCarportMapper instance()
    {
        return PrebuiltCarportMapper.getInstance();
    }
    
    /** 
     * returns a list os all prebuilts carport - used to populate prebuiltcarport.jsp 
     * 
     * @return list of all prebuilts carport in database
     */
    public abstract List<PrebuiltCarport> getAllPrebuiltCarports() throws NoSuchPrebuiltCarportException;
}
