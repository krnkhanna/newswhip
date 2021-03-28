package org.newswhip.services.impl;

import org.newswhip.data.SocialScoreStore;
import org.newswhip.domains.SocialScore;
import org.newswhip.exceptions.InvalidSyntaxException;
import org.newswhip.services.CommandProcessor;
import org.newswhip.utilities.UrlUtility;

import java.net.MalformedURLException;

public class AddCommandProcessor implements CommandProcessor {

    private final SocialScoreStore socialScoreStore;

    protected AddCommandProcessor(SocialScoreStore socialScoreStore) {
        this.socialScoreStore = socialScoreStore;
    }

    @Override
    public void processCommand(String commandWithArguments) throws InvalidSyntaxException {
        SocialScore socialScore = parseAddCommand(commandWithArguments);
        socialScoreStore.addSocialScore(socialScore);
    }

    @Override
    public boolean isProgramToContinue() {
        return true;
    }

    private SocialScore parseAddCommand(String commandWithArguments) throws InvalidSyntaxException {
        String[] command = commandWithArguments.split(" ");
        if (command.length != 3) {
            throw new InvalidSyntaxException(commandWithArguments, command[0]);
        } else {
            try {
                return new SocialScore(UrlUtility.getDomainNameFromUrl(
                        command[1]), command[1], Integer.parseInt(command[2]));
            } catch (NumberFormatException | MalformedURLException exception) {
                throw new InvalidSyntaxException(commandWithArguments, command[0]);
            }
        }
    }
}
