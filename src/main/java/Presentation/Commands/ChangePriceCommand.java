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
import Presentation.Exceptions.InvalidInputException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class ChangePriceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException {
        int id, price = 0;
        String choice = request.getParameter("where");
        try {
            price = Integer.parseInt(request.getParameter("price"));
            if (price == 0) {
                throw new InvalidInputException("jsp/change_price.jsp", "Ingen gratis materialer!");
            } else if (!Pattern.matches("[0-9]{3}", String.valueOf(price))) {
                throw new InvalidInputException("jsp/change_price.jsp", "Ugyldig pris!");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            request.setAttribute("priceError", "Indtast venligst et postivt heltal");
            return "jsp/change_price.jsp";
        } catch (InvalidInputException e) {
            request.setAttribute("priceError", e.getMessage());
        }

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            request.setAttribute("idError", "Indtast venligst et postivt heltal");
            return "jsp/change_price.jsp";
        }

        try {
            switch (choice) {
                case "length":
                    PresentationFacade.getInstance().updatePrices(price, id);
                    break;
                case "nolength":
                    PresentationFacade.getInstance().updatePricesNoLength(price, id);
                    break;
                default:
                    PresentationFacade.getInstance().updatePricesRoof(price, id);
                    break;
            }
        } catch (NoSuchMaterialException ex) {
            request.setAttribute("error", ex.getMessage());
            return "jsp/change_price.jsp";
        } catch (SystemErrorException ex) {
            request.setAttribute("error", ex.getMessage());
            return "jsp/error.jsp";
        } catch(InvalidInputException ex) {
                request.setAttribute("priceError", ex.getMessage());
                return ex.getTarget();
        }
        
        request.setAttribute("status", "succes");
        return "jsp/frontpage.jsp";
    }

}
