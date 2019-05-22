/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Controller;

import Data.Database.DBConnector;
import Data.Database.DataSourceMysql;
import Data.Entity.Material;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Exceptions.DuplicateException;
import Data.Mappers.IMaterialMapper;
import Data.Mappers.IRequestMapper;
import Data.Mappers.IUserMapper;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.User;
import Data.Mappers.IPrebuiltCarportMapper;
import Data.Mappers.IResponseMapper;
import Logic.Calculator.CalculatePrice;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
public class DataFacade {

    private static DataFacade instance = null;
    CalculatePrice pc = new CalculatePrice();
    private static final DataSource ds = new DataSourceMysql().getDataSource();
    private static final IRequestMapper r = IRequestMapper.instance();
    private static final IResponseMapper rp = IResponseMapper.instance();
    private static final IMaterialMapper m = IMaterialMapper.instance();
    private static final IUserMapper u = IUserMapper.instance();
    private static final IPrebuiltCarportMapper p = IPrebuiltCarportMapper.instance(); 

    //prevents other classes from creating instance
    private DataFacade() {
    }
    
    //global point of access
    public static DataFacade getInstance() {
        if (instance == null) {
            instance = new DataFacade();
            m.setDataSource(ds);
            u.setDataSource(ds);
            r.setDataSource(ds);
            p.setDataSource(ds);
            rp.setDataSource(ds);
        }
        return instance;
    }
    public LinkedHashMap<Integer, Integer> updatePrices(int price, LinkedHashMap<Integer, Integer> prices) throws InvalidInputException {
        return pc.updatePrices(price, prices);
    }
    
    public Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException {
        return r.getRequest(id);
    }
    
}
