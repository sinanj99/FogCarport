/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Super class for all client-related exceptions.
 *
 * @author sinanjasar
 */
public class ClientException extends Exception {

    /**
     * The jsp-file/command to reach.
     */
    private String target = "";

    /**
     * A short description of what went wrong for the client.
     */
    private String detail;
    
    /**
     * Constructs a ClientException with user-specified target, message & detail. 
     * Used in presentation package, where target, message & detail is known.
     * @param target where to send the client
     * @param message a short description of the error
     * @param detail a detailed description of the error
     */
    public ClientException(String target, String message, String detail) {
        super(message);
        this.target = target;
        this.detail = detail;
    }

    /**
     * Constructs a ClientException with user-specified target & message. 
     * Used in presentation package, where target & message is known.
     * @param target where to send the client
     * @param message a short description of the error
     */
    public ClientException(String target, String message) {
        super(message);
        this.target = target;
    }
    /**
     * Constructs a ClientException with no specified message or target.
     * Used outside of presentation package, where target & message is unknown
     */
    public ClientException() {

    }

    public String getDetail() {
        return detail;
    }
    
    public String getTarget() {
        return target;
    }

}
