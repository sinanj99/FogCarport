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
public class NoSuchRequestException extends SystemErrorException{
    
    public NoSuchRequestException(String message) {
        super(message);
    }

    public NoSuchRequestException() {
    }
    
}
