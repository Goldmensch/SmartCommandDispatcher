package de.goldmensch.commanddispatcher.exceptions;

import de.goldmensch.commanddispatcher.subcommand.SmartSubCommand;

public class CommandNotValidException extends RuntimeException{

    public CommandNotValidException(Class<? extends SmartSubCommand> sub) {
        super("Commmand not valid, clas: " + sub.getName());
    }
}
