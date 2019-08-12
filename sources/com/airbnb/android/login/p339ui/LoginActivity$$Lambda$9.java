package com.airbnb.android.login.p339ui;

import android.support.p000v4.app.FragmentManager.OnBackStackChangedListener;

/* renamed from: com.airbnb.android.login.ui.LoginActivity$$Lambda$9 */
final /* synthetic */ class LoginActivity$$Lambda$9 implements OnBackStackChangedListener {
    private final LoginActivity arg$1;

    private LoginActivity$$Lambda$9(LoginActivity loginActivity) {
        this.arg$1 = loginActivity;
    }

    public static OnBackStackChangedListener lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$9(loginActivity);
    }

    public void onBackStackChanged() {
        LoginActivity.lambda$setUpBackNavigation$3(this.arg$1);
    }
}
