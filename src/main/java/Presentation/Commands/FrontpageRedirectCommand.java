/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.User;
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Obaydah Mohamad
 */
public class FrontpageRedirectCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) throws NoSuchMaterialException, UserNotFoundException, NoSuchRoofException, SystemErrorException, ClientException, NoSuchShedException {
        User user = null;
        user = (User) request.getSession().getAttribute("user");
        
        if(user == null) return "jsp/login";
        if(user.isAdmin()) return "jsp/adminfrontpage.jsp";
        if(user.isSeller()) return "jsp/sellerfrontpage.jsp";
        
        
        return "jsp/frontpage.jsp";
    }
    
}
