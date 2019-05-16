/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.User;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Obaydah Mohamad
 */
public class ShowResponsesCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException{
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) return "frontpage.jsp";
        request.setAttribute("responses", PresentationFacade.getInstance().getResponses(user.getId()));
        return "showresponses.jsp";
    }
    
}