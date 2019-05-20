/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 *
 * @author Obaydah Mohamad
 */
public class NoSuchShedException extends SystemErrorException {
     /**
     * Id of request.
     */
    private int id;

    /**
     * Used in data-layer where only id is known
     * @param id of the request
     */
    public NoSuchShedException(int id) {
        super("Kunne ikke finde skur tilhørende forespørgsel med id " + id + "!");
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
