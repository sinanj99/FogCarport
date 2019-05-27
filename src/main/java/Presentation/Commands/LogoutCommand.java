/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Presentation.Exceptions.NoSuchRoofException;
import javax.servlet.http.HttpServletRequest;

/**
 * Command that logs a user out
 * @author sinanjasar
 */
public class LogoutCommand implements Command {

    public LogoutCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) throws NoSuchRoofException {
        request.getSession().invalidate();
        return "jsp/login.jsp";
    }
    
}
