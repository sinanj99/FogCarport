/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.BOM;
import Data.Entity.LineItem;
import Data.Entity.Request;
import Data.Entity.ShippingAddress;
import Logic.Calculator.CalculateBOM;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.SystemErrorException;
import Logic.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Obaydah Mohamad
 */
public class CreateResponseCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException {
        Request r = PresentationFacade.getInstance().getRequest(Integer.parseInt(request.getParameter("requestID")));
        ShippingAddress s = PresentationFacade.getInstance().getRequestShippingAddress(r.getReq_id());
        int productionPrice = 0;
        
        BOM bom;
        CalculateBOM b = new CalculateBOM();
        try {
            if (r.getCarport().getInclination() != 0) {
                bom = b.inclineRoofBOM(r);
            } else {
                bom = b.generateFlatRoofCarportBOM(r);
            }
        } catch(SystemErrorException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
        
        for(LineItem l : bom.getLineitems()){
            productionPrice += l.getPrice();
        }
        
        request.setAttribute("request", r);
        request.setAttribute("shippingAddress", s);
        request.setAttribute("productionPrice", productionPrice);
        
        request.setAttribute("status", "offernotsend");
        
        if(PresentationFacade.getInstance().getResponse(r.getReq_id()) != null){
            request.setAttribute("status", "offersend");
            request.setAttribute("response", PresentationFacade.getInstance().getResponse(r.getReq_id()));
        }
        
        return "create_response.jsp";
    }
    
}