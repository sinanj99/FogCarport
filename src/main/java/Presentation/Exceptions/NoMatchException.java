/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Used specifically in LoginCommand, when a user's password and username 
 * does not match.
 * @author sinanjasar
 */
public class NoMatchException extends ClientException {

    public NoMatchException(String message) {
        super(message);
    }
    
    public NoMatchException(String target, String message, String type) {
        super(target, message, type);
    }
}
