/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Used in any command, when user-input does not meet requirements.
 * @author sinanjasar
 */
public class InvalidInputException extends ClientException {

    /**
     * Constructs an InvalidInputException with user-specified msg, target & type.
     * Is dependent on client input, and therefore, is only constructed 
     * inside of presentation-package where the parameters are known.
     * @param message
     * @param target
     * @param type 
     */
    public InvalidInputException(String message, String target, String type) {
        super(message, target, type);
    }
    
    
}
