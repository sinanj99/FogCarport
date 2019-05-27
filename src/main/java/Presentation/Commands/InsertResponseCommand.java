/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.Response;
import Data.Entity.User;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.SystemErrorException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Obaydah Mohamad
 */
public class InsertResponseCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException, ClientException{
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) throw new ClientException("jsp/frontpage.jsp", "Log venligst ind for at tilgå denne side!"); 
        if(!user.isSeller()) throw new ClientException("jsp/frontpage.jsp", "Du har ikke adgang til denne funktion!"); 
        int sellerId = user.getId();
        
        int requestId = 0;
        try{
            requestId = Integer.parseInt(request.getParameter("requestID"));
        }catch(NumberFormatException e){
            throw new InvalidInputException("FrontController?command=showrequests", "Ugyldigt ordre nummer!");
        }
        
        int sellPrice = 0;
        try{
            sellPrice = Integer.parseInt(request.getParameter("sellprice").trim());
            if(sellPrice < 1) throw new InvalidInputException("FrontController?command=createresponse&requestID=" + requestId, "Ugyldig pris!");
        }catch(NumberFormatException e){
            throw new InvalidInputException("FrontController?command=createresponse&requestID=" + requestId, "Ugyldig pris!");
        }
        
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datePlaced = String.valueOf(date.format(dateFormat));
        
        Response r = new Response(PresentationFacade.getInstance().getRequest(requestId), sellerId, datePlaced, sellPrice);
        
        PresentationFacade.getInstance().insertResponse(r);
        
        return "FrontController?command=createresponse&requestID=" + requestId;
    }
    
}
