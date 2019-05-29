/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Response;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.SystemErrorException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Obaydah Mohamad
 */
abstract class IResponseMapper {
     protected static IResponseMapper instance() {
        return ResponseMapper.getInstance();
    }
    
    protected abstract void setDataSource(DataSource ds);
    
    /**
     * Returns a list of all response in dB associated with a given user(client); 
     * used to display all response which has not yet
     * been answered by the user(client)
     * @return list from the database, of a users open responses
     */
    protected abstract List<Response> getResponses(int userId) throws SystemErrorException;
    
    /**
     * Returns a specific response object - used when logged in as a user
     * @param id of the request
     * @return Request
     * @throws Presentation.Exceptions.NoSuchResponseException if no response with specified id could be found
     */
    protected abstract Response getResponse(int requestId) throws NoSuchResponseException, SystemErrorException;
    
    /**
     * Inserts a response to db-table 'response'
     * @param res the response
     * @throws Presentation.Exceptions.SystemErrorException if an sql exception is thrown
     */
    protected abstract void insertResponse(Response res) throws SystemErrorException;
    /**
     * Deletes a specific response object - used when a user(client) declines an 
     * offer(response) on a request
     * @param id of the response
     * @throws Presentation.Exceptions.NoSuchResponseException if no response with specified id could be found and deleted
     */
    protected abstract void deleteResponse(int responseId) throws NoSuchResponseException, SystemErrorException;    
}
