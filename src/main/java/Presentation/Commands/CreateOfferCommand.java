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
        Request r = Manager.getRequest(Integer.parseInt(request.getParameter("requestID")));
        BOM bom;
        CalculateBOM b = new CalculateBOM();
        if(r.getCarport().getInclination() == 0) {
            bom = b.inclineRoofBOM(r);
        } else if(r.getCarport().getInclination() != 0 && r.getCarport().getShed_() != null){
            bom = b.generateFlatRoofCarportBOM(r);
        } else {
            bom = b.generateFlatRoofWihtToolShedBOM(r);
        }
        request.setAttribute("bom", bom);
        return "create_offer.jsp";
    }
    
}
