package de.goldmensch.commanddispatcher;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum ExecutorLevel {
    CONSOLE,
    PLAYER,
    CONSOLE_PLAYER;

    public static ExecutorLevel getFromSender(CommandSender sender) {
        if(sender instanceof Player) {
            return ExecutorLevel.PLAYER;
        }else {
            return ExecutorLevel.CONSOLE;
        }
    }
}
