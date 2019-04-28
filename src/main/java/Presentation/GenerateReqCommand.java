/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Data.Carport;
import Data.IMaterialMapper;
import Data.IRequestMapper;
import Data.IUserMapper;
import Data.PersonalInfo;
import Data.Request;
import Data.Roof;
import Data.Shed;
import Data.User;
import Logic.Manager;
import Logic.NoSuchRoofException;
import Logic.UserNotFoundException;
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
    public String execute(HttpServletRequest request) throws ServletException, UserNotFoundException, NoSuchRoofException, SQLException, IOException {
        
        int cwidth = Integer.parseInt(request.getParameter("cwidth"));
        int clength = Integer.parseInt(request.getParameter("clength"));
        String rchoice = request.getParameter("rchoice");
        Roof roof = Manager.getRoof(rchoice);
        
        int schoice = Integer.parseInt(request.getParameter("schoice"));
        boolean bshed = false;
        if (schoice == 1) bshed = true;
        int slength = Integer.parseInt(request.getParameter("slength"));
        int swidth = Integer.parseInt(request.getParameter("swidth"));
        boolean inclined = Boolean.valueOf(request.getParameter("inclined"));
        
        Shed shed = new Shed(swidth, slength);
        Carport cp = new Carport(roof, inclined, cwidth, clength, bshed, shed);
        
        
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
        Manager.insertRequest(req);
        
        return "reqsent.jsp";
    }
}
