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
public class NoSuchResponseException extends ClientException{
    public NoSuchResponseException (String msg){
        super(msg);
    }
    
    public NoSuchResponseException (String target, String message, String type) {
        super(target, "Kunne ikke finde materiale med id: " + message, type);
    }
}
