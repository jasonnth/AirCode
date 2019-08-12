package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.login.oauth.OAuthOption;

/* renamed from: com.airbnb.android.login.ui.LoginActivity$$Lambda$10 */
final /* synthetic */ class LoginActivity$$Lambda$10 implements OnClickListener {
    private final LoginActivity arg$1;
    private final OAuthOption arg$2;

    private LoginActivity$$Lambda$10(LoginActivity loginActivity, OAuthOption oAuthOption) {
        this.arg$1 = loginActivity;
        this.arg$2 = oAuthOption;
    }

    public static OnClickListener lambdaFactory$(LoginActivity loginActivity, OAuthOption oAuthOption) {
        return new LoginActivity$$Lambda$10(loginActivity, oAuthOption);
    }

    public void onClick(View view) {
        this.arg$1.onLogInWithOAuthOption(this.arg$2);
    }
}
