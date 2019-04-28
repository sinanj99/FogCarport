/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Data.User;
import Logic.LoginController;
import Logic.Manager;
import Logic.NoMatchException;
import Logic.UserNotFoundException;
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
    public String execute(HttpServletRequest request) throws ServletException, IOException, SQLException {
        User user;
        String email = request.getParameter("email");
        String password = request.getParameter("pword");
        
        try {
            user = Manager.getUser(email);
            LoginController.doesMatch(email, password);
            request.getSession().setAttribute("user", user);
        } catch (UserNotFoundException | NoMatchException e) {
            /*if the user doesn't exist in the database, or  
            if there is no match */
            request.setAttribute("loginResult", e.getMessage());
            return "login.jsp";
        }
        return "frontpage.jsp";
    }

}
