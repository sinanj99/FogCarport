/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Controller;

import Presentation.Commands.Command;
import Presentation.Exceptions.DuplicateException;
import Logic.Controller.LogicFacade;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoSuchShedException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sinanjasar
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController/*"})
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("command");
        Command command = CommandFactory.from(key);
        try {
            String target = command.execute(request);
            request.getRequestDispatcher(target).forward(request, response);
        } catch (SystemErrorException e) {
            String message = e.getMessage();
            request.setAttribute("message", message);
            System.out.println(message);
            request.getRequestDispatcher(e.getTarget()).forward(request, response);
        } catch (ClientException e) {
            String message = e.getMessage();
            if (message == null) {
                message = "Der opstod en fejl!";
            }
            String detail = (e.getDetail());
            System.out.println("message: " + message);
            System.out.println("target: " + e.getTarget());
            request.setAttribute("error", message);
            request.setAttribute("detail", detail);
            request.getRequestDispatcher(e.getTarget()).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
