package com.airbnb.android.core.net;

import com.airbnb.airrequest.BaseUrl;
import okhttp3.HttpUrl;

public class AirbnbApiUrlMatcher {
    private final BaseUrl apiBaseUrl;

    public AirbnbApiUrlMatcher(BaseUrl apiBaseUrl2) {
        this.apiBaseUrl = apiBaseUrl2;
    }

    /* access modifiers changed from: 0000 */
    public boolean matches(HttpUrl httpUrl) {
        return httpUrl.host().matches("[\\w\\-]*" + this.apiBaseUrl.url().host());
    }
}
