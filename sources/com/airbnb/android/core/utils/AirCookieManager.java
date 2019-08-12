package com.airbnb.android.core.utils;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.models.UserWebSession;
import com.airbnb.android.core.persist.DomainStore;

public class AirCookieManager {
    private static final String COOKIE_EXPIRATION_NEVER = "Expires=Tue, 15-Jan-2050 21:47:38 GMT";
    private static final String COOKIE_SECURE = "secure; HttpOnly";
    private final CookieManager cookieManager = CookieManager.getInstance();
    private final CookieSyncManager cookieSyncManager;
    DomainStore domainStore;

    private static class Cookie {
        private final String name;
        private boolean secure;
        private boolean session;
        private final String value;

        Cookie(String name2, String value2) {
            this.name = name2;
            this.value = value2;
        }

        public Cookie setSecure(boolean isSecure) {
            this.secure = isSecure;
            return this;
        }

        public Cookie setSession(boolean isSession) {
            this.session = isSession;
            return this;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(this.name);
            builder.append("=");
            builder.append(this.value);
            if (this.secure) {
                builder.append(";");
                builder.append(AirCookieManager.COOKIE_SECURE);
            }
            if (!this.session) {
                builder.append(";");
                builder.append(AirCookieManager.COOKIE_EXPIRATION_NEVER);
            }
            return builder.toString();
        }
    }

    public AirCookieManager(Context context) {
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
        this.cookieSyncManager = CookieSyncManager.createInstance(context);
        this.cookieManager.setAcceptCookie(true);
        addAirbnbBannerCookie();
    }

    private void addAirbnbBannerCookie() {
        setCookieForAllDomains(new Cookie("sbc", "1"));
    }

    private void setCookieForAllDomains(Cookie cookie) {
        String cookieString = cookie.toString();
        for (String domain : this.domainStore.getDomainList()) {
            this.cookieManager.setCookie(getHost(domain), cookieString);
        }
        syncCookies();
    }

    private void syncCookies() {
        this.cookieSyncManager.sync();
    }

    private static String getHost(String domainName) {
        if (domainName.startsWith("airbnb.")) {
            return "." + domainName;
        }
        return domainName;
    }

    public void setAirbnbSession(UserWebSession session) {
        setCookieForAllDomains(new Cookie(session.getCookieName(), session.getSessionId()).setSecure(true).setSession(true));
    }

    public static void clearSessionCookies(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeSessionCookie();
    }
}
