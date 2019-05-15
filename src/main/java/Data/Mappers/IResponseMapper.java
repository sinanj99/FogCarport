/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Response;
import javax.sql.DataSource;

/**
 *
 * @author Obaydah Mohamad
 */
public abstract class IResponseMapper {
     public static IResponseMapper instance() {
        return ResponseMapper.getInstance();
    }
    
    public abstract void setDataSource(DataSource ds);
    public abstract Response getResponse(int requestId);
    public abstract void insertResponse(Response res);
}
