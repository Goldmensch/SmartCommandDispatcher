package de.goldmensch.commanddispatcher.subcommand;

import de.goldmensch.commanddispatcher.Executor;
import org.bukkit.command.CommandExecutor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;

public abstract class SmartSubCommand implements CommandExecutor {

    private final Executor executor;
    private final String[] permissions;
    private String name;
    private String description;

    public SmartSubCommand(@NotNull Executor executor, @Nullable String... permissions) {
        this.executor = executor;
        this.permissions = permissions;
    }

    @ApiStatus.Internal
    public void setName(@NotNull String name) {
        this.name = name;
    }

    @ApiStatus.Internal
    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    /***
     * @return The {@link Executor} of the SubCommand
     */
    public @NotNull Executor getExecutor() {
        return executor;
    }

    /***
     * @return The permissions, only one of which a user must have, of the SubCommand
     */
    public @NotNull String[] getPermissions() {
        return Arrays.copyOf(permissions, permissions.length);
    }

    /***
     * <p>The name is a string consisting of the path and the name. </p>
     * <p>Example: The StringArray {"arg0", "arg1", "name"} becomes "arg0 arg1 name"</p>
     * @return The name of the SubCommand
     */
    public @NotNull String getName() {
        return name;
    }

    public @NotNull Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }
}
