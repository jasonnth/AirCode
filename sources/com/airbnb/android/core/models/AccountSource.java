package com.airbnb.android.core.models;

import android.text.TextUtils;
import com.airbnb.android.core.analytics.RegistrationAnalytics;

public enum AccountSource {
    Phone("phone", 5, false, "phone"),
    Email("email", 0, false, "email"),
    Facebook("facebook", 1, true, "facebook"),
    Google("google", 2, true, RegistrationAnalytics.GPLUS),
    Weibo(RegistrationAnalytics.WEIBO, 3, true, RegistrationAnalytics.WEIBO),
    WeChat(RegistrationAnalytics.WECHAT, 4, true, RegistrationAnalytics.WECHAT),
    Alipay("alipay", 10, true, "alipay"),
    MoWeb("mobile_web_token", -1, true, RegistrationAnalytics.MOWEB);
    
    private final String name;
    private final String serviceNameForAnalytics;
    private final int signupMethod;
    private final boolean socialNetwork;

    private AccountSource(String name2, int signupMethod2, boolean socialNetwork2, String serviceNameForAnalytics2) {
        this.name = name2;
        this.signupMethod = signupMethod2;
        this.socialNetwork = socialNetwork2;
        this.serviceNameForAnalytics = serviceNameForAnalytics2;
    }

    public static AccountSource findAccountSourceByName(String name2) {
        AccountSource[] values;
        if (TextUtils.isEmpty(name2)) {
            return null;
        }
        for (AccountSource source : values()) {
            if (TextUtils.equals(name2.toLowerCase(), source.getName().toLowerCase())) {
                return source;
            }
        }
        return null;
    }

    public static AccountSource findAccountSourceBySignupMethodCode(int signupMethod2) {
        AccountSource[] values;
        for (AccountSource source : values()) {
            if (signupMethod2 == source.signupMethod) {
                return source;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public boolean isSocialNetwork() {
        return this.socialNetwork;
    }

    public String getServiceNameForAnalytics() {
        return this.serviceNameForAnalytics;
    }
}
