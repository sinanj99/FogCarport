/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.PersonalInfo;
import Data.Entity.User;
import Logic.Exceptions.DuplicateException;
import Logic.Controller.Facade;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class RegisterCommand implements Command {

    public RegisterCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException {

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String adress = request.getParameter("adress");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        String pword = request.getParameter("pword");
        String pword2 = request.getParameter("pword2");
        String gender = request.getParameter("gender");
        try {
            Facade.insertUser(new User(new PersonalInfo(fname, lname, adress, zip, city, gender), email, pword));
        } catch (DuplicateException e) {
            request.setAttribute("registerResult", e.getMessage());
            return "register.jsp";
        }
        return "frontpage.jsp";
    }

}
