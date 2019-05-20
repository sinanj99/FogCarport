/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Used in any command, when a material with specified id, doesn't exist in database. 
 * @author sinanjasar
 */
public class NoSuchMaterialException extends ClientException {

     /**
     * Id of material.
     */
    private int id;

    /**
     * Used in data-layer where only id is known
     * @param id of the material
     */
    public NoSuchMaterialException(int id) {
        this.id = id;
    }

     /**
     * Used in presentation layer where target is known
     * @param target where to send client
     * @param id id of the material
     */
    public NoSuchMaterialException(String target, int id) {
        super(target, "Kunne ikke finde materiale med id " + id + "!");
    }
    

    public int getId() {
        return id;
    }
    
}
