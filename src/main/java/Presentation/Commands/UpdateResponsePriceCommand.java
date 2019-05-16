/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.UserNotFoundException;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Obaydah Mohamad
 */
public class UpdateResponsePriceCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException{
        boolean isn = StringUtils.isStrictlyNumeric(request.getParameter("sellprice"));
        int id = (int) Integer.parseInt(request.getParameter("requestID"));
        if(isn) request.setAttribute("updatedPrice", Integer.parseInt(request.getParameter("sellprice")));
        
        return "FrontController?command=createresponse&requestID=" + id;
    }
    
}
