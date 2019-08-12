package com.airbnb.android.login.oauth.strategies;

import android.content.Intent;
import android.support.p002v7.app.AppCompatActivity;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.android.login.oauth.webview.OauthActivity;
import com.airbnb.android.login.oauth.webview.OauthActivity.Service;

class WeiboLoginStrategy extends OAuthLoginStrategy {
    private static final int RC_AUTH_WITH_WEIBO = 303;

    protected WeiboLoginStrategy(AppCompatActivity appCompatActivity, OAuthLoginManager oauthLoginManager) {
        super(appCompatActivity, oauthLoginManager);
    }

    public void login() {
        getActivity().startActivityForResult(OauthActivity.intentForService(getActivity(), Service.WEIBO), 303);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 303 && resultCode == -1) {
            finishWithToken(data.getStringExtra("code"));
        } else if (requestCode == 303 && resultCode == 0) {
            finishWithCanceled();
        } else if (requestCode == 303) {
            finishWithError();
        }
    }

    /* access modifiers changed from: protected */
    public OAuthOption getOAuthOption() {
        return OAuthOption.Weibo;
    }
}
