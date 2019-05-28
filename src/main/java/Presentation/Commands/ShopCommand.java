/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.PrebuiltCarport;
import Data.Entity.ShoppingCart;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchPrebuiltCarportException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.UserNotFoundException;
import Presentation.Controller.PresentationFacade;
import Presentation.Exceptions.SystemErrorException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 * Recieves request from prebuiltcarport.jsp
 * Created a session containing ShoppingCart entity class
 * Forward to shoppingcart.jsp
 * @author Kasper Jeppesen
 */
public class ShopCommand implements Command
{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException
    {
        if(request.getSession().getAttribute("shoppingcart") == null) 
        {
            request.getSession().setAttribute("shoppingcart", new ShoppingCart() );
        }
        
        ArrayList<PrebuiltCarport> prebuiltCarports = null;
        
        try
        {
            prebuiltCarports = (ArrayList<PrebuiltCarport>) PresentationFacade.getInstance().getAllPrebuiltCarports();
        }
        catch (NoSuchPrebuiltCarportException ex)
        {
            request.setAttribute("Error", "Something went wrong");
            return "jsp/error.jsp";
        }
        
        for(int i = 1; i <= prebuiltCarports.size(); i++)
        {
            if(request.getParameter(Integer.toString(i)) != null)
            {
                ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("shoppingcart");
                cart.getPrebuiltCarports().add(prebuiltCarports.get(i-1));
                break;
            }
        }
        
        return "jsp/shoppingcart.jsp";
    }
    
}
