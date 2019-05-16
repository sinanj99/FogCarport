/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Mappers.UserMapper;
import Data.Entity.User;
import Presentation.Exceptions.DuplicateException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
public abstract class IUserMapper {

    public static IUserMapper instance() {
        return UserMapper.getInstance();
    }
    
    public abstract void setDataSource(DataSource ds);

    public abstract void insertUser(User user) throws DuplicateException, SystemErrorException;

    public abstract User getUser(String email) throws UserNotFoundException, SystemErrorException;

    public abstract User getUser(int id) throws UserNotFoundException, SystemErrorException;
}
