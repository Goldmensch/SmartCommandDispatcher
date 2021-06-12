package de.goldmensch.commanddispatcher.command;

import de.goldmensch.commanddispatcher.ExecutorLevel;
import de.goldmensch.commanddispatcher.exceptions.CommandNotValidException;
import de.goldmensch.commanddispatcher.subcommand.SmartSubCommand;
import de.goldmensch.commanddispatcher.util.ArraySetUtils;
import de.goldmensch.commanddispatcher.util.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class SmartCommand implements TabExecutor {
    private final Map<String[], SmartSubCommand> subCommandMap = new HashMap<>();

    public void registerSubCommand(SmartSubCommand command, String... args) {
        args = ArrayUtils.toLowerCase(args);
        if(isValid(args)) {
            command.setName(ArrayUtils.buildString(args));
            subCommandMap.put(args, command);
        }else {
            throw new CommandNotValidException(args);
        }
    }

    private boolean isValid(String[] args) {
       return (args.length != 0)
               && (!subCommandMap.containsKey(args));
    }

    public Optional<ArgValuedSubCommand> searchSub(String[] args) {
        Set<String[]> possibleSubCommand = new HashSet<>();
        for (String[] c : subCommandMap.keySet()) {
            if(ArrayUtils.startWith(args, c)) {
                possibleSubCommand.add(c);
            }
        }

        return possibleSubCommand.size() != 0
                ? Optional.of(getValuedCommand(args, possibleSubCommand))
                : Optional.empty();
    }

    private ArgValuedSubCommand getValuedCommand(String[] args, Set<String[]> possibleCommands) {
        String[] matchArgs = ArraySetUtils.getBiggest(possibleCommands);
        SmartSubCommand matchCommand = subCommandMap.get(matchArgs);
        String[] newArgs = Arrays.copyOfRange(args, matchArgs.length, args.length);

        return new ArgValuedSubCommand(matchCommand, newArgs);
    }

    public abstract boolean noSubFound(String[] args, CommandSender sender, Command command, String label);

    public abstract void wrongExecutorLevel(ArgValuedSubCommand command, CommandSender sender);

    public abstract void noPermission(ArgValuedSubCommand command, CommandSender sender);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Optional<ArgValuedSubCommand> optional = searchSub(args);
        if(optional.isEmpty()) {
            return noSubFound(args, sender, command, label);
        }

        ArgValuedSubCommand foundCommand = optional.get();
        if(checkLevel(sender, foundCommand)) {
            wrongExecutorLevel(foundCommand, sender);
            return true;
        }

        if(checkPermission(sender, foundCommand)) {
            noPermission(foundCommand, sender);
            return true;
        }

        return foundCommand.getCommand().onCommand(sender, command, label, foundCommand.getArgs());
    }

    private boolean checkLevel(CommandSender sender, ArgValuedSubCommand command) {
        ExecutorLevel level = command.getCommand().getExecutorLevel();
        if(level != ExecutorLevel.CONSOLE_PLAYER) {
            return ExecutorLevel.getFromSender(sender) != level;
        }
        return false;
    }

    private boolean checkPermission(CommandSender sender, ArgValuedSubCommand command) {
        SmartSubCommand subCommand = command.getCommand();
        if(subCommand.hasPermission()) {
            return !sender.hasPermission(subCommand.getPermission());
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] a) {
        List<String> completion = new ArrayList<>();

        String[] args = ArrayUtils.toLowerCase(a);
        String[] argPath = Arrays.copyOf(args, args.length-1);

        for(Map.Entry<String[], SmartSubCommand> c : subCommandMap.entrySet()) {
            int argLength = args.length-1;
            String[] comArgs = c.getKey();

            if(checkPermissionAndExecutor(sender, c.getValue()) || (comArgs.length < args.length)) continue;
            String[] comPath = Arrays.copyOf(comArgs, argLength);
            if(Arrays.equals(comPath, argPath)) {
                String comArg = comArgs[argLength];
                String arg = args[argLength];
                if(comArg.startsWith(arg)) completion.add(comArg);
            }
        }

        Optional<ArgValuedSubCommand> foundCommand = searchSub(args);
        if(foundCommand.isPresent()) {
            SmartSubCommand subCommand = foundCommand.get().getCommand();
            if(!checkPermissionAndExecutor(sender, subCommand)) {
                List<String> commandCompletion = subCommand.onTabComplete(sender, command, alias, foundCommand.get().getArgs());
                if(commandCompletion != null) completion.addAll(commandCompletion);
            }
        }

        return completion;
    }

    private boolean checkPermissionAndExecutor(CommandSender sender, SmartSubCommand command) {
        ExecutorLevel level = command.getExecutorLevel();
        if(level != ExecutorLevel.CONSOLE_PLAYER) {
            if(level != ExecutorLevel.getFromSender(sender)) return true;
        }
        return command.hasPermission() && !sender.hasPermission(command.getPermission());
    }
}

