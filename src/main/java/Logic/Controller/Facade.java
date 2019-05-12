/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Controller;

import Data.Database.DBConnector;
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
import Logic.Exceptions.NoSuchRequestException;
import Logic.Exceptions.NoSuchShedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public class Facade {

    public static void getTestConnection() {
        IUserMapper.instance().getTestConnection();
    }
    
    public static List<Roof> getRoofs(int rooftype) throws NoSuchRoofException, NoSuchRoofException {
        return IRequestMapper.instance().getRoofs(rooftype);
    }

    public static void insertUser(User user) throws DuplicateException, SQLException {
        IUserMapper.instance().insertUser(user);
    }

    public static User getUser(String email) throws UserNotFoundException, SQLException, SQLException {
        return IUserMapper.instance().getUser(email);
    }

    public static void insertRequest(Request req) {
        IRequestMapper.instance().insertRequest(req);
    }
    public static Roof getRoof(int id) throws NoSuchRoofException {
        return IRequestMapper.instance().getRoof(id);
    }
    
    public static int getDimensionPrice(int roof_id, int length) {
        return IRequestMapper.instance().getDimensionPrice(roof_id, length);
    }

    public static Material getMaterial(String name) throws NoSuchMaterialException {
        return IMaterialMapper.instance().getMaterial_(name);
    }
    public static Material getMaterialWithLength(int id, int length) throws NoSuchMaterialException {
        return IMaterialMapper.instance().getMaterialWithLength(id, length);
    }
    public static Material getMaterialNoLength(int id) {
        return IMaterialMapper.instance().getMaterialNoLength(id);
    }
    public static List<Request> getRequests() {
        return IRequestMapper.instance().getRequests();
    }
    public static Roof newGetRoof(int id, int length) {
        return IRequestMapper.instance().getRoof(id, length);
    }
    public static Request getRequest(int id) {
        return IRequestMapper.instance().getRequest(id);
    }
}

