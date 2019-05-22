/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Controller;

import Data.Database.DataSourceMysql;
import Data.Entity.Material;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Exceptions.NoSuchRoofException;
import Data.Mappers.IMaterialMapper;
import Data.Mappers.IRequestMapper;
import Data.Mappers.IUserMapper;
import Data.Entity.Roof;
import Data.Entity.User;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.SystemErrorException;
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
    public User getUser(String email) throws UserNotFoundException, SystemErrorException {
        return IUserMapper.instance().getUser(email);
    }

    public Roof getRoof(int id) throws NoSuchRoofException, SystemErrorException {
        return IRequestMapper.instance().getRoof(id);
    }

    public Material getWoodMaterial(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        return IMaterialMapper.instance().getWoodMaterial(id, length);
    }

    public Material getFitting(int id) throws SystemErrorException, NoSuchMaterialException {
        return IMaterialMapper.instance().getFitting(id);
    }

    public Roof newGetRoof(int id, int length) throws SystemErrorException {
        return IRequestMapper.instance().getRoof(id, length);
    }
}
