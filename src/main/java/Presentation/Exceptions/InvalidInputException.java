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

    public InvalidInputException(String message, String target, String detail) {
        super(message, target, detail);
    }
    
    public InvalidInputException(String target, String message) {
        super(target, message);
    }
    
}
