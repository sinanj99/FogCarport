/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

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
    }

    static Command from(String key) {
        if(instance == null) instance = new CommandFactory();
        return instance.commands.getOrDefault(key, new BackCommand());
    }

}
