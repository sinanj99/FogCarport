/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Logic;

import Presentation.Exceptions.UserNotFoundException;
import Presentation.Exceptions.NoMatchException;
import Data.Entity.User;
import Presentation.Exceptions.SystemErrorException;

/**
 * Handles login-related validation
 * @author sinanjasar
 */
class LoginController {

    /**
     * Checks if inserted password matches password for the user with the
     * specified username in the database.
     *
     * @param email
     * @param password password of the user that needs validation.
     * @param user the user to validate
     * @throws Presentation.Exceptions.UserNotFoundException
     * @throws Presentation.Exceptions.SystemErrorException
     * @throws Presentation.Exceptions.NoMatchException
     */
    public static void doesMatch(String email, String password, User user) throws UserNotFoundException, SystemErrorException, NoMatchException {
        if (!password.equals(user.getPassword())) {
            throw new NoMatchException();
        }
    }
}
