/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.PrebuiltCarport;
import Logic.Controller.Facade;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchPrebuiltCarportException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kasper Jeppesen
 */
public class PrebuiltCarportCommand implements Command
{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException, SQLException, IOException
    {
        ArrayList<PrebuiltCarport> prebuiltCarports = new ArrayList();
        
        try
        {
            prebuiltCarports = (ArrayList) Facade.getAllPrebuiltCarports();
            System.out.println(prebuiltCarports);
            request.setAttribute("prebuiltCarport", prebuiltCarports);
        }
        catch (NoSuchPrebuiltCarportException ex)
        {
            //If there are no prebuilt carports
            Logger.getLogger(PrebuiltCarportCommand.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("Error", "Something went wrong");
            return "errorpage.jsp";
        }
        return "prebuiltCarport.jsp";
    }
    
}