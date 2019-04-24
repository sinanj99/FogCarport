/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Logic.Manager;
import Logic.NoSuchRoofException;
import java.io.IOException;
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

    private final Manager manager = new Manager();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("command");
        Command command = CommandFactory.from(key);
        try {
            String target = command.execute(request, manager);
            request.getRequestDispatcher(target).forward(request, response);
        } catch (NoSuchRoofException | ServletException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
