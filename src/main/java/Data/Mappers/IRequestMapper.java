/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.ShippingAddress;
import Presentation.Exceptions.NoSuchCarportException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import java.util.List;
import javax.sql.DataSource;

/**
 * Interface for RequestMapper. Is public so that its javadoc can be published.
 * @author Obaydah Mohamad
 */
 public abstract class IRequestMapper {

    protected static IRequestMapper instance() {
        return RequestMapper.getInstance();
    }

    protected abstract void setDataSource(DataSource ds);

    /**
     * Returns a specific request object with all its sub-tables - used when a
     * seller wants to process a request.
     *
     * @param id of the request
     * @return
     * @throws Presentation.Exceptions.SystemErrorException if an sql-exception
     * is thrown
     * @throws Presentation.Exceptions.NoSuchRequestException if no request with
     * specified id can be found
     * @throws Presentation.Exceptions.NoSuchShedException if no shed with
     * specified id can be found
     */
    protected abstract Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException;

    /**
     * Returns a list of all requests in dB; used to display all requests not
     * yet processed in jsp-file 'showrequests'.
     *
     * @return list of all requests in dB
     * @throws Presentation.Exceptions.SystemErrorException if an sql-exception
     * is thrown
     * @throws Presentation.Exceptions.NoSuchShedException if a shed with
     * specified id cannot be found
     */
    protected abstract List<Request> getRequests() throws SystemErrorException, NoSuchShedException;

    /**
     * Inserts a request to db-table 'request' (and all its child tables) with
     * specified user-input fetched from jsp-files 'flatroof- and incline'.
     *
     * @param req the request
     * @throws Presentation.Exceptions.SystemErrorException if an sql exception
     * is thrown
     */
    protected abstract void insertRequest(Request req) throws SystemErrorException;

    /**
     * Deletes a specific request from db-table 'request'. Calls
     * deleteRequestCarport and deleteRequestShed, so that the carport and the
     * shed for the given shed also gets deleted. Used when a seller wants to
     * delete a request.
     *
     * @param id the request id
     * @throws Presentation.Exceptions.NoSuchRequestException if an sql
     * exception is thrown
     */
    protected abstract void deleteRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchCarportException, NoSuchShedException;

    /**
     * Is used in data insertion class, where different dimensions of a roof is
     * inserted.
     *
     * @param id of the roof
     * @param length of the roof
     * @param price of the roof
     * @param stock of the roof
     * @throws Presentation.Exceptions.SystemErrorException if an sql exception
     * is thrown
     */
    protected abstract void insertDimensions(int id, int length, int price, int stock) throws SystemErrorException;

    /**
     * Used in flatRoof calculator to find price of rooftype with specified
     * dimensions.
     *
     * @param roof_id id of the roof
     * @param length of the roof to be fetched
     * @return the desired roof with length and id as specified
     * @throws Presentation.Exceptions.SystemErrorException if no roof with
     * specified id & length can be found
     */
    protected abstract int getDimensionPrice(int roof_id, int length) throws SystemErrorException;

    /**
     * Fetches data from rooftype-table in dB and returns a roof object with id,
     * name and inclined attributes - used in genereate request command, when a
     * request object is created.
     *
     * @param id of the roof
     * @return
     * @throws NoSuchRoofException
     */
    protected abstract Roof getRoof(int id) throws NoSuchRoofException, SystemErrorException;

    /**
     * Fetches data from rooftype-table and its child-table, rooflength, where
     * the length and price of the can be found - used to return a LineItem in
     * calculateBOM.
     *
     * @param id of the roof
     * @param length of the roof
     * @return desired roof with specified id and length
     * @throws Presentation.Exceptions.SystemErrorException if an sql-exception
     * is thrown
     */
    protected abstract Roof getRoof(int id, int length) throws SystemErrorException;

    /**
     * Returns roofs of all types - used in DataInsertion class which loops
     * through list, checks if roof is type inclined or flat, and inserts data
     * dependent on which type it is. Returns all roofs
     *
     * @return all roofs, independent on rooftype.
     * @throws Presentation.Exceptions.SystemErrorException if an sql exception
     * is thrown
     */
    protected abstract List<Roof> getRoofs() throws SystemErrorException;

    /**
     * Returns all roofs with specified rooftype (inclined or flat).
     *
     * @param rooftype
     * @return all roofs with specified rooftype
     * @throws NoSuchRoofException
     */
    protected abstract List<Roof> getRoofs(int rooftype) throws SystemErrorException;

    /**
     * Returns a ShippingAddress object which contains user and user shipping
     * info, specified by request id.
     *
     * @param id of request with the shipping address
     * @return user-info(firstname, lastname, address, zipcode and city)
     * @throws Presentation.Exceptions.SystemErrorException if an sql-exception
     * is thrown
     */
    protected abstract ShippingAddress getRequestShippingAddress(int id) throws SystemErrorException;
}
