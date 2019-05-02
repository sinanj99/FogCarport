/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.Carport;
import Data.Mappers.IRequestMapper;
import Data.Entity.PersonalInfo;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.Shed;
import Data.Entity.User;
import Logic.Controller.Manager;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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
        
        boolean inclined = Boolean.valueOf(request.getParameter("inclined"));
        int inclination = 0;
        if(inclined) inclination = Integer.parseInt(request.getParameter("inclination"));
        int cwidth = Integer.parseInt(request.getParameter("cwidth"));
        int clength = Integer.parseInt(request.getParameter("clength"));
        int rchoice = Integer.parseInt(request.getParameter("rchoice"));
        Roof roof = Manager.getRoof(rchoice);
        System.out.println("ROOF = " + roof.getRoof_id() + " " + roof.getName());
        String schoice = request.getParameter("schoice");
        boolean bshed = false;
        if (schoice.equals("1")) bshed = true;
        
        Shed shed = null;
        
        if(bshed==true) {
        int slength = Integer.parseInt(request.getParameter("slength"));
        int swidth = Integer.parseInt(request.getParameter("swidth"));
        shed = new Shed(swidth, slength);
        }
        
        Carport cp = new Carport(roof, inclined, cwidth, clength, bshed, shed); //hvorfor har carport boolean shed?
        if(inclined) cp = new Carport(roof, inclined, cwidth, clength, bshed, shed);
        
        String fname = request.getParameter("fname");
        System.out.println("FNAME = " + fname);
        String lname = request.getParameter("lname");
        String address = request.getParameter("address");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        
        
        User user = (User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        PersonalInfo info = new PersonalInfo(fname, lname, address, zip, city, "m");
        System.out.println("PERSONALINFO = " + info);
        
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datePlaced = String.valueOf(date.format(dateFormat));
        
        Request req = new Request(info, user_id, datePlaced, cp);
        
        System.out.println("FIRSTNAME ======" + req.getInfo().getFirstname());
        Manager.insertRequest(req);
        
        System.out.println("==============================================");
        System.out.println("CWIDTH=" + cwidth);
        System.out.println("CLENGTH=" + clength);
        System.out.println("ROOFTYPE=" + roof);
        System.out.println("INCLINEDCHOICE=" + inclined);
        System.out.println("INCLINATION=" + inclination);
        System.out.println("SHEDCHOICE=" + bshed);
        System.out.println("SHED=" + shed);
        System.out.println("FN=" + fname);
        System.out.println("LN=" + lname);
        System.out.println("ADDRESS=" + address);
        System.out.println("ZIPCODE=" + zip);
        System.out.println("CITY=" + city);
        System.out.println("EMAIL=" + email);
        System.out.println("==============================================");        
        
        return "reqsent.jsp";
    }
}
