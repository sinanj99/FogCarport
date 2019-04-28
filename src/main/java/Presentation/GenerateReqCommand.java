/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Data.Carport;
import Data.IRequestMapper;
import Data.PersonalInfo;
import Data.Request;
import Data.Shed;
import Data.User;
import Logic.Manager;
import Logic.NoSuchRoofException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class GenerateReqCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, NoSuchRoofException, SQLException, IOException {
        int cwidth = Integer.parseInt(request.getParameter("cwidth"));
        int clength = Integer.parseInt(request.getParameter("clength"));
        String rchoice = request.getParameter("rchoice");
        //receive roof from room mapper with name as parameter.
        int schoice = Integer.parseInt(request.getParameter("schoice"));
        boolean bshed = false;
        if (schoice == 1) bshed = true;
        int slength = Integer.parseInt(request.getParameter("slength"));
        int swidth = Integer.parseInt(request.getParameter("swidth"));
        boolean inclined = Boolean.valueOf(request.getParameter("inclined"));
        
        Shed shed = new Shed(swidth, slength);
        Carport cp = new Carport(null, inclined, cwidth, clength, bshed, shed);
        
        
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String address = request.getParameter("address");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        
        User user = (User) request.getSession().getAttribute("user");
        PersonalInfo info = new PersonalInfo(fname, lname, address, zip, city, "m");
        
        int user_id = user.getId();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        String datePlaced = String.valueOf(date.format(dateFormat));
        
        Request req = new Request(info, user_id, datePlaced, cp);
        Manager.insertRequest(user_id, user_id, zip, inclined, swidth, slength, bshed, swidth, slength, datePlaced);
        return "reqsent.jsp";
    }
}
