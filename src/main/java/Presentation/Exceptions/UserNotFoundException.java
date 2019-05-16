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
public class UserNotFoundException extends ClientException {

    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(String target, String message, String type) {
        super(target, message, type);
    }
    
}
