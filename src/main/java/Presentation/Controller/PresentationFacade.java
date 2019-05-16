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
import Logic.Exceptions.UserNotFoundException;
import Logic.Exceptions.DuplicateException;
import Logic.Exceptions.NoSuchRoofException;
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
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchPrebuiltCarportException;
import Logic.Exceptions.SystemErrorException;
import Presentation.Exceptions.InvalidInputException;
import java.util.List;
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

    public Material getMaterialNoLength(int id) throws SystemErrorException, NoSuchMaterialException {
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
    
    public List<PrebuiltCarport> getAllPrebuiltCarports() throws NoSuchPrebuiltCarportException {
        return p.getAllPrebuiltCarports();
    }
    
    public ShippingAddress getRequestShippingAddress(int id){
        return r.getRequestShippingAddress(id);
    }
    
    //Response
    
    public void insertResponse(Response res){
        rp.insertResponse(res);
    }
    
    public Response getResponse(int requestId){
        return rp.getResponse(requestId);
    }
    
    public List<Response> getResponses(int userId){
        return rp.getResponses(userId);
    }
}