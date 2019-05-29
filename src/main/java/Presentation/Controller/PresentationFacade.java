/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Controller;
import Data.Entity.BOM;
import Data.Entity.Carport;
import Data.Entity.Material;
import Data.Entity.PrebuiltCarport;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Exceptions.DuplicateException;
import Presentation.Exceptions.NoSuchRoofException;
import Data.Entity.Request;
import Data.Entity.Response;
import Data.Entity.Roof;
import Data.Entity.ShippingAddress;
import Data.Entity.User;
import Logic.Logic.LogicFacade;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoMatchException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.NoSuchShedException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public class PresentationFacade {

    private static PresentationFacade instance = null;

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

    public void updatePrices(int price, int id, String type) throws SystemErrorException, NoSuchMaterialException, InvalidInputException {
        LogicFacade.getInstance().updatePrices(price, id, type);
    }

    public void updatePriceFittings(int price, int id) throws SystemErrorException, NoSuchMaterialException {
        LogicFacade.getInstance().updatePriceFittings(price, id);
    }

    public LinkedHashMap<Integer, Integer> getPricesWithLength(int id) throws SystemErrorException, NoSuchMaterialException {
        return LogicFacade.getInstance().getPricesWithLength(id);
    }

    public LinkedHashMap<Integer, Integer> getRoofLengthPrices(int id) throws SystemErrorException, NoSuchMaterialException {
        return LogicFacade.getInstance().getRoofLengthPrices(id);
    }

    public List<Roof> getRoofs() throws SystemErrorException {
        return LogicFacade.getInstance().getRoofs();
    }
    public List<Roof> getRoofsSorted() throws SystemErrorException {
        return LogicFacade.getInstance().getRoofsSorted();
    }

    public List<Material> getMaterials() throws SystemErrorException {
        return LogicFacade.getInstance().getMaterials();
    }

    public List<Roof> getRoofs(int rooftype) throws NoSuchRoofException, NoSuchRoofException, SystemErrorException {
        return LogicFacade.getInstance().getRoofs(rooftype);
    }

    public void insertUser(User user) throws DuplicateException, SystemErrorException {
        LogicFacade.getInstance().insertUser(user);
    }

    public User getUser(String email) throws UserNotFoundException, SystemErrorException {
        return LogicFacade.getInstance().getUser(email);
    }

    public void insertRequest(Request req) throws SystemErrorException {
        LogicFacade.getInstance().insertRequest(req);
    }

    public void deleteRequest(int id) throws NoSuchRequestException, SystemErrorException {
        LogicFacade.getInstance().deleteRequest(id);
    }

    public Material getWoodMaterial(int id, int length) throws NoSuchMaterialException, SystemErrorException {
        return LogicFacade.getInstance().getWoodMaterial(id, length);
    }

    public Material getFitting(int id) throws SystemErrorException, NoSuchMaterialException {
        return LogicFacade.getInstance().getFitting(id);
    }

    public List<Request> getRequests() throws NoSuchShedException, SystemErrorException {
        return LogicFacade.getInstance().getRequests();
    }

    public Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException {
        return LogicFacade.getInstance().getRequest(id);
    }

    public List<PrebuiltCarport> getAllPrebuiltCarports() throws SystemErrorException {
        return LogicFacade.getInstance().getAllPrebuiltCarports();
    }

    public ShippingAddress getRequestShippingAddress(int id) throws SystemErrorException {
        return LogicFacade.getInstance().getRequestShippingAddress(id);
    }

    //Response
    public void insertResponse(Response res) throws SystemErrorException {
        LogicFacade.getInstance().insertResponse(res);
    }

    public void deleteResponse(int responseId) throws NoSuchResponseException, SystemErrorException {
        LogicFacade.getInstance().deleteResponse(responseId);
    }

    public Response getResponse(int requestId) throws NoSuchResponseException, SystemErrorException {
        return LogicFacade.getInstance().getResponse(requestId);
    }

    public List<Response> getResponses(int userId) throws SystemErrorException {
        return LogicFacade.getInstance().getResponses(userId);
    }

    // Logic 
    public void doesMatch(String email, String password, User user) throws UserNotFoundException, SystemErrorException, NoMatchException {
        LogicFacade.getInstance().doesMatch(email, password, user);
    }

    public String drawFlat(Carport carport) {
        return LogicFacade.getInstance().drawFlat(carport);
    }

    public String drawTopIncline(Carport carport) {
        return LogicFacade.getInstance().drawTopIncline(carport);
    }

    public String drawFrontIncline(Carport carport) {
        return LogicFacade.getInstance().drawFrontIncline(carport);
    }

    public BOM inclineRoofBOM(Request r) throws NoSuchMaterialException, SystemErrorException {
        return LogicFacade.getInstance().inclineRoofBOM(r);
    }

    public BOM generateFlatRoofCarportBOM(Request r) throws NoSuchMaterialException, SystemErrorException {
        return LogicFacade.getInstance().generateFlatRoofCarportBOM(r);
    }
}
