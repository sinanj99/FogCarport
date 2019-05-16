/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Super class for all client-related exceptions.
 * @author sinanjasar
 */
public class ClientException extends Exception {
    /**
     * The jsp-file/command to reach.
     */ 
    private String target = "";
    /**
     * The client input type that was affected.
     */
    private String type = "";

    /**
     * Constructs a ClientException with user-specified target, type & message.
     * Used in presentation package, when target, type & message is known.
     * @param target
     * @param message
     * @param type 
     */
    public ClientException(String target, String message, String type) {
        super(message);
        this.target = target;
        this.type = type;
    }
    
    /**
     * Constructs a ClientException with specified target & type. 
     * Used in presentation package, when target & type is known; 
     * message is omitted in this constructor,
     * since some exceptions have a specific message associated to them.
     * @param target where to send the client.
     * @param type the type of error which describes what input field should be alerted.
     */
    public ClientException(String target, String type) {
        this.target = target;
        this.type = type;
    }
    
    /**
     * Constructs a ClientException with no user-specified target / type. 
     * An example is in the mappers where the target, and input type
     * is unknown.
     * @param message a user-specified message about the exception
     */
    public ClientException(String message) {
        super(message);
    }
    
    public String getTarget() {
        return target;
    }
    
    public String getType() {
        return type;
    }
    
}
