/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Exceptions;

/**
 *
 * @author sinanjasar
 */
public class InvalidInputException extends Exception {
    private String target;
    public InvalidInputException(String target, String message) {
        super(message);
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
    
    
}
