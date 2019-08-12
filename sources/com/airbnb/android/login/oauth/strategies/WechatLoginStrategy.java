package com.airbnb.android.login.oauth.strategies;

import android.support.p002v7.app.AppCompatActivity;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.events.WechatLoginAuthCodeEvent;
import com.airbnb.android.core.events.WechatLoginFailedEvent;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.squareup.otto.Subscribe;

class WechatLoginStrategy extends OAuthLoginStrategy {
    protected WechatLoginStrategy(AppCompatActivity appCompatActivity, OAuthLoginManager oauthLoginManager) {
        super(appCompatActivity, oauthLoginManager);
        CoreApplication.instance().component().bus().register(this);
    }

    public void login() {
        WeChatHelper.loginWithWeChat(getActivity());
    }

    /* access modifiers changed from: protected */
    public void onFinish() {
        CoreApplication.instance().component().bus().unregister(this);
        super.onFinish();
    }

    @Subscribe
    public void onWechatAuthCodeRecevied(WechatLoginAuthCodeEvent e) {
        finishWithToken(e.getWechatAuthCode());
    }

    @Subscribe
    public void onWechatLoginFailed(WechatLoginFailedEvent e) {
        finishWithError();
    }

    /* access modifiers changed from: protected */
    public OAuthOption getOAuthOption() {
        return OAuthOption.Wechat;
    }
}
