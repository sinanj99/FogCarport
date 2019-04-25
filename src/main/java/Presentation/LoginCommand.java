/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Data.User;
import Logic.LoginController;
import Logic.Manager;
import Logic.UserNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sinanjasar
 */
public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException, UserNotFoundException {
        String target = "frontpage.jsp";
        User user;
        String email = request.getParameter("email");
        String password = request.getParameter("pword");
        
            //if the user doesn't exist in the database. 
            if(Manager.getUser(email).getEmail()== null
               || Manager.getUser(email).getEmail().isEmpty())
            {
                request.setAttribute("loginResult", "noUser");
                target = "login.jsp";
            }
            //if the inserted password does match the password of the user with the inserted email. 
            else if (LoginController.doesMatch(email, password)) {
                user = Manager.getUser(email);
                request.getSession().setAttribute("user", user);
                
            //if the inserted password does match the password of the user with the inserted email. 
            } else if(!LoginController.doesMatch(email, password)){
                request.setAttribute("loginResult", "loginFailed");
                target = "login.jsp";
            }
            
        return target;
    }
    
}
