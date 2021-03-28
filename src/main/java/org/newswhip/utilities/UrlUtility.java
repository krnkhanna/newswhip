package org.newswhip.utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtility {

    private UrlUtility() {

    }

    public static String getDomainNameFromUrl(String urlString) throws MalformedURLException {
        String domainName = new URL(urlString).getHost();
        if(domainName.startsWith("www.")) {
            return domainName.replace("www.", "");
        }
        return domainName;
    }
}
