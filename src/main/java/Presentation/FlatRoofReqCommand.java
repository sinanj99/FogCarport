/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Data.Roof;
import Logic.Manager;
import Logic.NoSuchRoofException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class FlatRoofReqCommand implements Command {

    public FlatRoofReqCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, Manager manager) throws NoSuchRoofException {
        request.setAttribute("roofs", manager.allFlatRoofs());
        return "flatroof.jsp";
    }
    
}
