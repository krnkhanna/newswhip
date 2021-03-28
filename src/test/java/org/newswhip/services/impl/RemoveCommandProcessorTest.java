package org.newswhip.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.newswhip.data.SocialScoreStore;
import org.newswhip.domains.SocialScore;
import org.newswhip.exceptions.InvalidSyntaxException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RemoveCommandProcessorTest {

    private RemoveCommandProcessor removeCommandProcessor;

    @Mock
    private SocialScoreStore socialScoreStore;

    @BeforeEach
    public void setUp() {
        removeCommandProcessor = new RemoveCommandProcessor(socialScoreStore);
    }

    @Test
    @DisplayName("processCommand should parse and call remove social score")
    public void processCommandShouldParseAndCallRemoveSocialScore() throws InvalidSyntaxException {
        String commandWithArguments = "REMOVE http://www.rte.ie/news/politics/2018/1004/1001034-cso/";
        removeCommandProcessor.processCommand(commandWithArguments);
        verify(socialScoreStore, times(1)).removeSocialScore(any(SocialScore.class));
    }

    @Test
    @DisplayName("processCommand should throw InvalidSyntaxException if the number of arguments is not 2")
    public void processCommandShouldThrowInvalidSyntaxExceptionIfNumberOfArgumentsNot2() {
        String commandWithArguments = "REMOVE http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 23";
        assertThrows(InvalidSyntaxException.class, () -> removeCommandProcessor.processCommand(commandWithArguments));
    }

    @Test
    @DisplayName("processCommand should throw InvalidSyntaxException if the url in the argument is not correct")
    public void processCommandShouldThrowInvalidSyntaxExceptionIfUrlInArgumentsIsNotCorrect() {
        String commandWithArguments = "REMOVE www.rte.ie/news/politics/2018/1004/1001034-cso/";
        assertThrows(InvalidSyntaxException.class, () -> removeCommandProcessor.processCommand(commandWithArguments));
    }

    @Test
    @DisplayName("isProgramToContinue should return true")
    public void isProgramToContinueShouldReturnTrue() {
        assertTrue(removeCommandProcessor.isProgramToContinue());
    }

}
