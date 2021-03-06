/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.User;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.SystemErrorException;
import javax.servlet.http.HttpServletRequest;

/**
 * Command that retrieves all of the responses associated with a specifc user.
 * @author Obaydah Mohamad
 */
public class ShowResponsesCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException{
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) return "jsp/frontpage.jsp";
        if(user.isAdmin() || user.isSeller()) return "FrontController?command=frontpageredirect";
        request.setAttribute("responses", PresentationFacade.getInstance().getResponses(user.getId()));
        request.setAttribute("access", "true");
        return "jsp/showresponses.jsp";
    }
    
}
