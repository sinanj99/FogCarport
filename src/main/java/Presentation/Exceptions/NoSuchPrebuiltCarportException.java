/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Used in any command, when a prebuilt carport with specified id, doesn't exist in database.
 * @author Kasper Jeppesen
 */
public class NoSuchPrebuiltCarportException extends SystemErrorException
{
    
       
    /**
     * Id of carport.
     */
    private final int id;

    /**
     * Used in data-layer where only id is known
     * @param id of the carport
     */
    public NoSuchPrebuiltCarportException(int id) {
        super("Kunne ikke finde carport med id " + id + "!");
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
}
