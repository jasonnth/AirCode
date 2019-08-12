package com.airbnb.android.login.p339ui;

import com.airbnb.android.core.responses.CountriesResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.LoginLandingFragment$$Lambda$1 */
final /* synthetic */ class LoginLandingFragment$$Lambda$1 implements Action1 {
    private final LoginLandingFragment arg$1;

    private LoginLandingFragment$$Lambda$1(LoginLandingFragment loginLandingFragment) {
        this.arg$1 = loginLandingFragment;
    }

    public static Action1 lambdaFactory$(LoginLandingFragment loginLandingFragment) {
        return new LoginLandingFragment$$Lambda$1(loginLandingFragment);
    }

    public void call(Object obj) {
        LoginLandingFragment.lambda$new$0(this.arg$1, (CountriesResponse) obj);
    }
}
