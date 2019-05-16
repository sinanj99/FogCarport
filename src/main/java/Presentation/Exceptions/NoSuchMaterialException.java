/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 *
 * @author sinanjasar
 */
public class NoSuchMaterialException extends CustomException {

    public NoSuchMaterialException(String message) {
        super(message);
    }

    public NoSuchMaterialException(String message, String target, String type) {
        super(message, target, type);
    }
    
}
