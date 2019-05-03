/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.BOM;
import Data.Entity.Request;
import Logic.Calculator.CalculateBOM;
import Logic.Controller.Manager;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRequestException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class CreateOfferCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException, SQLException, IOException {
        System.out.println("test");
        Request r = Manager.getRequest(Integer.parseInt(request.getParameter("requestID")));
        System.out.println("test2");
        BOM bom = null;
        System.out.println("test3");
        CalculateBOM b = new CalculateBOM();
        System.out.println("test4");
        System.out.println(r.getCarport().getInclination());
        System.out.println(b.inclineRoofBOM(r).getLineitems().size());
        
        
        //den kan ikke finde inclination
        if(r.getCarport().getInclination() != 0) {
            bom = b.inclineRoofBOM(r);
            System.out.println(b.inclineRoofBOM(r).getLineitems().size());
        } else {
            bom = b.generateFlatRoofCarportBOM(r);
        }
        request.setAttribute("bom", bom);
        return "create_offer.jsp";
    }
    
}
