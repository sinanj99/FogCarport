/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Controller;

import Presentation.Commands.Command;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.ClientException;
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
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController/*"})
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String key = request.getParameter("command");
        Command command = CommandFactory.from(key);
        try {
            String target = command.execute(request);
            String redirect = (String) request.getAttribute("redirect");
            if (redirect != null && redirect.equals("true")) {
                response.sendRedirect(target);
            } else {
                request.getRequestDispatcher(target).forward(request, response);
            }
        } catch (SystemErrorException e) {
            String detail = e.getDetail();
            String message = e.getMessage();
            request.setAttribute("message", message);
            System.out.println("detail: " + detail);
            request.getRequestDispatcher(e.getTarget()).forward(request, response);
        } catch (ClientException e) {
            String message = e.getMessage();
            String target = e.getTarget();
            if (target == null || target.isEmpty()) {
                target = "jsp/error.jsp";
            } else if (message == null) {
                message = "Der opstod en fejl!";
            }
            String detail = (e.getDetail());
            System.out.println("message: " + message);
            System.out.println("target: " + target);
            request.setAttribute("error", message);
            request.setAttribute("detail", detail);
            request.getRequestDispatcher(target).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
