/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Controller;

import Data.Entity.Material;
import Logic.Exceptions.UserNotFoundException;
import Logic.Exceptions.DuplicateException;
import Logic.Exceptions.NoSuchRoofException;
import Data.Mappers.IMaterialMapper;
import Data.Mappers.IRequestMapper;
import Data.Mappers.IUserMapper;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.User;
import Logic.Exceptions.NoSuchMaterialException;
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

    public static void insertRequest(Request req) throws NoSuchRoofException {
        IRequestMapper.instance().insertRequest(req);
    }
    public static Roof getRoof(int id) throws NoSuchRoofException {
        return IRequestMapper.instance().getRoof(id);
    }
    
    public static int getDimensionPrice(int roof_id, int length) {
        return IRequestMapper.instance().getDimensionPrice(roof_id, length);
    }

    public static Material getMaterial(String name) throws NoSuchMaterialException{
        return IMaterialMapper.instance().getMaterial_(name);
    }
    public static List<Request> getRequests() {
        return IRequestMapper.instance().getRequests();

    }
}

