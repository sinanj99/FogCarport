/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Used in any command, when a user with specified id, doesn't exist in database.
 * @author sinanjasar
 */
public class UserNotFoundException extends ClientException {

    public UserNotFoundException() {
    }
    public UserNotFoundException(String target, String message) {
        super(target, message);
    }
    
}
