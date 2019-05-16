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
public class NoSuchCarportException extends SystemErrorException {
    
    public NoSuchCarportException(String message) {
        super(message);
    }
    
}
