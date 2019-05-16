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
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException{
        int reqId = Integer.parseInt(request.getParameter("requestID"));
        int userId = Integer.parseInt(request.getParameter("userID"));
        int empId = Integer.parseInt(request.getParameter("empID"));
        int carportId = Integer.parseInt(request.getParameter("carportID"));
        int shedId = Integer.parseInt(request.getParameter("shedID"));
        int productionPrice = Integer.parseInt(request.getParameter("productionprice").trim());
        int sellPrice = Integer.parseInt(request.getParameter("sellprice").trim());
        
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datePlaced = String.valueOf(date.format(dateFormat));
        
        Response r = new Response(reqId, userId, empId, carportId, shedId, datePlaced, productionPrice, sellPrice);
        
        PresentationFacade.getInstance().insertResponse(r);
        
        return "FrontController?command=showrequests";
    }
    
}
