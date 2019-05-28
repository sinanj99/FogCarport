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
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 * Command which handles the deletion of a specific request.
 * Used by a seller.
 * @author Obaydah Mohamad
 */
public class DeleteRequestCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException, ClientException, NoSuchShedException {
        int id = 0;
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) return "jsp/frontpage.jsp"; 
        if(!user.isSeller()) return "FrontController?Command=frontpageredirect";
        
        try{
            id = Integer.parseInt(request.getParameter("requestID"));
        }catch(NumberFormatException e){
            throw new InvalidInputException("FrontController?command=showrequests", "Ugyldigt id nummer!");
        }
        PresentationFacade.getInstance().deleteRequest(id);
        return "FrontController?command=showrequests";
    }
    
}
