package de.goldmensch.commanddispatcher;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum ExecutorLevel {
    /***
     * The command can be executed only from the console.
     */
    CONSOLE,
    /***
     * The command can be executed only from a player.
     */
    PLAYER,
    /***
     * The command can be executed from a player or the console.
     */
    CONSOLE_PLAYER;

    /***
     * Gives you the ExecutorLevel belonging to the CommandSender.
     * @param sender The CommandSender
     * @return The ExecutorLevel that corresponds to the CommandSender.
     */
    public static ExecutorLevel getFromSender(CommandSender sender) {
        if(sender instanceof Player) {
            return ExecutorLevel.PLAYER;
        }else {
            return ExecutorLevel.CONSOLE;
        }
    }
}
