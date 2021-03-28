package org.newswhip.domains;

public class SocialScore {

    private final String domain;

    private final String url;

    private final int count;

    public SocialScore(String domain, String url, int count) {
        this.domain = domain.toLowerCase();
        this.url = url.toLowerCase();
        this.count = count;
    }

    public SocialScore(String domain, String url) {
        this(domain, url, 0);
    }

    public String getDomain() {
        return domain;
    }

    public String getUrl() {
        return url;
    }

    public int getCount() {
        return count;
    }
}
