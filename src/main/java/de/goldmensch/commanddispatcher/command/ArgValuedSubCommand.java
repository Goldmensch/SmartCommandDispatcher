package de.goldmensch.commanddispatcher.command;

import de.goldmensch.commanddispatcher.subcommand.SmartSubCommand;
import org.jetbrains.annotations.NotNull;

public class ArgValuedSubCommand {
    private final SmartSubCommand command;
    private final String[] args;

    public ArgValuedSubCommand(@NotNull SmartSubCommand command, @NotNull String[] args) {
        this.command = command;
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public SmartSubCommand getCommand() {
        return command;
    }
}
