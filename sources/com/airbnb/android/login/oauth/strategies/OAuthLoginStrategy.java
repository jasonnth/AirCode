package com.airbnb.android.login.oauth.strategies;

import android.content.Intent;
import android.support.p002v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.android.login.oauth.OAuthStrategyListener;

public abstract class OAuthLoginStrategy {
    private final AppCompatActivity appCompatActivity;
    private final OAuthLoginManager oauthLoginManager;
    private OAuthStrategyListener oauthStrategyListener;

    /* access modifiers changed from: protected */
    public abstract OAuthOption getOAuthOption();

    public abstract void login();

    protected OAuthLoginStrategy(AppCompatActivity appCompatActivity2, OAuthLoginManager oauthLoginManager2) {
        this.appCompatActivity = appCompatActivity2;
        this.oauthLoginManager = oauthLoginManager2;
    }

    public OAuthLoginStrategy withListener(OAuthStrategyListener oauthStrategyListener2) {
        this.oauthStrategyListener = oauthStrategyListener2;
        return this;
    }

    /* access modifiers changed from: protected */
    public void finishWithToken(String token) {
        if (!TextUtils.isEmpty(token)) {
            this.oauthStrategyListener.onOAuthLoginSuccess(getOAuthOption(), token);
        } else {
            this.oauthStrategyListener.onOAuthLoginError(getOAuthOption());
        }
        onFinish();
    }

    /* access modifiers changed from: protected */
    public void finishWithCanceled() {
        this.oauthStrategyListener.onOAuthLoginCanceled(getOAuthOption());
        onFinish();
    }

    /* access modifiers changed from: protected */
    public void finishWithError() {
        this.oauthStrategyListener.onOAuthLoginError(getOAuthOption());
        onFinish();
    }

    /* access modifiers changed from: protected */
    public void onFinish() {
        this.oauthLoginManager.onStrategyFinished();
    }

    public AppCompatActivity getActivity() {
        return this.appCompatActivity;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
