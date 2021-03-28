package org.newswhip.services.impl;

import org.newswhip.exceptions.InvalidSyntaxException;
import org.newswhip.services.CommandProcessor;

public class QuitCommandProcessor implements CommandProcessor {

    protected QuitCommandProcessor() {

    }

    @Override
    public void processCommand(String commandWithArguments) throws InvalidSyntaxException {
        parseQuitCommand(commandWithArguments);
    }

    @Override
    public boolean isProgramToContinue() {
        return false;
    }

    private void parseQuitCommand(String commandWithArguments) throws InvalidSyntaxException {
        String[] command = commandWithArguments.split(" ");
        if (command.length != 1) {
            throw new InvalidSyntaxException(commandWithArguments, command[0]);
        }
    }
}
