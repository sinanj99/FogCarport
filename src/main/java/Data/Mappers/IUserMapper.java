/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Mappers.UserMapper;
import Data.Entity.User;
import Logic.Exceptions.DuplicateException;
import Logic.Exceptions.UserNotFoundException;
import java.sql.SQLException;

/**
 *
 * @author sinanjasar
 */
public abstract class IUserMapper {

    public static IUserMapper instance() {
        return UserMapper.getInstance();
    }

    public abstract void insertUser(User user) throws DuplicateException, SQLException;

    public abstract User getUser(String email) throws UserNotFoundException, SQLException;

    public abstract User getUser(int id) throws UserNotFoundException, SQLException;
}
