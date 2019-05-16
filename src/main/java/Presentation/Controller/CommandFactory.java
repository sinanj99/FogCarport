/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Controller;

import Presentation.Commands.BackCommand;
import Presentation.Commands.ChangePriceCommand;
import Presentation.Commands.GenerateReqCommand;
import Presentation.Commands.LoginCommand;
import Presentation.Commands.Command;
import Presentation.Commands.CreateOfferCommand;
import Presentation.Commands.LogoutCommand;
import Presentation.Commands.RegisterCommand;
import Presentation.Commands.FlatRoofFormCommand;
import Presentation.Commands.InclinedRoofFormCommand;
import Presentation.Commands.PrebuiltCarportCommand;
import Presentation.Commands.ShopCommand;
import Presentation.Commands.ShowPricesCommand;
//import Presentation.Commands.PrebuiltCarportCommand;
//import Presentation.Commands.ShopCommand;
import Presentation.Commands.ShowRequestCommand;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sinanjasar
 */
public class CommandFactory {

    private final Map<String, Command> commands = new HashMap();
    private static CommandFactory instance = null;

    private CommandFactory() {
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("flatroof", new FlatRoofFormCommand());
        commands.put("inclinedroof", new InclinedRoofFormCommand());
        commands.put("register", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("request", new GenerateReqCommand());
        commands.put("showrequests", new ShowRequestCommand());
        commands.put("createOffer", new CreateOfferCommand());
        commands.put("change_price", new ChangePriceCommand());
        commands.put("show_prices", new ShowPricesCommand());
        commands.put("prebuiltCarport", new PrebuiltCarportCommand());
        commands.put("shop", new ShopCommand());
    }

    static Command from(String key) {
        if(instance == null) instance = new CommandFactory();
        return instance.commands.getOrDefault(key, new BackCommand());
    }

}
