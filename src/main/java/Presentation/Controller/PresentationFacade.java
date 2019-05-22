/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Controller;

import Data.Database.DataSourceMysql;
import Data.Entity.Material;
import Data.Entity.PrebuiltCarport;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Exceptions.DuplicateException;
import Presentation.Exceptions.NoSuchRoofException;
import Data.Mappers.IMaterialMapper;
import Data.Mappers.IRequestMapper;
import Data.Mappers.IUserMapper;
import Data.Entity.Request;
import Data.Entity.Response;
import Data.Entity.Roof;
import Data.Entity.ShippingAddress;
import Data.Entity.User;
import Data.Mappers.IPrebuiltCarportMapper;
import Logic.Calculator.CalculatePrice;
import Data.Mappers.IResponseMapper;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.NoSuchShedException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
public class PresentationFacade {

    private static PresentationFacade instance = null;
    private CalculatePrice cp = new CalculatePrice();
    private static final DataSource ds = new DataSourceMysql().getDataSource();
    private static final IRequestMapper r = IRequestMapper.instance();
    private static final IResponseMapper rp = IResponseMapper.instance();
    private static final IMaterialMapper m = IMaterialMapper.instance();
    private static final IUserMapper u = IUserMapper.instance();
    private static final IPrebuiltCarportMapper p = IPrebuiltCarportMapper.instance();

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
            p.setDataSource(ds);
            rp.setDataSource(ds);
        }
        return instance;
    }
    public void updatePrices(int price, int id) throws SystemErrorException, NoSuchMaterialException, InvalidInputException {
        m.updatePriceWithLength(price, id);
    }
    public void updatePriceFittings(int price, int id) throws SystemErrorException, NoSuchMaterialException {
        m.updatePriceFittings(price, id);
    }
    public void updatePricesRoof(int price, int id) throws SystemErrorException, NoSuchRoofException, InvalidInputException {
        m.updatePriceRoof(price, id);
    }
    public List<Roof> getRoofs() throws SystemErrorException {
        return m.getRoofs();
    }
    public List<Material> getMaterials() throws SystemErrorException {
        return m.getMaterials();
    }
    public List<Roof> getRoofs(int rooftype) throws NoSuchRoofException, NoSuchRoofException, SystemErrorException {
        return r.getRoofs(rooftype);
    }

    public void insertUser(User user) throws DuplicateException, SystemErrorException {
        u.insertUser(user);
    }

    public User getUser(String email) throws UserNotFoundException, SystemErrorException {
        return u.getUser(email);
    }

    public void insertRequest(Request req) throws SystemErrorException {
        r.insertRequest(req);
    }
    
    public void deleteRequest(int id) throws NoSuchRequestException, SystemErrorException{
        r.deleteRequest(id);
    }
    
    public Material getWoodMaterial(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        return m.getWoodMaterial(id, length);
    }

    public Material getFitting(int id) throws SystemErrorException, NoSuchMaterialException {
        return m.getFitting(id);
    }

    public List<Request> getRequests() throws NoSuchShedException, SystemErrorException {
        return r.getRequests();
    }

    public Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException {
        return r.getRequest(id);
    }
    
    public List<PrebuiltCarport> getAllPrebuiltCarports() throws SystemErrorException {
        return p.getAllPrebuiltCarports();
    }
    
    public ShippingAddress getRequestShippingAddress(int id) throws SystemErrorException{
        return r.getRequestShippingAddress(id);
    }
    
    //Response
    
    public void insertResponse(Response res) throws SystemErrorException{
        rp.insertResponse(res);
    }
    
    public void deleteResponse(int responseId) throws NoSuchResponseException, SystemErrorException{
        rp.deleteResponse(responseId);
    }
    
    public Response getResponse(int requestId) throws NoSuchResponseException, SystemErrorException{
        return rp.getResponse(requestId);
    }
    
    public List<Response> getResponses(int userId) throws SystemErrorException{
        return rp.getResponses(userId);
    }
}
