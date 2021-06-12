package de.goldmensch.commanddispatcher.exceptions;

import java.util.Arrays;

public class CommandNotValidException extends RuntimeException{

    public CommandNotValidException(String[] args) {
        super("Commmand not valid: (args: " + Arrays.asList(args)
                + ")(name: " + args[args.length-1] + ")");
    }
}
