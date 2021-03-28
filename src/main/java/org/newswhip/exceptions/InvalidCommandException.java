package org.newswhip.exceptions;

public class InvalidCommandException extends Exception {

    private final String command;

    private final static String MESSAGE =
            "'%s' is not a valid command, Please use ReadMe to see the correct commands.";

    public InvalidCommandException(String command) {
        super(String.format(MESSAGE, command.toUpperCase()));
        this.command = command.toUpperCase();
    }
}
