/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 * Used when two input fields do not match.
 * @author sinanjasar
 */
public class NoMatchException extends ClientException {

    public NoMatchException() {
    }
    
    public NoMatchException(String target, String message) {
        super(target, message);
    }

    public NoMatchException(String target, String message, String detail) {
        super(target, message, detail);
    }
}
