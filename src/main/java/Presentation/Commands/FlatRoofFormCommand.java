/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class FlatRoofFormCommand implements Command {

    public FlatRoofFormCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) throws NoSuchRoofException, SystemErrorException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("target", "flat");
            return "jsp/login.jsp";
        }
        request.setAttribute("roofs", PresentationFacade.getInstance().getRoofs(0)); // 0 for flat roofs 
        return "jsp/flatroof.jsp";
    }

}
