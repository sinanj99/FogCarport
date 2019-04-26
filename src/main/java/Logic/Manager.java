/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.Client;
import Data.IMaterialMapper;
import Data.IRequestMapper;
import Data.IUserMapper;
import Data.Roof;
import Data.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public class Manager {
    
    public static List<Roof> allFlatRoofs() throws NoSuchRoofException {
        return IRequestMapper.instance().getRoofs();
    }
    public static void insertClient(Client client) throws DuplicateException, SQLException {
        IUserMapper.instance().insertClient(client);
    }
    public static User getUser(String email) throws SQLException, UserNotFoundException{
        return IUserMapper.instance().getUser(email);
    }
}
