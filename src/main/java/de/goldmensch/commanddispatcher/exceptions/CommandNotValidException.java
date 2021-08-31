package de.goldmensch.commanddispatcher.exceptions;

import de.goldmensch.commanddispatcher.subcommand.SmartSubCommand;
import org.jetbrains.annotations.NotNull;

public class CommandNotValidException extends RuntimeException{

    public CommandNotValidException(@NotNull Class<? extends SmartSubCommand> sub) {
        super("Commmand not valid, class: " + sub.getName());
    }
}
