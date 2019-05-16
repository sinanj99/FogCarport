/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.PersonalInfo;
import Data.Entity.User;
import Presentation.Exceptions.DuplicateException;
import Logic.Controller.LogicFacade;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.NoMatchException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class RegisterCommand implements Command {

    public RegisterCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) throws SystemErrorException, NoMatchException, DuplicateException {

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String adress = request.getParameter("adress");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        String pword = request.getParameter("pword");
        String pword2 = request.getParameter("pword2");
        String gender = request.getParameter("gender");
        if(!pword.equals(pword2)) {
            throw new NoMatchException("jsp/register.jsp", "Adgangskoderne matcher ikke!", "passwordError");
        }
        try {
            PresentationFacade.getInstance().insertUser(new User(new PersonalInfo(fname, lname, adress, zip, city, gender), email, pword));
        } catch (DuplicateException ex) {
            throw new DuplicateException("jsp/register.jsp", ex.getMessage(), "emailError");
        }

        return "jsp/frontpage.jsp";
    }

}
