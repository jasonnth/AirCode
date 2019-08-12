package com.airbnb.android.core.enums;

import com.airbnb.android.core.analytics.RegistrationAnalytics;

public enum AuthorizeService {
    GOOGLE("google"),
    FACEBOOK("facebook"),
    WEIBO(RegistrationAnalytics.WEIBO),
    WECHAT(RegistrationAnalytics.WECHAT);
    
    public final String name;

    private AuthorizeService(String name2) {
        this.name = name2;
    }
}
