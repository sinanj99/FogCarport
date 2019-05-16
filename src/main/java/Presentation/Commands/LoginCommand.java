/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.User;
import Logic.Controller.LoginController;
import Logic.Controller.LogicFacade;
import Presentation.Controller.PresentationFacade;
import Logic.Exceptions.NoMatchException;
import Logic.Exceptions.SystemErrorException;
import Logic.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        User user;
        String email = request.getParameter("email");
        String password = request.getParameter("pword");
        
        try {
            user = PresentationFacade.getInstance().getUser(email);
            LoginController.doesMatch(email, password);
            request.getSession().setAttribute("user", user);
        } catch (UserNotFoundException | NoMatchException e) {
            /*if the user doesn't exist in the database, or  
            if there is no match */
            request.setAttribute("loginResult", e.getMessage());
            return "jsp/login.jsp";
        } catch(SystemErrorException e) {
            request.setAttribute("error", e.getMessage());
            return "jsp/error.jsp";
        }
//        if(user.isSeller() == true) return "jsp/frontpage.jsp";
        return "jsp/frontpage.jsp";
    }

}
