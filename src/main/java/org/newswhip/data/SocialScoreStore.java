package org.newswhip.data;

import org.newswhip.domains.SocialScore;
import org.newswhip.domains.SocialScoreReport;

import java.util.List;

/**
 * The interface for interacting with the store for the social scores.
 * It could be database, or any other kind of storage.
 */
public interface SocialScoreStore {

    /**
     * Add social score to the store.
     *
     * @param socialScore social score.
     */
    void addSocialScore(SocialScore socialScore);

    /**
     * Remove the social score from the store.
     *
     * @param socialScore the social score to be removed.
     */
    void removeSocialScore(SocialScore socialScore);

    /**
     * Get the social score report.
     *
     * @return a list of the social score reports.
     */
    List<SocialScoreReport> getSocialScoreReport();

}
