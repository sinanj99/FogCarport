/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Super class of all exceptions related to system errors.
 * The types of exceptions that inherit this class is independent on client-input. 
 * @author sinanjasar
 */
public class SystemErrorException extends Exception {

    private final String target = "jsp/error.jsp";
    
    /**
     * Constructs a SystemErrorException with no description of the error.
     */
    public SystemErrorException() {
    }
    
    /**
     * Constructs a SystemErrorException with a specified message
     * @param message a despriction of what went wrong
     */
    public SystemErrorException(String message) {
        super(message);
    }

    public String getTarget() {
        return target;
    }   
}
