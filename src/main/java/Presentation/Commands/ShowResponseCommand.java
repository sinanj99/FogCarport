/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.Response;
import Data.Entity.User;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.InvalidInputException;
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
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) throw new ClientException("jsp/frontpage.jsp", "Du skal være logget ind for at se tilbud!");
        
        int requestId = 0;
        try{
            requestId = Integer.parseInt(request.getParameter("requestID"));
        }catch(NumberFormatException e){
            throw new InvalidInputException("FrontController?command=showresponses", "Ugyldigt id nummer!");
        }
        if(requestId < 1) throw new InvalidInputException("FrontController?command=showresponses", "Ugyldigt id nummer!");
        
        Response r = PresentationFacade.getInstance().getResponse(requestId);
        if(r.getRequest().getUserId() != user.getId()) throw new InvalidInputException("FrontController?command=showresponses", "Dette tilbud tilhører ikke dig!");
        
        request.setAttribute("response", r);
        return "jsp/showresponse.jsp";
    }
    
}
