/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.User;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 * Command that allows a seller to update the price on a response(offer on a carport).
 * @author Obaydah Mohamad
 */
public class UpdateResponsePriceCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws UserNotFoundException, InvalidInputException{
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) return "jsp/frontpage.jsp";
        if(!user.isSeller()) return "FrontController?command=frontpageredirect";
        
        int id = 0;
        int newPrice = 0;
        String target = "";
        
        try{
            id = Integer.parseInt(request.getParameter("requestID"));
        }catch(NumberFormatException e){
            throw new InvalidInputException("FrontController?command=showrequests", "Indtast venligst kun tal!");
        }
        
        try{
            newPrice = Integer.parseInt(request.getParameter("sellprice"));
        }catch(NumberFormatException e){
            target = "FrontController?command=createresponse&requestID=" + id;
            throw new InvalidInputException(target, "Indtast venligst kun tal!");
        }
        if(newPrice == 0) throw new InvalidInputException("FrontController?command=createresponse&requestID=" + id, "En carport må ikke gives væk gratis!");
        
        request.setAttribute("updatedPrice", newPrice);
        return "FrontController?command=createresponse&requestID=" + id;
    }
    
}
