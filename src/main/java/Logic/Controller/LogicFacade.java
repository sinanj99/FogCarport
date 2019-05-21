/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Controller;

import Data.Database.DBConnector;
import Data.Database.DataSourceMysql;
import Data.Entity.Material;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Exceptions.DuplicateException;
import Presentation.Exceptions.NoSuchRoofException;
import Data.Mappers.IMaterialMapper;
import Data.Mappers.IRequestMapper;
import Data.Mappers.IUserMapper;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.User;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
public class LogicFacade {

    private static LogicFacade instance = null;
    private static DataSource ds = new DataSourceMysql().getDataSource();
    private static IRequestMapper r = IRequestMapper.instance();
    private static IMaterialMapper m = IMaterialMapper.instance();
    private static IUserMapper u = IUserMapper.instance();

    //prevents other classes from creating instance
    private LogicFacade() {
    }

    //global point of access
    public static LogicFacade getInstance() {
        if (instance == null) {
            instance = new LogicFacade();
            m.setDataSource(ds);
            r.setDataSource(ds);
            u.setDataSource(ds);
        }

        return instance;
    }
    public List<Roof> getRoofs() throws SystemErrorException {
        return IRequestMapper.instance().getRoofs();
    }

    public void insertDimensions(int id, int length, int price) throws SystemErrorException {
        IRequestMapper.instance().insertDimensions(id, length, price);
    }

    public List<Roof> getRoofs(int rooftype) throws NoSuchRoofException, NoSuchRoofException, SystemErrorException {
        return IRequestMapper.instance().getRoofs(rooftype);
    }

    public void insertUser(User user) throws DuplicateException, SystemErrorException {
        IUserMapper.instance().insertUser(user);
    }

    public User getUser(String email) throws UserNotFoundException, SystemErrorException {
        return IUserMapper.instance().getUser(email);
    }

    public void insertRequest(Request req) throws SystemErrorException {
        IRequestMapper.instance().insertRequest(req);
    }

    public Roof getRoof(int id) throws NoSuchRoofException, SystemErrorException {
        return IRequestMapper.instance().getRoof(id);
    }

    public int getDimensionPrice(int roof_id, int length) throws SystemErrorException {
        return IRequestMapper.instance().getDimensionPrice(roof_id, length);
    }

    public Material getWoodMaterial(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        return IMaterialMapper.instance().getWoodMaterial(id, length);
    }

    public Material getFitting(int id) throws SystemErrorException, NoSuchMaterialException {
        return IMaterialMapper.instance().getFitting(id);
    }

    public List<Request> getRequests() throws SystemErrorException, NoSuchShedException {
        return IRequestMapper.instance().getRequests();
    }

    public Roof newGetRoof(int id, int length) throws SystemErrorException {
        return IRequestMapper.instance().getRoof(id, length);
    }

    public Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException {
        return IRequestMapper.instance().getRequest(id);
    }
}
