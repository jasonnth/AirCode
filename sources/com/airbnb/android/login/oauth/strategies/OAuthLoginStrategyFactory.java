package com.airbnb.android.login.oauth.strategies;

import android.support.p002v7.app.AppCompatActivity;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.google.android.gms.auth.api.credentials.Credential;

public class OAuthLoginStrategyFactory {
    public static OAuthLoginStrategy create(OAuthOption option, AppCompatActivity appCompatActivity, OAuthLoginManager oauthLoginManager) {
        switch (option) {
            case Facebook:
                return new FacebookLoginStrategy(appCompatActivity, oauthLoginManager);
            case Google:
                return new GoogleLoginStrategy(appCompatActivity, oauthLoginManager);
            case Alipay:
                return new AlipayLoginStrategy(appCompatActivity, oauthLoginManager);
            case Weibo:
                return new WeiboLoginStrategy(appCompatActivity, oauthLoginManager);
            case Wechat:
                return new WechatLoginStrategy(appCompatActivity, oauthLoginManager);
            default:
                throw new UnhandledStateException(option);
        }
    }

    public static OAuthLoginStrategy createGoogleStrategyWithCredential(Credential credential, AppCompatActivity appCompatActivity, OAuthLoginManager oauthLoginManager) {
        return new GoogleLoginStrategy(appCompatActivity, oauthLoginManager).withCredential(credential);
    }
}
