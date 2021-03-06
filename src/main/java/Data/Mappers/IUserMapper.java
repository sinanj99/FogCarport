/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.User;
import Presentation.Exceptions.DuplicateException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import javax.sql.DataSource;

/**
 * Interface for user mappers. Is public so that its javadoc can be published.
 * @author sinanjasar
 */
  public abstract class IUserMapper {

    protected static IUserMapper instance() {
        return UserMapper.getInstance();
    }
    /**
     * Sets datasource
     * @param ds datasource
     */
    protected abstract void setDataSource(DataSource ds);

    /**
     * Inserts user to the database.
     * User information is inserted to the tables 'users' and 'users_personalinfo'.
     * @param user the inserted user
     * @throws DuplicateException if a user with given name already exists in database
     * @throws SystemErrorException if any other sql-related error occurs
     */
    protected abstract void insertUser(User user) throws DuplicateException, SystemErrorException;

    /**
     * Fetches user from database with specified email.
     * Is used in login-command where id of the user is unknown.
     * @param email the email of the user to get
     * @return the user with the specified email
     * @throws UserNotFoundException if there is no user in database with given email
     * @throws SystemErrorException if any other sql-related error occurs
     */
    protected abstract User getUser(String email) throws UserNotFoundException, SystemErrorException;

    /**
     * Fetches user from database with specified id.
     * @param id the id of the user to get
     * @return
     * @throws UserNotFoundException if there is no user in database with given id
     * @throws SystemErrorException if any other sql-related error occurs
     */
    protected abstract User getUser(int id) throws UserNotFoundException, SystemErrorException;
}
