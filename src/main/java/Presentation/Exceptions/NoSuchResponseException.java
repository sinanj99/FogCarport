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

    public NoSuchResponseException(){}
    
    public NoSuchResponseException (String target, String message) {
        super(target, message);
    }
}
