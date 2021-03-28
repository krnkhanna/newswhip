package org.newswhip.boot;

import org.newswhip.exceptions.InvalidCommandException;
import org.newswhip.exceptions.InvalidSyntaxException;
import org.newswhip.services.CommandProcessor;
import org.newswhip.services.impl.CommandProcessorFactory;

import java.util.Scanner;

public class ApplicationStartup {

    public static void main(String[] args) {
        boolean isProgramToContinue = true;
        Scanner in = new Scanner(System.in);
        do {
            String commandWithArguments = in.nextLine();
            try {
                CommandProcessor commandProcessor = CommandProcessorFactory.getInstance().
                        getCommandProcessor(commandWithArguments);
                commandProcessor.processCommand(commandWithArguments);
                isProgramToContinue = commandProcessor.isProgramToContinue();
            } catch (InvalidCommandException| InvalidSyntaxException e) {
                System.out.println(e.getMessage());
            }
        }
        while (isProgramToContinue);
    }
}
