/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

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

    public static List<Roof> getRoofs(int rooftype) throws NoSuchRoofException {
        return IRequestMapper.instance().getRoofs(rooftype);
    }

    public static void insertUser(User user) throws DuplicateException, SQLException {
        IUserMapper.instance().insertUser(user);
    }

    public static User getUser(String email) throws SQLException, UserNotFoundException {
        return IUserMapper.instance().getUser(email);
    }

    public static void insertRequest(int user_id, int roof_id, int price, boolean inclined, int width, int length, boolean shed, int shedWidth, int shedLength, String datePlaced) throws NoSuchRoofException {
        IRequestMapper.instance().insertRequest(user_id, roof_id, inclined, width, length, shed, shedWidth, shedLength, datePlaced);
    }
}
