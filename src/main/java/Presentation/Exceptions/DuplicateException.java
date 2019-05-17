/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Used specifically in RegisterCommand, when an email is already in use.
 * @author sinanjasar
 */

public class DuplicateException extends ClientException {

    public DuplicateException() {
    }
    
    public DuplicateException(String target, String message) {
        super(target, message);
    }
    
    
}
