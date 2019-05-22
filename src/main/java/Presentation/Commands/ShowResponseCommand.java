/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.Request;
import Data.Entity.Response;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Obaydah Mohamad
 */
public class ShowResponseCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException, ClientException, NoSuchRequestException, NoSuchShedException {
        Response r = PresentationFacade.getInstance().getResponse(Integer.parseInt(request.getParameter("requestID")));
        Request req = PresentationFacade.getInstance().getRequest(r.getRequest().getRequestId());
        
        request.setAttribute("response", r);
        request.setAttribute("request", req);
        
        System.out.println("Response: " + r);
        System.out.println("Request: " + req);
        return "jsp/showresponse.jsp";
    }
    
}
