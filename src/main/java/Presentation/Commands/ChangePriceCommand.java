/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.SystemErrorException;
import Logic.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class ChangePriceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException, SQLException, IOException {
        int id, price;
        try {
            price = Integer.parseInt(request.getParameter("price"));
        } catch (NumberFormatException e) {
            request.setAttribute("priceError", "Indtast venligst et postivt heltal");
            return "change_price.jsp";

        }
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            request.setAttribute("idError", "Indtast venligst et postivt heltal");
            return "change_price.jsp";
        }
        
        try {
            PresentationFacade.getInstance().updatePrices(price, id);
        } catch (NoSuchMaterialException ex) {
            request.setAttribute("error", ex.getMessage());
            return "change_price.jsp";
        } catch (SystemErrorException ex) {
            request.setAttribute("error", ex.getMessage());
            return "error.jsp";
        }
        
        request.setAttribute("status", "succes");
        return "change_price.jsp";
    }

}
