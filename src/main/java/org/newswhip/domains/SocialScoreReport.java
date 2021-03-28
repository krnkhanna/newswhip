package org.newswhip.domains;

public class SocialScoreReport {

    private final String domain;

    private final int urls;

    private final int socialScore;

    public SocialScoreReport(String domain, int urls, int socialScore) {
        this.domain = domain;
        this.urls = urls;
        this.socialScore = socialScore;
    }

    public String getDomain() {
        return domain;
    }

    public int getUrls() {
        return urls;
    }

    public int getSocialScore() {
        return socialScore;
    }
}
