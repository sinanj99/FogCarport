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
import Data.Entity.User;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.NoSuchShedException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Obaydah Mohamad
 */
public class CreateResponseCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, NoSuchResponseException, SystemErrorException, NoSuchRequestException, NoSuchShedException, ClientException {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) throw new ClientException("jsp/frontpage.jsp", "Log venligst ind for at tilgå denne side!"); 
        if(!user.isSeller()) throw new ClientException("jsp/frontpage.jsp", "Du har ikke adgang til denne funktion!"); 
        
        Request r = PresentationFacade.getInstance().getRequest(Integer.parseInt(request.getParameter("requestID")));
        ShippingAddress s = PresentationFacade.getInstance().getRequestShippingAddress(r.getRequestId());
        int productionPrice = 0;

        BOM bom;
        if (r.getCarport().getInclination() != 0) {
            bom = PresentationFacade.getInstance().inclineRoofBOM(r);
        } else {
            bom = PresentationFacade.getInstance().generateFlatRoofCarportBOM(r);
        }

        for (LineItem l : bom.getLineitems()) {
            productionPrice += l.getPrice();
        }

        request.setAttribute("request", r);
        request.setAttribute("shippingAddress", s);
        request.setAttribute("productionPrice", productionPrice);

        request.setAttribute("status", "offernotsend");
        
        if (PresentationFacade.getInstance().getResponse(r.getRequestId()) != null) {
            request.setAttribute("status", "offersend");
            request.setAttribute("response", PresentationFacade.getInstance().getResponse(r.getRequestId()));
        }
        return "jsp/create_response.jsp";
    }

}
