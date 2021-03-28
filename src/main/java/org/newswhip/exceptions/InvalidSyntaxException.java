package org.newswhip.exceptions;

public class InvalidSyntaxException extends Exception {

    private final String commandWithArguments;

    private final String command;

    private final static String MESSAGE =
            "'%s' is not a valid syntax for '%s' command, Please use ReadMe to see the correct syntax.";

    public InvalidSyntaxException(String commandWithArguments, String command) {
        super(String.format(MESSAGE, commandWithArguments, command.toUpperCase()));
        this.command = command.toUpperCase();
        this.commandWithArguments = commandWithArguments;
    }
}
