/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Controller;

import Presentation.Commands.Command;
import Logic.Exceptions.DuplicateException;
import Logic.Controller.Manager;
import Logic.Exceptions.UserNotFoundException;
import Logic.Exceptions.NoSuchRoofException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sinanjasar
 */
@WebServlet(name = "FrontController", urlPatterns = {"/jsp/FrontController/*"})
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("command");
        Command command = CommandFactory.from(key);
        try {
            String target = command.execute(request);
            request.getRequestDispatcher(target).forward(request, response);
        } catch (NoSuchRoofException | ServletException | IOException | SQLException | UserNotFoundException ex) {
            System.out.println("EXCEPTION: " + ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
