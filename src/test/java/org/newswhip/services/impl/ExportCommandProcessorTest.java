package org.newswhip.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.newswhip.data.SocialScoreStore;
import org.newswhip.exceptions.InvalidSyntaxException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ExportCommandProcessorTest {

    private ExportCommandProcessor exportCommandProcessor;

    @Mock
    private SocialScoreStore socialScoreStore;

    @BeforeEach
    public void setUp() {
        exportCommandProcessor = new ExportCommandProcessor(socialScoreStore);
    }

    @Test
    @DisplayName("processCommand should get list of social score report")
    public void processCommandShouldGetListOfSocialScoreReport() throws InvalidSyntaxException {
        String commandWithArguments = "EXPORT";
        exportCommandProcessor.processCommand(commandWithArguments);
        verify(socialScoreStore, times(1)).getSocialScoreReport();
    }

    @Test
    @DisplayName("processCommand should throw InvalidSyntaxException if the number of arguments is not 1")
    public void processCommandShouldThrowInvalidSyntaxExceptionIfNumberOfArgumentsNot2() {
        String commandWithArguments = "EXPORT http://www.rte.ie/news/politics/2018/1004/1001034-cso/";
        assertThrows(InvalidSyntaxException.class, () -> exportCommandProcessor.processCommand(commandWithArguments));
    }

    @Test
    @DisplayName("isProgramToContinue should return true")
    public void isProgramToContinueShouldReturnTrue() {
        assertTrue(exportCommandProcessor.isProgramToContinue());
    }

}
