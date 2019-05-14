/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.PrebuiltCarport;
import Data.Entity.ShoppingCart;
import Logic.Controller.Facade;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchPrebuiltCarportException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.UserNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
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
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, ServletException, UserNotFoundException, NoSuchRoofException, SQLException, IOException
    {
        if(request.getSession().getAttribute("shoppingcart") == null) 
        {
            System.out.println("test er vi her?");
            request.getSession().setAttribute("shoppingcart", new ShoppingCart() );
        }
        
        ArrayList<PrebuiltCarport> prebuiltCarports = null;
        
        try
        {
            prebuiltCarports = (ArrayList<PrebuiltCarport>) Facade.getAllPrebuiltCarports();
        }
        catch (NoSuchPrebuiltCarportException ex)
        {
            Logger.getLogger(ShopCommand.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("Error", "Something went wrong");
            return "errorpage.jsp";
        }
        
        for(int i = 1; i <= prebuiltCarports.size(); i++)
        {
            System.out.println(i);
            if(request.getParameter(Integer.toString(i)) != null)
            {
                System.out.println(prebuiltCarports.get(i-1));
                ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("shoppingcart");
                System.out.println(cart.getPrebuiltCarports().size());
                cart.getPrebuiltCarports().add(prebuiltCarports.get(i-1));
                System.out.println(cart.getPrebuiltCarports());
                break;
            }
        }
        
        return "shoppingCart.jsp";
    }
    
}
