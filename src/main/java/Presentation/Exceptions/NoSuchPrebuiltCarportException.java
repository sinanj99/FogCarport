/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 *
 * @author Kasper Jeppesen
 */
public class NoSuchPrebuiltCarportException extends SystemErrorException
{
    
    public NoSuchPrebuiltCarportException(String message) {
        super(message);
    }
    
}
