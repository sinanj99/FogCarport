/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.IUserMapper;
import Data.User;

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
     * @return boolean
     * @throws Logic.UserNotFoundException
     */
    
    public static boolean doesMatch(String email, String password) throws UserNotFoundException {
        if (email == null || email.isEmpty()) {
            return false;
        }
        else if (password == null || password.isEmpty()) {
            return false;
        }
        
        User user = Manager.getUser(email);
        return password.equals(user.getPassword());
    }
}
