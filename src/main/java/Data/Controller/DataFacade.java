/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Controller;

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
import Logic.Calculator.CalculatePrice;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRequestException;
import Logic.Exceptions.NoSuchShedException;
import Logic.Exceptions.SystemErrorException;
import Presentation.Exceptions.InvalidInputException;
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

    //prevents other classes from creating instance
    private DataFacade() {
    }
    
    //global point of access
    public static DataFacade getInstance() {
        if (instance == null) {
            instance = new DataFacade();
        }
        return instance;
    }
    public LinkedHashMap<Integer, Integer> updatePrices(int price, LinkedHashMap<Integer, Integer> prices) throws InvalidInputException {
        return pc.updatePrices(price, prices);
    }
    
}
