package com.airbnb.android.login.p339ui;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.LoginActivity$$Lambda$6 */
final /* synthetic */ class LoginActivity$$Lambda$6 implements Action1 {
    private final LoginActivity arg$1;

    private LoginActivity$$Lambda$6(LoginActivity loginActivity) {
        this.arg$1 = loginActivity;
    }

    public static Action1 lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$6(loginActivity);
    }

    public void call(Object obj) {
        this.arg$1.onNetworkError((AirRequestNetworkException) obj);
    }
}
