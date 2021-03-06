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
import Presentation.Commands.ShowRequestCommand;
import Presentation.Commands.CreateResponseCommand;
import Presentation.Commands.DeleteRequestCommand;
import Presentation.Commands.DeleteResponseCommand;
import Presentation.Commands.LogoutCommand;
import Presentation.Commands.RegisterCommand;
import Presentation.Commands.FlatRoofFormCommand;
import Presentation.Commands.FrontpageRedirectCommand;
import Presentation.Commands.InclinedRoofFormCommand;
import Presentation.Commands.PrebuiltCarportCommand;
import Presentation.Commands.ShopCommand;
import Presentation.Commands.ShowPricesCommand;
import Presentation.Commands.InsertResponseCommand;
import Presentation.Commands.ShowRequestsCommand;
import Presentation.Commands.ShowResponseCommand;
import Presentation.Commands.ShowResponsesCommand;
import Presentation.Commands.UpdateResponsePriceCommand;
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
        commands.put("frontpageredirect", new FrontpageRedirectCommand());
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("flatroof", new FlatRoofFormCommand());
        commands.put("inclinedroof", new InclinedRoofFormCommand());
        commands.put("register", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("request", new GenerateReqCommand());
        commands.put("deleterequest", new DeleteRequestCommand());
        commands.put("showrequests", new ShowRequestsCommand());
        commands.put("showrequest", new ShowRequestCommand());
        commands.put("changeprice", new ChangePriceCommand());
        commands.put("showprices", new ShowPricesCommand());
        commands.put("prebuiltcarport", new PrebuiltCarportCommand());
        commands.put("shop", new ShopCommand());
        commands.put("createresponse", new CreateResponseCommand());
        commands.put("updateresponseprice", new UpdateResponsePriceCommand());
        commands.put("insertresponse", new InsertResponseCommand());
        commands.put("showresponses", new ShowResponsesCommand());
        commands.put("showresponse", new ShowResponseCommand());
        commands.put("deleteresponse", new DeleteResponseCommand());
    }

    static Command from(String key) {
        if(instance == null) instance = new CommandFactory();
        return instance.commands.getOrDefault(key, new BackCommand());
    }

}
