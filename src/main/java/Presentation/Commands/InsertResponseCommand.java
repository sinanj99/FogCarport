/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.Response;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
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
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException{
        int requestId = Integer.parseInt(request.getParameter("requestID"));
        int sellerId = Integer.parseInt(request.getParameter("sellerID"));
        int sellPrice = Integer.parseInt(request.getParameter("sellprice").trim());
        
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datePlaced = String.valueOf(date.format(dateFormat));
        
        Response r = new Response(PresentationFacade.getInstance().getRequest(requestId), sellerId, datePlaced, sellPrice);
        
        PresentationFacade.getInstance().insertResponse(r);
        
        return "FrontController?command=createresponse&requestID=" + requestId;
    }
    
}
