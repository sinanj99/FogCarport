/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.Material;
import Data.Entity.Roof;
import Data.Entity.User;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Controller.PresentationFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Command that fetches all available roofs and materials 
 * so they can be displayed on jsp page
 * @author sinanjasar
 */
public class ShowPricesCommand implements Command {
    
    @Override
    public String execute(HttpServletRequest request) throws NoSuchRoofException, SystemErrorException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return "jsp/login.jsp";
        if(!user.isAdmin()) return "FrontController?command=frontpageredirect";
        
        List<Roof> roofs = PresentationFacade.getInstance().getRoofsSorted();
        List<Material> materials = PresentationFacade.getInstance().getMaterials();
//        for (int i = 1; i < materials.size(); i++) {
//            if (materials.get(i).getName().equals(materials.get(i - 1).getName())) {
//                materials.remove(i);
//                i-=1;
//            }
//        }
        request.setAttribute("materials", materials);
        request.setAttribute("roofs", roofs);
        System.out.println(roofs.get(0).getPrice());
        return "jsp/change_price.jsp";
    }

}
