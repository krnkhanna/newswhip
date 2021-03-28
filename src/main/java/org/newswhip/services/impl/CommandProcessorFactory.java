package org.newswhip.services.impl;

import org.newswhip.constants.Commands;
import org.newswhip.data.SocialScoreStore;
import org.newswhip.data.impl.SocialScoreStoreImpl;
import org.newswhip.exceptions.InvalidCommandException;
import org.newswhip.services.CommandProcessor;

public class CommandProcessorFactory {

    private static final CommandProcessorFactory COMMAND_PROCESSOR_FACTORY = new CommandProcessorFactory();

    private final CommandProcessor addCommandProcessor;

    private final CommandProcessor removeCommandProcessor;

    private final CommandProcessor exportCommandProcessor;

    private final CommandProcessor quitCommandProcessor;

    private CommandProcessorFactory() {
        SocialScoreStore socialScoreStore = new SocialScoreStoreImpl();
        this.addCommandProcessor = new AddCommandProcessor(socialScoreStore);
        this.removeCommandProcessor = new RemoveCommandProcessor(socialScoreStore);
        this.exportCommandProcessor = new ExportCommandProcessor(socialScoreStore);
        this.quitCommandProcessor = new QuitCommandProcessor();
    }

    public static CommandProcessorFactory getInstance() {
        return COMMAND_PROCESSOR_FACTORY;
    }

    /**
     * Will provide an appropriate CommandProcessor depending on the commnad used.
     *
     * @param commandWithArguments the command with the arguments
     * @return commandProcessor
     * @throws InvalidCommandException if the command is not a valid command.
     */
    public CommandProcessor getCommandProcessor(String commandWithArguments) throws InvalidCommandException {
        if (null == commandWithArguments || commandWithArguments.trim().length() <= 0) {
            throw new InvalidCommandException("");
        } else {
            String command = commandWithArguments.split(" ")[0].toUpperCase();
            if (command.equals(Commands.ADD.name())) {
                return this.addCommandProcessor;
            } else if (command.equals(Commands.REMOVE.name())) {
                return this.removeCommandProcessor;
            } else if (command.equals(Commands.EXPORT.name())) {
                return this.exportCommandProcessor;
            } else if (command.equals(Commands.QUIT.name())) {
                return this.quitCommandProcessor;
            } else {
                throw new InvalidCommandException(commandWithArguments);
            }
        }
    }
}
