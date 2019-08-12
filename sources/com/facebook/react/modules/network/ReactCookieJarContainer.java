package com.facebook.react.modules.network;

import java.util.Collections;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class ReactCookieJarContainer implements CookieJarContainer {
    private CookieJar cookieJar = null;

    public void setCookieJar(CookieJar cookieJar2) {
        this.cookieJar = cookieJar2;
    }

    public void removeCookieJar() {
        this.cookieJar = null;
    }

    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (this.cookieJar != null) {
            this.cookieJar.saveFromResponse(url, cookies);
        }
    }

    public List<Cookie> loadForRequest(HttpUrl url) {
        if (this.cookieJar != null) {
            return this.cookieJar.loadForRequest(url);
        }
        return Collections.emptyList();
    }
}
