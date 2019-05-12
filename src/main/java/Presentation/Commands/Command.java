/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Logic.Controller.Facade;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sinanjasar
 */
public interface Command {
    
    String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException, SQLException, IOException;
}
