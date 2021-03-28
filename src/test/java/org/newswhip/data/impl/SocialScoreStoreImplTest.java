package org.newswhip.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.newswhip.data.SocialScoreStore;
import org.newswhip.domains.SocialScore;
import org.newswhip.domains.SocialScoreReport;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialScoreStoreImplTest {

    private static final String TEST_DOMAIN = "test_domain";

    private static final String TEST_URL = "test_url";

    private SocialScoreStore socialScoreStore;

    @BeforeEach
    public void setUp() {
        socialScoreStore = new SocialScoreStoreImpl();
    }

    @Test
    @DisplayName("addSocialScore should add social score details to the store")
    public void addSocialScoreShouldAddSocialScoreDetailsToTheStore() {
        SocialScore socialScore = new SocialScore(TEST_DOMAIN, TEST_URL, 20);
        socialScoreStore.addSocialScore(socialScore);
        List<SocialScoreReport> socialScoreReportList = socialScoreStore.getSocialScoreReport();
        assertThat(socialScoreReportList)
                .hasSize(1).extracting(SocialScoreReport::getDomain).containsExactlyInAnyOrder(TEST_DOMAIN);
        assertThat(socialScoreReportList)
                .hasSize(1).extracting(SocialScoreReport::getUrls).containsExactlyInAnyOrder(1);
        assertThat(socialScoreReportList)
                .hasSize(1).extracting(SocialScoreReport::getSocialScore).containsExactlyInAnyOrder(20);
    }

    @Test
    @DisplayName("removeSocialScore should remove social score details from the store")
    public void removeSocialScoreShouldRemoveSocialScoreDetailsFromTheStore() {
        SocialScore socialScore = new SocialScore(TEST_DOMAIN, TEST_URL, 20);
        socialScoreStore.addSocialScore(socialScore);
        socialScoreStore.removeSocialScore(socialScore);
        List<SocialScoreReport> socialScoreReportList = socialScoreStore.getSocialScoreReport();
        assertThat(socialScoreReportList).hasSize(1).extracting(SocialScoreReport::getUrls).containsExactlyInAnyOrder(0);
    }
}
