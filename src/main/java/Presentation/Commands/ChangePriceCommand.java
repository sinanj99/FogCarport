/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.InvalidInputException;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class ChangePriceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws
            UserNotFoundException, NoSuchRoofException, InvalidInputException, SystemErrorException,
            NumberFormatException, NoSuchMaterialException {
        int id, price = 0;
        String choice = request.getParameter("where");
        try {
            price = Integer.parseInt(request.getParameter("price"));
        } catch (NumberFormatException e) {
            throw new InvalidInputException("FrontController?command=show_prices", "Indtast venligst et tal!");
        }
        if (price == 0) {
            throw new InvalidInputException("FrontController?command=show_prices", "Ingen gratis materialer!");
        } else if (!Pattern.matches("[0-9]{1,4}", String.valueOf(price))) {
            throw new InvalidInputException("FrontController?command=show_prices", "Ugyldig pris!");
        }
        try{
        id = Integer.parseInt(request.getParameter("id"));
        } catch(NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new InvalidInputException("FrontController?command=show_prices", "Indtast venligst et tal!");
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
        } catch(NoSuchMaterialException e) {
            System.out.println(e.getMessage());
            throw new NoSuchMaterialException("FrontController?command=show_prices", e.getId());
        }
        return "jsp/frontpage.jsp";
    }

}
