/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.PrebuiltCarport;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchPrebuiltCarportException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.SystemErrorException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * Command that creates a list of prebuilt carports to present in jsp file
 * @author Kasper Jeppesen
 */
public class PrebuiltCarportCommand implements Command
{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException
    {
        ArrayList<PrebuiltCarport> prebuiltCarports = new ArrayList();
        try
        {
            prebuiltCarports = (ArrayList) PresentationFacade.getInstance().getAllPrebuiltCarports();
            request.setAttribute("prebuiltcarport", prebuiltCarports);
        }
        catch (NoSuchPrebuiltCarportException ex)
        {
            //If there are no prebuilt carports
            request.setAttribute("Error", "Something went wrong");
            return "jsp/error.jsp";
        }
                    

        request.setAttribute("access", "true");
        return "jsp/prebuiltcarport.jsp";
    }
    
}
