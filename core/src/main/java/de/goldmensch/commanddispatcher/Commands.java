package de.goldmensch.commanddispatcher;

import de.goldmensch.commanddispatcher.subcommand.SmartSubCommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class Commands {

    private Commands() {
    }

    public static boolean checkExecutor(@NotNull CommandSender sender, @NotNull Executor executor) {
        return executor == Executor.CONSOLE_PLAYER
                || executor == Executor.fromSender(sender);
    }

    // Optional ends up not being required anymore
    public static boolean checkPermissions(@NotNull CommandSender sender, @NotNull String[] posPermissions) {
        // Only require permission if the subcommand requires any permissions (duh)
        if (posPermissions.length > 0) {
            // Loop through the permissions
            for (String permission : posPermissions) {
                if (sender.hasPermission(permission)) {
                    // If the user has at least one, go ahead and break
                    return false;
                }
            }

            // Exit if the player doesn't have one+ of the required permissions
            return false;
        }
        return true;
    }

    public static boolean checkPermissionAndExecutor(@NotNull CommandSender sender, @NotNull SmartSubCommand command) {
        return Commands.checkExecutor(sender, command.getExecutor())
                && Commands.checkPermissions(sender, command.getPermissions());
    }
}
