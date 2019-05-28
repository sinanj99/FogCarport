/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Logic.Logic.LogicFacade;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Command for loading inclined roof form jsp including a list of available roofs
 * @author Obaydah Mohamad
 */
public class InclinedRoofFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws UserNotFoundException, NoSuchRoofException, SystemErrorException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("target", "inclined");
            return "jsp/login.jsp";
        }
        request.setAttribute("roofs", PresentationFacade.getInstance().getRoofs(1)); // 0 for flat roofs
        request.setAttribute("access", "true");
        return "jsp/inclineroof.jsp";
    }
    
}
