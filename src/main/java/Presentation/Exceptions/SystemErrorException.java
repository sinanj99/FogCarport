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

    /**
     * Where to send the user
     */
    private final String target = "jsp/error.jsp";
    /**
     * Message to be displayed to user
     */
    private final String message = "Der opstod en fejl!";
    /**
     * Detailed message of what went wrong
     */
    private String detail = "";
     
    /**
     * Constructs a SystemErrorException with a specified message
     * @param detail a detailed despriction of what went wrong
     */
    public SystemErrorException(String detail) {
        super(detail);
    }

    public String getTarget() {
        return target;
    }   

    public String getDetail() {
        return detail;
    }

    @Override
    public String getMessage() {
        return message;
    }  
}
