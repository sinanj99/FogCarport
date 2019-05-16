/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Obaydah Mohamad
 */
public class DeleteResponseCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException, ClientException {
        PresentationFacade.getInstance().deleteResponse(Integer.parseInt(request.getParameter("responseID")));
        return "FrontController?command=showresponses";
    }
    
}
