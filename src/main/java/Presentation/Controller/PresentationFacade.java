/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Controller;

import Data.Database.DBConnector;
import Data.Database.DataSourceMysql;
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
import Logic.Exceptions.SystemErrorException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
public class PresentationFacade {

    private static PresentationFacade instance = null;
    private DataSource ds = new DataSourceMysql().getDataSource();
    private IRequestMapper r = IRequestMapper.instance();
    private IMaterialMapper m = IMaterialMapper.instance();
    private IUserMapper u = IUserMapper.instance();

    //prevents other classes from creating instance
    private PresentationFacade() {
    }
    
    //global point of access
    public static PresentationFacade getInstance() {
        if (instance == null) {
            instance = new PresentationFacade();
        }
        return instance;
    }

    public void setDataSource() {
        m.setDataSource(ds);
        r.setDataSource(ds);
        u.setDataSource(ds);
    }

    public List<Roof> getRoofs() {
        return IRequestMapper.instance().getRoofs();
    }

    public void insertDimensions(int id, int length, int price) {
        IRequestMapper.instance().insertDimensions(id, length, price);
    }

    public List<Roof> getRoofs(int rooftype) throws NoSuchRoofException, NoSuchRoofException {
        return IRequestMapper.instance().getRoofs(rooftype);
    }

    public void insertUser(User user) throws DuplicateException, SystemErrorException {
        IUserMapper.instance().insertUser(user);
    }

    public User getUser(String email) throws UserNotFoundException, SystemErrorException {
        return IUserMapper.instance().getUser(email);
    }

    public void insertRequest(Request req) {
        IRequestMapper.instance().insertRequest(req);
    }

    public Roof getRoof(int id) throws NoSuchRoofException {
        return IRequestMapper.instance().getRoof(id);
    }

    public int getDimensionPrice(int roof_id, int length) {
        return IRequestMapper.instance().getDimensionPrice(roof_id, length);
    }

    public Material getMaterial(String name) throws NoSuchMaterialException {
        return IMaterialMapper.instance().getMaterial_(name);
    }

    public Material getMaterialWithLength(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        return IMaterialMapper.instance().getMaterialWithLength(id, length);
    }

    public Material getMaterialNoLength(int id) throws SystemErrorException {
        return IMaterialMapper.instance().getMaterialNoLength(id);
    }

    public List<Request> getRequests() {
        return IRequestMapper.instance().getRequests();
    }

    public Roof newGetRoof(int id, int length) {
        return IRequestMapper.instance().getRoof(id, length);
    }

    public Request getRequest(int id) {
        return IRequestMapper.instance().getRequest(id);
    }
}
