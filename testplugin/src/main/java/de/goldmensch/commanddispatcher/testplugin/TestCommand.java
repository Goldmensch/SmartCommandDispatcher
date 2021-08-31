package de.goldmensch.commanddispatcher.testplugin;

import commanddispatcher.Commands;
import commanddispatcher.Executor;
import commanddispatcher.subcommand.SmartSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class TestCommand extends SmartSubCommand {

    public TestCommand() {
        super(Executor.PLAYER, "test");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("works!");
        sender.sendMessage(Arrays.asList(args).toString());
        return false;
    }
}
