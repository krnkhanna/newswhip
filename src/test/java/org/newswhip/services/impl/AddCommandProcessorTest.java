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
public class AddCommandProcessorTest {

    private AddCommandProcessor addCommandProcessor;

    @Mock
    private SocialScoreStore socialScoreStore;

    @BeforeEach
    public void setUp() {
        addCommandProcessor = new AddCommandProcessor(socialScoreStore);
    }

    @Test
    @DisplayName("processCommand should parse and call add social score")
    public void processCommandShouldParseAndCallAddSocialScore() throws InvalidSyntaxException {
        String commandWithArguments = "ADD http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20";
        addCommandProcessor.processCommand(commandWithArguments);
        verify(socialScoreStore, times(1)).addSocialScore(any(SocialScore.class));
    }

    @Test
    @DisplayName("processCommand should throw InvalidSyntaxException if the number of arguments is not 3")
    public void processCommandShouldThrowInvalidSyntaxExceptionIfNumberOfArgumentsNot3() {
        String commandWithArguments = "ADD http://www.rte.ie/news/politics/2018/1004/1001034-cso/";
        assertThrows(InvalidSyntaxException.class, () -> addCommandProcessor.processCommand(commandWithArguments));
    }

    @Test
    @DisplayName("processCommand should throw InvalidSyntaxException if the order of arguments is not correct")
    public void processCommandShouldThrowInvalidSyntaxExceptionIfOrderOfArgumentsIsNotCorrect() {
        String commandWithArguments = "ADD 20 http://www.rte.ie/news/politics/2018/1004/1001034-cso/";
        assertThrows(InvalidSyntaxException.class, () -> addCommandProcessor.processCommand(commandWithArguments));
    }

    @Test
    @DisplayName("processCommand should throw InvalidSyntaxException if the url in the argument is not correct")
    public void processCommandShouldThrowInvalidSyntaxExceptionIfUrlInArgumentsIsNotCorrect() {
        String commandWithArguments = "ADD www.rte.ie/news/politics/2018/1004/1001034-cso/ 20";
        assertThrows(InvalidSyntaxException.class, () -> addCommandProcessor.processCommand(commandWithArguments));
    }

    @Test
    @DisplayName("processCommand should throw InvalidSyntaxException if the social score in argument is not an integer")
    public void processCommandShouldThrowInvalidSyntaxExceptionIfSocialScoreInArgumentsIsNotInteger() {
        String commandWithArguments = "ADD http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20s";
        assertThrows(InvalidSyntaxException.class, () -> addCommandProcessor.processCommand(commandWithArguments));
    }

    @Test
    @DisplayName("isProgramToContinue should return true")
    public void isProgramToContinueShouldReturnTrue() {
        assertTrue(addCommandProcessor.isProgramToContinue());
    }
}
