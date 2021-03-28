package org.newswhip.services;

import org.newswhip.exceptions.InvalidSyntaxException;

public interface CommandProcessor {

    /**
     * Process the command.
     *
     * @param commandWithArguments the command with the arguments.
     * @throws InvalidSyntaxException if the syntax of the command is not correct.
     */
    void processCommand(String commandWithArguments) throws InvalidSyntaxException;

    /**
     * Returns if the program should continue.
     *
     * @return true if the program should continue else false.
     */
    boolean isProgramToContinue();

}
