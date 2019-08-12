package com.airbnb.android.login.oauth;

import android.text.TextUtils;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.login.C7331R;

public enum OAuthOption {
    Facebook(C7331R.string.facebook, C7331R.C7332drawable.icon_logo_facebook, "facebook"),
    Google(C7331R.string.sign_in_with_google, C7331R.C7332drawable.icon_logo_google, RegistrationAnalytics.GPLUS),
    Weibo(C7331R.string.weibo, C7331R.C7332drawable.icon_logo_weibo, RegistrationAnalytics.WEIBO),
    Wechat(C7331R.string.wechat, C7331R.C7332drawable.icon_logo_wechat, RegistrationAnalytics.WECHAT),
    Alipay(C7331R.string.alipay, C7331R.C7332drawable.icon_logo_alipay, "alipay");
    
    public final int icon;
    public final String serviceNameForAnalytics;
    public final int title;

    private OAuthOption(int title2, int icon2, String serviceNameForAnalytics2) {
        this.title = title2;
        this.icon = icon2;
        this.serviceNameForAnalytics = serviceNameForAnalytics2;
    }

    public static OAuthOption stringToOAuthOption(String oauthOption) {
        OAuthOption[] values;
        if (TextUtils.isEmpty(oauthOption)) {
            return null;
        }
        for (OAuthOption option : values()) {
            if (oauthOption.equalsIgnoreCase(option.toString())) {
                return option;
            }
        }
        return null;
    }
}
