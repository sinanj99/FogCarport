/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 * Command that shows a list of all requests
 * @author sinanjasar
 */
public class ShowRequestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws UserNotFoundException, NoSuchRoofException, SystemErrorException, NoSuchShedException {

        request.setAttribute("requests", PresentationFacade.getInstance().getRequests());
        System.out.println(request.getAttribute("requests"));
        return "jsp/showrequests.jsp";
    }

}
