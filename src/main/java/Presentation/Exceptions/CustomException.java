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
public class CustomException extends Exception {
    private String target = "";
    private String type = "";

    public CustomException(String message) {
        super(message);
    }
    
    public CustomException(String target, String message, String type) {
        super(message);
        this.target = target;
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public String getType() {
        return type;
    }
    
}
