package org.newswhip.services.impl;

import org.newswhip.data.SocialScoreStore;
import org.newswhip.domains.SocialScore;
import org.newswhip.exceptions.InvalidSyntaxException;
import org.newswhip.services.CommandProcessor;
import org.newswhip.utilities.UrlUtility;

import java.net.MalformedURLException;

public class RemoveCommandProcessor implements CommandProcessor {

    private final SocialScoreStore socialScoreStore;

    protected RemoveCommandProcessor(SocialScoreStore socialScoreStore) {
        this.socialScoreStore = socialScoreStore;
    }

    @Override
    public void processCommand(String commandWithArguments) throws InvalidSyntaxException {
        SocialScore socialScore = parseRemoveCommand(commandWithArguments);
        socialScoreStore.removeSocialScore(socialScore);
    }

    @Override
    public boolean isProgramToContinue() {
        return true;
    }

    private SocialScore parseRemoveCommand(String commandWithArguments) throws InvalidSyntaxException {
        String[] command = commandWithArguments.split(" ");
        if (command.length != 2) {
            throw new InvalidSyntaxException(commandWithArguments, command[0]);
        } else {
            try {
                return new SocialScore(UrlUtility.getDomainNameFromUrl(command[1]), command[1], 0);
            } catch (NumberFormatException | MalformedURLException exception) {
                throw new InvalidSyntaxException(commandWithArguments, command[0]);
            }
        }
    }
}
