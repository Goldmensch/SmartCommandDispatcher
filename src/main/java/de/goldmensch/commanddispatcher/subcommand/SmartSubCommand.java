package de.goldmensch.commanddispatcher.subcommand;

import de.goldmensch.commanddispatcher.Executor;
import org.bukkit.command.CommandExecutor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class SmartSubCommand implements CommandExecutor {

    private final Executor executor;
    private String name;
    private final String permission;

    public SmartSubCommand(@NotNull Executor executor, @Nullable String permission) {
        this.executor = executor;
        this.permission = permission;
    }

    @ApiStatus.Internal
    public void setName(@NotNull String name) {
        this.name = name;
    }

    /***
     * @return The {@link Executor} of the SubCommand
     */
    public @NotNull Executor getExecutorLevel() {
        return executor;
    }

    /***
     * @return The permission of the SubCommand
     */
    public @NotNull Optional<String> getPermission() {
        return permission.isEmpty()
                ? Optional.empty()
                : Optional.of(permission);
    }

    /***
     * <p>The name is a string consisting of the path and the name. </p>
     * <p>Example: The StringArray {"arg0", "arg1", "name"} becomes "arg0 arg1 name"</p>
     * @return The name of the SubCommand
     */
    public @NotNull String getName() {
        return name;
    }
}
