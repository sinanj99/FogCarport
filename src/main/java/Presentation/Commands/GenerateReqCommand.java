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
import Data.Entity.ShippingAddress;
import Data.Entity.User;
import Logic.Controller.LogicFacade;
import Presentation.Exceptions.InvalidInputException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class GenerateReqCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws UserNotFoundException, NoSuchRoofException, InvalidInputException {

        String cmd = request.getParameter("command");
        
        if (request.getSession().getAttribute("user") == null) {
            return "jsp/login.jsp";
        }
        String inclined = (String) request.getSession().getAttribute("inclined");
        int inclination = 0;
        if (inclined.equals("true")) {
            try{
                inclination = Integer.parseInt(request.getParameter("inclination"));
            }catch(NumberFormatException e){
                throw new InvalidInputException("FrontController?command="+cmd, "Hældningen må kun indeholde tal");
            }
        }
        
        int cwidth, clength, rchoice = 0;
        
        try{
            cwidth = Integer.parseInt(request.getParameter("cwidth"));
            clength = Integer.parseInt(request.getParameter("clength"));
            rchoice = Integer.parseInt(request.getParameter("rchoice"));
        }catch(NumberFormatException e){
            throw new InvalidInputException("FrontController?command="+cmd, "Målene på carporten må kun indeholde tal!");
        }
        
        Roof roof = LogicFacade.getInstance().getRoof(rchoice);
        String schoice = request.getParameter("schoice");
        boolean bshed = false;
        if (schoice.equals("1")) {
            bshed = true;
        }
        
        Shed shed = null;
        int slength, swidth = 0;
        
        if (bshed == true) {
            try{
                slength = Integer.parseInt(request.getParameter("slength"));
                swidth = Integer.parseInt(request.getParameter("swidth"));
            }catch(NumberFormatException e){
                throw new InvalidInputException("FrontController?command="+cmd, "Målene på redskabsrummet må kun indeholde tal");
            }
            shed = new Shed(swidth, slength);
        }

        Carport cp = new Carport(roof, inclination, cwidth, clength, shed);
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        int zip = Integer.parseInt(request.getParameter("zip"));
        
        if(!Pattern.matches("[a-zA-z]", fname)) throw new InvalidInputException("FrontController?command=" + cmd, "Fornavnet må kun indeholde bogstaver!");
        if(!Pattern.matches("[a-zA-z]", lname)) throw new InvalidInputException("FrontController?command=" + cmd, "Efternavnet må kun indeholde bogstaver!");
        if(!Pattern.matches("[a-zA-z0-9]", address)) throw new InvalidInputException("FrontController?command=" + cmd, "Adressen må kun indeholde tal og bogstaver!");
        if(!Pattern.matches("[a-zA-z]", city)) throw new InvalidInputException("FrontController?command=" + cmd, "By må kun indeholde bogstaver!");
        if(!Pattern.matches("[0-9]{4}", city)) throw new InvalidInputException("FrontController?command=" + cmd, "Postnummeret skal være et 4 tegn langt tal!");
        

        User user = (User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        ShippingAddress sAddress = new ShippingAddress(fname, lname, address, zip, city);

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datePlaced = String.valueOf(date.format(dateFormat));

        Request req = new Request(sAddress, user_id, datePlaced, cp);
        System.out.println(req.getCarport().getInclination());

        LogicFacade.getInstance().insertRequest(req);
        request.getSession().removeAttribute("inclined");
        return "jsp/reqsent.jsp";
    }
}
