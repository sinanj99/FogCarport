/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Used in any command, when a roof with specified id, doesn't exist in database.
 * @author Obaydah Mohamad
 */
public class NoSuchRoofException extends SystemErrorException {

     /**
     * Id of roof.
     */
    private int id;

    /**
     * Used in data-layer where only id is known
     * @param id of the roof
     */
    public NoSuchRoofException(int id) {
        super("Kunne ikke finde tag med id " + id + "!");
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
}
