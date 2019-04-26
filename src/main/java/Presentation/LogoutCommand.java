/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Logic.NoSuchRoofException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class LogoutCommand implements Command {

    public LogoutCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, NoSuchRoofException, SQLException, IOException {
        request.getSession().invalidate();
        return "login.jsp";
    }
    
}
