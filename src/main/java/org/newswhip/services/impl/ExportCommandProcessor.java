package org.newswhip.services.impl;

import org.newswhip.data.SocialScoreStore;
import org.newswhip.domains.SocialScoreReport;
import org.newswhip.exceptions.InvalidSyntaxException;
import org.newswhip.services.CommandProcessor;

import java.util.List;

import static java.lang.String.format;

public class ExportCommandProcessor implements CommandProcessor {

    private static final String PRINT_FORMAT = "%s;%s;%s%n";

    private final SocialScoreStore socialScoreStore;

    protected ExportCommandProcessor(SocialScoreStore socialScoreStore) {
        this.socialScoreStore = socialScoreStore;
    }

    @Override
    public void processCommand(String commandWithArguments) throws InvalidSyntaxException{
        parseExportCommand(commandWithArguments);
        List<SocialScoreReport> socialScoreReportList = socialScoreStore.getSocialScoreReport();
        printSocialScoreReport(socialScoreReportList);
    }

    @Override
    public boolean isProgramToContinue() {
        return true;
    }

    private void parseExportCommand(String commandWithArguments) throws InvalidSyntaxException {
        String[] command = commandWithArguments.split(" ");
        if (command.length != 1) {
            throw new InvalidSyntaxException(commandWithArguments, command[0]);
        }
    }

    private void printSocialScoreReport(List<SocialScoreReport> socialScoreReportList) {
        System.out.printf(PRINT_FORMAT, "domain", "urls", "social_score");
        socialScoreReportList.forEach(socialScoreReport ->
                System.out.printf(PRINT_FORMAT, socialScoreReport.getDomain(), socialScoreReport.getUrls(),
                        socialScoreReport.getSocialScore()));

    }
}
