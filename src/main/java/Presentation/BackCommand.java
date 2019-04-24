/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Logic.Manager;
import Logic.NoSuchRoofException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sinanjasar
 */
public class BackCommand implements Command {

    public BackCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, Manager manager) throws NoSuchRoofException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
