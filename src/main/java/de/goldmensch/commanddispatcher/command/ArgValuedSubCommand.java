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

    /***
     * <p>The new arguments are the arguments without the path and the name of the command.</p>
     * <p>Example: subcommand path : "arg0 name" </p>
     * <p>path: "arg0 name value" becomes Arguments: "value"</p>
     * @return The new Arguments of the command.
     */
    public String[] getArgs() {
        return args;
    }

    /***
     * @return The SubCommand
     */
    public SmartSubCommand getCommand() {
        return command;
    }
}
