package com.airbnb.android.login.oauth;

import android.content.Context;
import android.content.Intent;
import android.support.p002v7.app.AppCompatActivity;
import android.util.Pair;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.ExternalAppUtils;
import com.airbnb.android.login.oauth.strategies.OAuthLoginStrategy;
import com.airbnb.android.login.oauth.strategies.OAuthLoginStrategyFactory;
import com.google.android.gms.auth.api.credentials.Credential;
import java.util.ArrayList;
import java.util.List;

public class OAuthLoginManager {
    private static OAuthLoginManager INSTANCE;
    private OAuthLoginStrategy oauthLoginStrategy;

    public static OAuthLoginManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OAuthLoginManager();
        }
        return INSTANCE;
    }

    public static Pair<OAuthOption, List<OAuthOption>> getOAuthLoginOptions(Context context) {
        OAuthOption primaryOption;
        OAuthOption primaryOption2;
        List<OAuthOption> allOptions = new ArrayList<>();
        if (ChinaUtils.isUserInChinaOrPrefersChineseLanguage()) {
            if (ExternalAppUtils.isWechatInstalled(context)) {
                primaryOption2 = OAuthOption.Wechat;
                allOptions.add(OAuthOption.Wechat);
            } else if (ExternalAppUtils.isFacebookInstalled(context)) {
                primaryOption2 = OAuthOption.Facebook;
            } else {
                primaryOption2 = OAuthOption.Weibo;
            }
            allOptions.add(OAuthOption.Weibo);
            if (ChinaUtils.isAlipayInstalled(context)) {
                allOptions.add(OAuthOption.Alipay);
            }
            allOptions.add(OAuthOption.Google);
            allOptions.add(OAuthOption.Facebook);
        } else {
            if (ExternalAppUtils.isFacebookInstalled(context)) {
                primaryOption = OAuthOption.Facebook;
                allOptions.add(OAuthOption.Facebook);
                allOptions.add(OAuthOption.Google);
            } else {
                primaryOption = OAuthOption.Google;
                allOptions.add(OAuthOption.Google);
                allOptions.add(OAuthOption.Facebook);
            }
            if (ExternalAppUtils.isWechatInstalled(context)) {
                allOptions.add(OAuthOption.Wechat);
            }
            allOptions.add(OAuthOption.Weibo);
            if (ChinaUtils.isAlipayInstalled(context)) {
                allOptions.add(OAuthOption.Alipay);
            }
        }
        return new Pair<>(primaryOption2, allOptions);
    }

    public void loginWith(OAuthOption option, AppCompatActivity activity, OAuthStrategyListener listener) {
        this.oauthLoginStrategy = OAuthLoginStrategyFactory.create(option, activity, this).withListener(listener);
        this.oauthLoginStrategy.login();
    }

    public void loginWithGoogleCredential(Credential credential, AppCompatActivity context, OAuthStrategyListener listener) {
        this.oauthLoginStrategy = OAuthLoginStrategyFactory.createGoogleStrategyWithCredential(credential, context, this).withListener(listener);
        this.oauthLoginStrategy.login();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.oauthLoginStrategy != null) {
            this.oauthLoginStrategy.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onStrategyFinished() {
        this.oauthLoginStrategy = null;
    }
}
