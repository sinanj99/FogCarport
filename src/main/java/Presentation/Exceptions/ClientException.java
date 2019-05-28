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
    
    
    public ClientException(String target, String message, String detail) {
        super(message);
        this.target = target;
        this.detail = detail;
    }

    
    public ClientException(String target, String message) {
        super(message);
        this.target = target;
    }
    
    public ClientException() {

    }

    public String getDetail() {
        return detail;
    }
    
    public String getTarget() {
        return target;
    }

}
