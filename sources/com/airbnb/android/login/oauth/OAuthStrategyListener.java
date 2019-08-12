package com.airbnb.android.login.oauth;

public interface OAuthStrategyListener {
    void onOAuthLoginCanceled(OAuthOption oAuthOption);

    void onOAuthLoginError(OAuthOption oAuthOption);

    void onOAuthLoginSuccess(OAuthOption oAuthOption, String str);
}
