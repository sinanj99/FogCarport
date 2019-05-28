/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.User;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 * Commands which handles the deletion of a specific response.
 * Used when a user declines an offer on a carport.
 * @author Obaydah Mohamad
 */
public class DeleteResponseCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException, ClientException {
        int id = 0;
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) return "jsp/frontpage.jsp"; 
        if(user.isSeller() || user.isAdmin()) return "FrontController?Command=frontpageredirect";
        
        try{
            id = Integer.parseInt(request.getParameter("requestID"));
        }catch(NumberFormatException e){
            throw new InvalidInputException("FrontController?command=showresponses", "Ugyldigt id nummer!");
        }
        
        PresentationFacade.getInstance().deleteResponse(id);
        return "FrontController?command=showresponses";
    }
    
}
