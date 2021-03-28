package org.newswhip.utilities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UrlUtilityTest {

    @Test
    @DisplayName("getDomainFromUrl should return domain name if Url is valid and starts with www.")
    public void getDomainFromUrlShouldReturnDomainNameFromUrlValidStartingWithWWW() throws MalformedURLException {
        String url = "http://www.rte.ie/news/politics/2018/1004/1001034-cso/";
        String domain = UrlUtility.getDomainNameFromUrl(url);
        assertEquals("rte.ie", domain);
    }

    @Test
    @DisplayName("getDomainFromUrl should return domain name if Url is valid ")
    public void getDomainFromUrlShouldReturnDomainNameFromUrlValid() throws MalformedURLException {
        String url = "http://rte.ie/news/politics/2018/1004/1001034-cso/";
        String domain = UrlUtility.getDomainNameFromUrl(url);
        assertEquals("rte.ie", domain);
    }

    @Test
    @DisplayName("getDomainFromUrl should throw MalformedURLException if Url is null")
    public void getDomainFromUrlShouldThrowMalformedURLExceptionifUrlIsNull() {
        String url = null;
        assertThrows(MalformedURLException.class, () -> UrlUtility.getDomainNameFromUrl(url));
    }

    @Test
    @DisplayName("getDomainFromUrl should throw MalformedURLException if Url is empty")
    public void getDomainFromUrlShouldThrowMalformedURLExceptionifUrlIsEmpty() {
        String url = "";
        assertThrows(MalformedURLException.class, () -> UrlUtility.getDomainNameFromUrl(url));
    }

    @Test
    @DisplayName("getDomainFromUrl should throw MalformedURLException if Url is invalid")
    public void getDomainFromUrlShouldThrowMalformedURLExceptionifUrlIsInvalid() {
        String url = "wwwgoogle.com";
        assertThrows(MalformedURLException.class, () -> UrlUtility.getDomainNameFromUrl(url));
    }
}
