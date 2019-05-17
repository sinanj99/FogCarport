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

    public NoSuchMaterialException() {
    }
    
    public NoSuchMaterialException(String target, String message) {
        super(target, "Kunne ikke finde materiale med id: " + message);
    }
    
}
