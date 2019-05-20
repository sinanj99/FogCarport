/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Controller;

import Data.Database.DBConnector;
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
import Presentation.Exceptions.NoSuchPrebuiltCarportException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.NoSuchShedException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.xml.ws.RespectBinding;

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
    public void updatePricesNoLength(int price, int id) throws SystemErrorException, NoSuchMaterialException {
        m.updatePriceNoLength(price, id);
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
    public void insertDimensions(int id, int length, int price) throws SystemErrorException {
        r.insertDimensions(id, length, price);
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
    
    public static void main(String[] args) throws SystemErrorException {
        PresentationFacade p = PresentationFacade.getInstance();
        try {
            p.deleteRequest(10);
        } catch (NoSuchRequestException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Roof getRoof(int id) throws NoSuchRoofException, SystemErrorException {
        return r.getRoof(id);
    }

    public int getDimensionPrice(int roof_id, int length) throws SystemErrorException {
        return r.getDimensionPrice(roof_id, length);
    }

    public Material getMaterial(String name) throws NoSuchMaterialException {
        return IMaterialMapper.instance().getMaterial_(name);
    }

    public Material getMaterialWithLength(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        return m.getMaterialWithLength(id, length);
    }

    public Material getMaterialNoLength(int id) throws SystemErrorException, NoSuchMaterialException {
        return m.getMaterialNoLength(id);
    }

    public List<Request> getRequests() throws NoSuchShedException, SystemErrorException {
        return r.getRequests();
    }

    public Roof newGetRoof(int id, int length) throws SystemErrorException {
        return r.getRoof(id, length);
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
    
    public void insertResponse(Response res){
        rp.insertResponse(res);
    }
    
    public void deleteResponse(int responseId) throws NoSuchResponseException{
        rp.deleteResponse(responseId);
    }
    
    public Response getResponse(int requestId){
        return rp.getResponse(requestId);
    }
    
    public List<Response> getResponses(int userId){
        return rp.getResponses(userId);
    }
}
