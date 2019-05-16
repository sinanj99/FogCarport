/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.Roof;
import Logic.Controller.LogicFacade;
import Presentation.Controller.PresentationFacade;
import Logic.Exceptions.NoSuchRoofException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sinanjasar
 */
public class FlatRoofFormCommand implements Command {

    public FlatRoofFormCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) throws NoSuchRoofException {
        if (request.getSession().getAttribute("user") == null) {
            return "jsp/login.jsp";
        }
        request.getSession().setAttribute("inclined", "false");
        request.setAttribute("roofs", PresentationFacade.getInstance().getRoofs(0)); // 0 for flat roofs 
        return "jsp/flatroof.jsp";
    }

}
