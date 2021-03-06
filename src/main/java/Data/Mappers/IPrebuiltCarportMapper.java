/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.PrebuiltCarport;
import Presentation.Exceptions.SystemErrorException;
import java.util.List;
import javax.sql.DataSource;

/**
 * Interface for PrebuiltCarportMapper. Is public so that its javadoc can be published.
 * @author Kasper Jeppesen
 */
 public abstract class IPrebuiltCarportMapper
{
    protected static IPrebuiltCarportMapper instance()
    {
        return PrebuiltCarportMapper.getInstance();
    }
    
    protected abstract void setDataSource(DataSource ds);
    
    /** 
     * returns a list os all prebuilts carport - used to populate prebuiltcarport.jsp 
     * 
     * @return list of all prebuilts carport in database
     * @throws Presentation.Exceptions.SystemErrorException if an sql-exception is thrown
     */
    protected abstract List<PrebuiltCarport> getAllPrebuiltCarports() throws SystemErrorException;
}
