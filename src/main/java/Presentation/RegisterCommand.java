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
public class RegisterCommand implements Command {

    public RegisterCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, Manager manager) {
        return "register.jsp";
    }
    
}
