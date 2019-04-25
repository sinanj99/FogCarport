/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.UserNotFoundException;
import java.sql.SQLException;

/**
 *
 * @author sinanjasar
 */
public abstract class IUserMapper {
    public static IUserMapper instance(){
        return UserMapper.getInstance();
    }
    public abstract void insertClient(Client client) throws SQLException;

    public abstract User getUser(String email) throws UserNotFoundException;
}
