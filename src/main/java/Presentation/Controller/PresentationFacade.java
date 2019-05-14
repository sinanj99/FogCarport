/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Controller;

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
    private static final DataSource ds = new DataSourceMysql().getDataSource();
    private static final IRequestMapper r = IRequestMapper.instance();
    private static final IMaterialMapper m = IMaterialMapper.instance();
    private static final IUserMapper u = IUserMapper.instance();

    //prevents other classes from creating instance
    private PresentationFacade() {
    }

    //global point of access
    public static PresentationFacade getInstance() {
        if (instance == null) {
            instance = new PresentationFacade();
            m.setDataSource(ds);
            u.setDataSource(ds);
            r.setDataSource(ds);
        }
        return instance;
    }

    public List<Roof> getRoofs() {
        return r.getRoofs();
    }

    public void insertDimensions(int id, int length, int price) {
        r.insertDimensions(id, length, price);
    }

    public List<Roof> getRoofs(int rooftype) throws NoSuchRoofException, NoSuchRoofException {
        return r.getRoofs(rooftype);
    }

    public void insertUser(User user) throws DuplicateException, SystemErrorException {
        u.insertUser(user);
    }

    public User getUser(String email) throws UserNotFoundException, SystemErrorException {
        return u.getUser(email);
    }

    public void insertRequest(Request req) {
        r.insertRequest(req);
    }

    public Roof getRoof(int id) throws NoSuchRoofException {
        return r.getRoof(id);
    }

    public int getDimensionPrice(int roof_id, int length) {
        return r.getDimensionPrice(roof_id, length);
    }

    public Material getMaterial(String name) throws NoSuchMaterialException {
        return IMaterialMapper.instance().getMaterial_(name);
    }

    public Material getMaterialWithLength(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        return m.getMaterialWithLength(id, length);
    }

    public Material getMaterialNoLength(int id) throws SystemErrorException {
        return m.getMaterialNoLength(id);
    }

    public List<Request> getRequests() {
        return r.getRequests();
    }

    public Roof newGetRoof(int id, int length) {
        return r.getRoof(id, length);
    }

    public Request getRequest(int id) {
        return r.getRequest(id);
    }
}
