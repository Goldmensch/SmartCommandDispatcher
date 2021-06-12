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

    public void setName(String name) {
        this.name = name;
    }

    public ExecutorLevel getExecutorLevel() {
        return executorLevel;
    }

    public String getPermission() {
        return permission;
    }

    public String getName() {
        return name;
    }

    public boolean hasPermission() {
        return !permission.equals("");
    }
}
