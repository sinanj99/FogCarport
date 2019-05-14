/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Controller;

import Logic.Exceptions.UserNotFoundException;
import Logic.Exceptions.NoMatchException;
import Data.Mappers.IUserMapper;
import Data.Entity.User;
import Logic.Exceptions.SystemErrorException;
import java.sql.SQLException;

/**
 *
 * @author sinanjasar
 */
public class LoginController {

    /**
     * Checks if inserted password matches password for the user with the
     * specified username in the database.
     *
     * @param email
     * @param password password of the user that needs validation.
     * @throws Logic.Exceptions.UserNotFoundException
     * @throws Logic.Exceptions.SystemErrorException
     * @throws Logic.Exceptions.NoMatchException
     */
    public static void doesMatch(String email, String password) throws UserNotFoundException, SystemErrorException, NoMatchException {
        User user = LogicFacade.getInstance().getUser(email);
        if (!password.equals(user.getPassword())) {
            throw new NoMatchException("Adgangskode passer ikke!");
        }
    }
}
