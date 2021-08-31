package de.goldmensch.commanddispatcher.testplugin;

import commanddispatcher.command.SmartCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TestPluginCommand extends SmartCommand {

    public TestPluginCommand() {
        registerSubCommand(new TestCommand(), "test");
    }

    @Override
    public boolean noSubFound(@NotNull String[] args, @NotNull CommandSender sender, @NotNull Command command, @NotNull String label) {
        sender.sendMessage("no sub found");
        return false;
    }

    @Override
    public void wrongExecutorLevel(@NotNull SubCommandEntity command, @NotNull CommandSender sender) {
        sender.sendMessage("wrong Executor");
    }

    @Override
    public void noPermission(@NotNull SubCommandEntity command, @NotNull CommandSender sender) {
        sender.sendMessage("no permissions");
    }
}
