/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Logic.Manager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class LoginCommand implements Command {

    public LoginCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, Manager manager) {
        return "login.jsp";
    }
    
}
