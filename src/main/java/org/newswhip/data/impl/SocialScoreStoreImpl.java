package org.newswhip.data.impl;

import org.newswhip.data.SocialScoreStore;
import org.newswhip.domains.SocialScore;
import org.newswhip.domains.SocialScoreReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialScoreStoreImpl implements SocialScoreStore {

    private final Map<String, Map<String, Integer>> socialScoreMap;

    public SocialScoreStoreImpl() {
        socialScoreMap = new HashMap<>();
    }

    @Override
    public void addSocialScore(SocialScore socialScore) {
        socialScoreMap.computeIfAbsent(socialScore.getDomain(),
                k -> socialScoreMap.put(socialScore.getDomain(), new HashMap<>()));
                socialScoreMap.get(socialScore.getDomain()).put(socialScore.getUrl(), socialScore.getCount());
    }

    @Override
    public void removeSocialScore(SocialScore socialScore) {
        if (socialScoreMap.containsKey(socialScore.getDomain())) {
            socialScoreMap.get(socialScore.getDomain()).keySet().removeIf(url -> url.equals(socialScore.getUrl()));
        }
    }

    @Override
    public List<SocialScoreReport> getSocialScoreReport() {
        List<SocialScoreReport> socialScoreReports = new ArrayList<>();
        for (String domain: socialScoreMap.keySet()) {
            int urls = 0;
            int socialScore = 0;
            for (String url: socialScoreMap.get(domain).keySet()) {
                urls++;
                socialScore += socialScoreMap.get(domain).get(url);
            }
            socialScoreReports.add(new SocialScoreReport(domain, urls, socialScore));
        }
        return socialScoreReports;
    }
}
