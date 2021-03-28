package org.newswhip.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.newswhip.exceptions.InvalidSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class QuitCommandProcessorTest {

    private QuitCommandProcessor quitCommandProcessor;

    @BeforeEach
    public void setUp() {
        quitCommandProcessor = new QuitCommandProcessor();
    }

    @Test
    @DisplayName("processCommand should throw InvalidSyntaxException if the number of arguments is not 1")
    public void processCommandShouldThrowInvalidSyntaxExceptionIfNumberOfArgumentsNot2() {
        String commandWithArguments = "QUIT abc";
        assertThrows(InvalidSyntaxException.class, () -> quitCommandProcessor.processCommand(commandWithArguments));
    }

    @Test
    @DisplayName("isProgramToContinue should return true")
    public void isProgramToContinueShouldReturnTrue() {
        assertFalse(quitCommandProcessor.isProgramToContinue());
    }
}
