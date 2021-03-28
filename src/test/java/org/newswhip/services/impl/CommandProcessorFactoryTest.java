package org.newswhip.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.newswhip.exceptions.InvalidCommandException;
import org.newswhip.services.CommandProcessor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandProcessorFactoryTest {

    @Test
    @DisplayName("getCommandProcessor should throw IllegalCommandException if command is null")
    public void getCommandProcessorShouldThrowIllegalCommandExceptionIfCommandNull() {
        String commandWithArguments = null;
        assertThrows(InvalidCommandException.class,
                () ->CommandProcessorFactory.getInstance().getCommandProcessor(commandWithArguments));
    }

    @Test
    @DisplayName("getCommandProcessor should throw IllegalCommandException if command is empty")
    public void getCommandProcessorShouldThrowIllegalCommandExceptionIfCommandEmpty() {
        String commandWithArguments = " ";
        assertThrows(InvalidCommandException.class,
                () ->CommandProcessorFactory.getInstance().getCommandProcessor(commandWithArguments));
    }

    @Test
    @DisplayName("getCommandProcessor should return AddCommandProcessor for ADD command")
    public void getCommandProcessorShouldReturnAddCommandProcessorForAddCommand() throws InvalidCommandException {
        String commandWithArguments = "ADD http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20";
        CommandProcessor commandProcessor
                = CommandProcessorFactory.getInstance().getCommandProcessor(commandWithArguments);
        assertEquals(AddCommandProcessor.class, commandProcessor.getClass());
    }

    @Test
    @DisplayName("getCommandProcessor should return RemoveCommandProcessor for REMOVE command")
    public void getCommandProcessorShouldReturnRemoveCommandProcessorForRemoveCommand() throws InvalidCommandException {
        String commandWithArguments = "REMOVE http://www.rte.ie/news/politics/2018/1004/1001034-cso/";
        CommandProcessor commandProcessor
                = CommandProcessorFactory.getInstance().getCommandProcessor(commandWithArguments);
        assertEquals(RemoveCommandProcessor.class, commandProcessor.getClass());
    }

    @Test
    @DisplayName("getCommandProcessor should return ExportCommandProcessor for EXPORT command")
    public void getCommandProcessorShouldReturnExportCommandProcessorForEXPORTCommand() throws InvalidCommandException {
        String commandWithArguments = "EXPORT";
        CommandProcessor commandProcessor
                = CommandProcessorFactory.getInstance().getCommandProcessor(commandWithArguments);
        assertEquals(ExportCommandProcessor.class, commandProcessor.getClass());
    }

    @Test
    @DisplayName("getCommandProcessor should return QuitCommandProcessor for QUIT command")
    public void getCommandProcessorShouldReturnQuitCommandProcessorForQUITCommand() throws InvalidCommandException {
        String commandWithArguments = "QUIT";
        CommandProcessor commandProcessor
                = CommandProcessorFactory.getInstance().getCommandProcessor(commandWithArguments);
        assertEquals(QuitCommandProcessor.class, commandProcessor.getClass());
    }

    @Test
    @DisplayName("getCommandProcessor should throw IllegalCommandException if command is invalid")
    public void getCommandProcessorShouldThrowIllegalCommandExceptionIfCommandInvalid() {
        String commandWithArguments = "ADD1 http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20";
        assertThrows(InvalidCommandException.class,
                () -> CommandProcessorFactory.getInstance().getCommandProcessor(commandWithArguments));
    }
}
