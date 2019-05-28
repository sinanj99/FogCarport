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
import Presentation.Exceptions.ClientException;
import Presentation.Exceptions.NoSuchShedException;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author sinanjasar
 */
public interface Command {
    
    String execute(HttpServletRequest request) throws NoSuchMaterialException, 
            UserNotFoundException, NoSuchRoofException, SystemErrorException, ClientException, NoSuchShedException;
}
