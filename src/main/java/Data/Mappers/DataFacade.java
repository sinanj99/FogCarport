/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Database.DataSourceMysql;
import Data.Entity.Material;
import Data.Entity.PrebuiltCarport;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Exceptions.DuplicateException;
import Data.Entity.Request;
import Data.Entity.Response;
import Data.Entity.Roof;
import Data.Entity.ShippingAddress;
import Data.Entity.User;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoSuchCarportException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import java.util.LinkedHashMap;
import java.util.List;
import javax.sql.DataSource;

/**
 * Purpose is to call methods in datalayer, mainly mappers.
 * @author sinanjasar
 */
public class DataFacade {

    private static DataFacade instance = null;
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

    
    // Material mapper 
    
    public void updatePricesRoof(LinkedHashMap<Integer, Integer> prices, int id) throws SystemErrorException, NoSuchRoofException, InvalidInputException {
        m.updatePriceRoof(prices, id);
    }

    public void updatePriceFittings(int price, int id) throws SystemErrorException, NoSuchMaterialException {
        m.updatePriceFittings(price, id);
    }

    public void updatePriceWithLength(LinkedHashMap<Integer, Integer> prices, int id) throws SystemErrorException, NoSuchMaterialException, InvalidInputException {
        m.updatePriceWithLength(prices, id);
    }

    public LinkedHashMap<Integer, Integer> getPricesWithLength(int id) throws SystemErrorException, NoSuchMaterialException {
        return m.getMaterialLengthPrices(id);
    }

    public LinkedHashMap<Integer, Integer> getRoofLengthPrices(int id) throws SystemErrorException, NoSuchMaterialException {
        return m.getRoofLengthPrices(id);
    }

    public void updateStockWithLength(int id, int length, int qty) throws SystemErrorException, IllegalArgumentException, NoSuchMaterialException {
        m.updateStockWithLength(id, length, qty);
    }

    public void updateStockFittings(int id, int qty) throws SystemErrorException, NoSuchMaterialException, IllegalArgumentException {
        m.updateStockFittings(id, qty);
    }

    public Material getWoodMaterial(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        return m.getWoodMaterial(id, length);
    }

    public Material getFitting(int id) throws SystemErrorException, NoSuchMaterialException {
        return m.getFitting(id);
    }

    public void insertMaterialDim(int id, int length, int price, int stock) throws SystemErrorException {
        m.insertMaterialDim(id, length, price, stock);
    }

    public List<Material> getMaterials() throws SystemErrorException {
        return m.getMaterials();
    }

    public LinkedHashMap<Integer, Integer> getMaterialLengthPrices(int id) throws SystemErrorException, NoSuchMaterialException {
        return m.getMaterialLengthPrices(id);
    }

    public void updatePriceRoof(LinkedHashMap<Integer, Integer> prices, int id) throws SystemErrorException, NoSuchRoofException, InvalidInputException {
        m.updatePriceRoof(prices, id);
    }
    
    public List<Roof> getRoofsSorted() throws SystemErrorException {
        return m.getRoofs();
    }
    // Request mapper
    
    public List<Roof> getRoofs() throws SystemErrorException {
        return r.getRoofs();
    }

    
    public Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException {
        return r.getRequest(id);
    }

    public List<Request> getRequests() throws SystemErrorException, NoSuchShedException {
        return r.getRequests();
    }

    public void insertRequest(Request req) throws SystemErrorException {
        r.insertRequest(req);
    }

    public void deleteRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchCarportException, NoSuchShedException {
        r.deleteRequest(id);
    }

    public void insertDimensions(int id, int length, int price, int stock) throws SystemErrorException {
        r.insertDimensions(id, length, price, stock);
    }

    public int getDimensionPrice(int roof_id, int length) throws SystemErrorException {
        return r.getDimensionPrice(roof_id, length);
    }

    public Roof getRoof(int id) throws NoSuchRoofException, SystemErrorException {
        return r.getRoof(id);
    }

    public Roof getRoof(int id, int length) throws SystemErrorException {
        return r.getRoof(id, length);
    }

    public List<Roof> getRoofs(int rooftype) throws SystemErrorException {
        return r.getRoofs(rooftype);
    }

    public ShippingAddress getRequestShippingAddress(int id) throws SystemErrorException {
        return r.getRequestShippingAddress(id);
    }

    // User mapper
    public void insertUser(User user) throws DuplicateException, SystemErrorException {
        u.insertUser(user);
    }

    public User getUser(String email) throws UserNotFoundException, SystemErrorException {
        return u.getUser(email);
    }

    public User getUser(int id) throws UserNotFoundException, SystemErrorException {
        return u.getUser(id);
    }

    // Response mapper
    public List<Response> getResponses(int userId) throws SystemErrorException {
        return rp.getResponses(userId);
    }

    public Response getResponse(int requestId) throws NoSuchResponseException, SystemErrorException {
        return rp.getResponse(requestId);
    }

    public void insertResponse(Response res) throws SystemErrorException {
        rp.insertResponse(res);
    }

    public void deleteResponse(int responseId) throws NoSuchResponseException, SystemErrorException {
        rp.deleteResponse(responseId);
    }

    // Prebuilt carport mapper
    public List<PrebuiltCarport> getAllPrebuiltCarports() throws SystemErrorException {
        return p.getAllPrebuiltCarports();
    }
}
