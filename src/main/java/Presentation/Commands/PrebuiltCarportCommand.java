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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
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
            System.out.println(prebuiltCarports);
            request.setAttribute("prebuiltCarport", prebuiltCarports);
        }
        catch (NoSuchPrebuiltCarportException ex)
        {
            //If there are no prebuilt carports
            Logger.getLogger(PrebuiltCarportCommand.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("Error", "Something went wrong");
            return "jsp/error.jsp";
        }
        return "jsp/prebuiltCarport.jsp";
    }
    
}
