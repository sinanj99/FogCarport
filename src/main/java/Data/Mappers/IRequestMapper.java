/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.ShippingAddress;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Obaydah Mohamad
 */
public abstract class IRequestMapper {
    
    public static IRequestMapper instance() {
        return RequestMapper.getInstance();
    }
    
    public abstract void setDataSource(DataSource ds);
    
    /**
     * Returns a specific request object with all its sub-tables - used when a seller
     * wants to process a request.
     * @param id of the request
     * @return 
     * @throws Presentation.Exceptions.SystemErrorException if an sql-exception is thrown
     * @throws Presentation.Exceptions.NoSuchRequestException if no request with specified id can be found
     * @throws Presentation.Exceptions.NoSuchShedException if no shed with specified id can be found
     */
    public abstract Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException;
    
    /**
     * Returns a list of all requests in dB; used to display all requests not yet 
     * processed in jsp-file 'showrequests'.
     * @return list of all requests in dB 
     * @throws Presentation.Exceptions.SystemErrorException if an sql-exception is thrown
     * @throws Presentation.Exceptions.NoSuchShedException  if a shed with specified id cannot be found
     */
    public abstract List<Request> getRequests() throws SystemErrorException, NoSuchShedException;
    
    /**
     * Inserts a request to db-table 'request' (and all its child tables)
     * with specified user-input fetched from jsp-files 'flatroof- and incline'.
     * @param req the request
     * @throws Presentation.Exceptions.SystemErrorException if an sql exception is thrown
     */
    
    public abstract void insertRequest(Request req) throws SystemErrorException;
    
    /**
     * Is used in data insertion class, where different dimensions of a roof is inserted.
     * @param id of the roof
     * @param length of the roof
     * @param price of the roof
     * @throws Presentation.Exceptions.SystemErrorException if an sql exception is thrown
     */
    public abstract void insertDimensions(int id, int length, int price) throws SystemErrorException;

    /**
     * Used in flatRoof calculator to find price of rooftype with specified dimensions.
     * @param roof_id id of the roof
     * @param length of the roof to be fetched
     * @return the desired roof with length and id as specified
     * @throws Presentation.Exceptions.SystemErrorException if no roof with specified id
     * & length can be found
     */
    public abstract int getDimensionPrice(int roof_id, int length) throws SystemErrorException;
    
    
    /**
     * Fetches data from rooftype-table in dB and returns a roof object 
     * with id, name and inclined attributes - used in genereate request command,
     * when a request object is created. 
     * @param id of the roof
     * @return
     * @throws NoSuchRoofException 
     */
    public abstract Roof getRoof(int id) throws  NoSuchRoofException, SystemErrorException;
    
    /**
     * Fetches data from rooftype-table and its child-table, rooflength,
     * where the length and price of the can be found - used to return a LineItem
     * in calculateBOM.
     * @param id of the roof
     * @param length of the roof
     * @return desired roof with specified id and length
     * @throws Presentation.Exceptions.SystemErrorException if an sql-exception is thrown 
     */
    public abstract Roof getRoof(int id, int length) throws SystemErrorException;
    
    
    /**
     * Returns roofs of all types - used in DataInsertion class which loops through list,
     * checks if roof is type inclined or flat, and inserts data dependent on which type it is. 
     * Returns all roofs
     * @return all roofs, independent on rooftype.
     * @throws Presentation.Exceptions.SystemErrorException if an sql exception is thrown
     */
    
    public abstract List<Roof> getRoofs() throws SystemErrorException;
    
    /**
     * Returns all roofs with specified rooftype (inclined or flat).
     * @param rooftype
     * @return all roofs with specified rooftype
     * @throws NoSuchRoofException 
     */
    
    public abstract List<Roof> getRoofs(int rooftype) throws SystemErrorException;
    
    /**
     * Updates price of a roof with specified length and rooftype.
     * @param roof_id id of roof
     * @param price of roof
     * @throws NoSuchRoofException if no roof with specified id can be found
     */
    public abstract void updateRoofPrice(int roof_id, int price) throws SystemErrorException;
    
    /**
     * Returns a ShippingAddress object which contains user and user shipping info, specified by
     * request id.
     * @param id of request with the shipping address
     * @return user-info(firstname, lastname, address, zipcode and city)
     * @throws Presentation.Exceptions.SystemErrorException if an sql-exception is thrown
     */
    public abstract ShippingAddress getRequestShippingAddress(int id) throws SystemErrorException;
}
