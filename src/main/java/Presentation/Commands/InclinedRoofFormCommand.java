/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Logic.Controller.Manager;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Obaydah Mohamad
 */
public class InclinedRoofFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, UserNotFoundException, NoSuchRoofException, SQLException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            return "login.jsp";
        }
        request.getSession().setAttribute("inclined", "true");
        
        request.setAttribute("roofs", Manager.getRoofs(1)); // 0 for flat roofs 
        return "inclineroof.jsp";
    }
    
}