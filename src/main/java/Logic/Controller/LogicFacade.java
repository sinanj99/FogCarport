/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Controller;

import Data.Mappers.DataFacade;
import Data.Database.DataSourceMysql;
import Data.Entity.Material;
import Data.Entity.PrebuiltCarport;
import Data.Entity.Request;
import Data.Entity.Response;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Exceptions.NoSuchRoofException;
import Data.Entity.Roof;
import Data.Entity.ShippingAddress;
import Data.Entity.User;
import Logic.Calculator.CalculatePrice;
import Presentation.Exceptions.DuplicateException;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import java.util.LinkedHashMap;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
public class LogicFacade {

    private static LogicFacade instance = null;
    private static CalculatePrice cp = new CalculatePrice();

    //prevents other classes from creating instance
    private LogicFacade() {
    }

    //global point of access
    public static LogicFacade getInstance() {
        if (instance == null) {
            instance = new LogicFacade();
        }
        return instance;
    }
    public void updatePrices(int price, int id, String type) throws InvalidInputException, NoSuchMaterialException, SystemErrorException {
        cp.updatePrices(price, id, type);
    }
    public User getUser(String email) throws UserNotFoundException, SystemErrorException {
        return DataFacade.getInstance().getUser(email);
    }
    public LinkedHashMap<Integer, Integer> getPricesWithLength(int id) throws SystemErrorException, NoSuchMaterialException {
        return DataFacade.getInstance().getPricesWithLength(id);
    }
    public void updatePriceFittings(int price, int id) throws SystemErrorException, NoSuchMaterialException {
        DataFacade.getInstance().updatePriceFittings(price, id);
    }
    public void updatePricesRoof(LinkedHashMap<Integer, Integer> prices, int id) throws SystemErrorException, NoSuchRoofException, InvalidInputException {
        DataFacade.getInstance().updatePricesRoof(prices, id);
    }
    public LinkedHashMap<Integer, Integer> getRoofLengthPrices(int id) throws SystemErrorException {
        return DataFacade.getInstance().getRoofLengthPrices(id);
    }
    public void updatePriceWithLength(LinkedHashMap<Integer, Integer> prices, int id) throws SystemErrorException, NoSuchMaterialException, InvalidInputException {
        DataFacade.getInstance().updatePriceWithLength(prices, id);
    }
    public Roof getRoof(int id) throws NoSuchRoofException, SystemErrorException {
        return DataFacade.getInstance().getRoof(id);
    }

    public Material getWoodMaterial(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        return DataFacade.getInstance().getWoodMaterial(id, length);
    }

    public Material getFitting(int id) throws SystemErrorException, NoSuchMaterialException {
        return DataFacade.getInstance().getFitting(id);
    }

    public Roof newGetRoof(int id, int length) throws SystemErrorException {
        return DataFacade.getInstance().getRoof(id, length);
    }
    public List<Roof> getRoofs() throws SystemErrorException {
        return DataFacade.getInstance().getRoofs();
    }
    
    public List<Material> getMaterials() throws SystemErrorException {
        return DataFacade.getInstance().getMaterials();
    }
    public List<Roof> getRoofs(int rooftype) throws NoSuchRoofException, NoSuchRoofException, SystemErrorException {
        return DataFacade.getInstance().getRoofs(rooftype);
    }

    public void insertUser(User user) throws DuplicateException, SystemErrorException {
        DataFacade.getInstance().insertUser(user);
    }
     public void insertRequest(Request req) throws SystemErrorException {
        DataFacade.getInstance().insertRequest(req);
    }
    
    public void deleteRequest(int id) throws NoSuchRequestException, SystemErrorException{
        DataFacade.getInstance().deleteRequest(id);
    }
    public List<Request> getRequests() throws NoSuchShedException, SystemErrorException {
        return DataFacade.getInstance().getRequests();
    }

    public Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException {
        return DataFacade.getInstance().getRequest(id);
    }
    public List<PrebuiltCarport> getAllPrebuiltCarports() throws SystemErrorException {
        return DataFacade.getInstance().getAllPrebuiltCarports();
    }
    
    public ShippingAddress getRequestShippingAddress(int id) throws SystemErrorException{
        return DataFacade.getInstance().getRequestShippingAddress(id);
    }
    public void insertResponse(Response res) throws SystemErrorException{
        DataFacade.getInstance().insertResponse(res);
    }
    
    public void deleteResponse(int responseId) throws NoSuchResponseException, SystemErrorException{
        DataFacade.getInstance().deleteResponse(responseId);
    }
    
    public Response getResponse(int requestId) throws NoSuchResponseException, SystemErrorException{
        return DataFacade.getInstance().getResponse(requestId);
    }
    
    public List<Response> getResponses(int userId) throws SystemErrorException{
        return DataFacade.getInstance().getResponses(userId);
    }
}
