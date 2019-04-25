/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Data.Client;
import Data.PersonalInfo;
import Logic.Manager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String zip = request.getParameter("zip");
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        String pword = request.getParameter("pword");
        String pword2 = request.getParameter("pword2");
        String gender = request.getParameter("gender");
        boolean gender_ = false;
        if(gender.equals("man")) gender_ = true;
        Manager.insertClient(new Client(new PersonalInfo(fname, lname, adress, Integer.parseInt(zip), city, gender_), email, pword));
        return "frontpage.jsp";
    }
    
}
