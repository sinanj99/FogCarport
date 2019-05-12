/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Exceptions;

/**
 *
 * @author sinanjasar
 */
public class NoSuchMaterialException extends Throwable {

    private final String message;

    public NoSuchMaterialException(int id, int length) {
        this.message = "The material with id '" + id + "' and length '" + length + "'could not be found";
    }

    public String getMessage() {
        return message;
    }
}
