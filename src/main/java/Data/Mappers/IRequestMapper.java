/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Request;
import Data.Entity.Roof;
import Logic.Exceptions.NoSuchRoofException;
import java.util.List;

/**
 *
 * @author Obaydah Mohamad
 */
public abstract class IRequestMapper {
    
    public static IRequestMapper instance() {
        return RequestMapper.getInstance();
    }
    
    /**
     * Returns a specific request object with all its sub-tables - used when a seller
     * wants to process a request.
     * @param id
     * @return 
     */
    public abstract Request getRequest(int id);
    
    /**
     * Returns a list of all requests in dB; used to display all requests not yet 
     * processed in jsp-file 'showrequests'.
     * @return list of all requests in dB 
     */
    public abstract List<Request> getRequests();
    
    /**
     * Inserts a request to db-table 'request' (and all its child tables)
     * with specified user-input fetched from jsp-files 'flatroof- and incline'.
     * @param req 
     */
    
    public abstract void insertRequest(Request req);
    
    /**
     * ??
     * @param id
     * @param length
     * @param price 
     */
    public abstract void insertDimensions(int id, int length, int price);

    /**
     * Used in flatRoof calculator to find price of rooftype with specified dimensions.
     * @param roof_id
     * @param length
     * @return 
     */
    public abstract int getDimensionPrice(int roof_id, int length);
    
    
    /**
     * Fetches data from rooftype-table in dB and returns a roof object 
     * with id, name and inclined attributes - used in genereate request command,
     * when a request object is created. 
     * @param id
     * @return
     * @throws NoSuchRoofException 
     */
    public abstract Roof getRoof(int id) throws NoSuchRoofException;
    
    /**
     * Fetches data from rooftype-table and its child-table, rooflength,
     * where the length and price of the can be found - used to return a LineItem
     * in calculateBOM.
     * @param id
     * @param length
     * @return 
     */
    public abstract Roof getRoof(int id, int length);
    
    
    /**
     * Returns roofs of all types - used in DataInsertion class which loops through list,
     * checks if roof is type inclined or flat, and inserts data dependent on which type it is. 
     * Returns all roofs
     * @return all roofs, independent on rooftype.
     */
    
    public abstract List<Roof> getRoofs();
    
    /**
     * Returns all roofs with specified rooftype (inclined or flat).
     * @param rooftype
     * @return all roofs with specified rooftype
     * @throws NoSuchRoofException 
     */
    
    public abstract List<Roof> getRoofs(int rooftype) throws NoSuchRoofException;
    
    /**
     * Updates price of a roof with specified length and rooftype.
     * @param roof_id
     * @param price
     * @throws NoSuchRoofException 
     */
    public abstract void updateRoofPrice(int roof_id, int price) throws NoSuchRoofException;
}
