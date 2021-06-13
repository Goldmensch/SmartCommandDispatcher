package de.goldmensch.commanddispatcher.subcommand;

import de.goldmensch.commanddispatcher.ExecutorLevel;
import org.bukkit.command.TabExecutor;

public abstract class SmartSubCommand implements TabExecutor {

    private final ExecutorLevel executorLevel;
    private String name;
    private final String permission;

    public SmartSubCommand(ExecutorLevel executorLevel, String permission) {
        this.executorLevel = executorLevel;
        this.permission = permission;
    }

    /***
     * @param name The Name of the SubCommand
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * @return The {@link ExecutorLevel} of the SubCommand
     */
    public ExecutorLevel getExecutorLevel() {
        return executorLevel;
    }

    /***
     * @return The permission of the SubCommand
     */
    public String getPermission() {
        return permission;
    }

    /***
     * <p>The name is a string consisting of the path and the name. </p>
     * <p>Example: The StringArray {"arg0", "arg1", "name"} becomes "arg0 arg1 name"</p>
     * @return The name of the SubCommand
     */
    public String getName() {
        return name;
    }

    /***
     * @return true if the command has a permission
     */
    public boolean hasPermission() {
        return !permission.equals("");
    }
}
