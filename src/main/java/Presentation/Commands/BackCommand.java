/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Commands;

import Data.Entity.User;
import Presentation.Exceptions.NoSuchRoofException;
import javax.servlet.http.HttpServletRequest;

/**
 * Command, that sends user back to front page if a command value of the specified string key
 * cannot be found.
 * @author sinanjasar
 */
public class BackCommand implements Command {

    public BackCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) throws NoSuchRoofException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.isAdmin() == true) {
            return "jsp/adminfrontpage.jsp";
        } else if(user.isSeller() == true) {
            return "jsp/sellerfrontpage.jsp";
        } else {
            return "jsp/frontpage.jsp";
        }
    }
}
