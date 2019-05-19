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
import Presentation.Exceptions.NoMatchException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.UserNotFoundException;
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
    public String execute(HttpServletRequest request) throws SystemErrorException, NoMatchException, UserNotFoundException {

        User user;
        String email = request.getParameter("email");
        String password = request.getParameter("pword");
        try {
            user = PresentationFacade.getInstance().getUser(email);
            LoginController.doesMatch(email, password);
            } catch(UserNotFoundException e) {
                throw new UserNotFoundException("jsp/login.jsp", "Bruger findes ikke!");
            } catch(NoMatchException e) {
                throw new NoMatchException("jsp/login.jsp", "Forkert adgangskode!");
            }
        request.getSession().setAttribute("user", user);
        return "jsp/frontpage.jsp";
    }

}
