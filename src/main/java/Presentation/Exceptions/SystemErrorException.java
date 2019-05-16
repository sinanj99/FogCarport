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

    private String target;
    
    public SystemErrorException(String message) {
        super(message);
        this.target = "jsp/error.jsp";
    }

    public String getTarget() {
        return target;
    }   
}
