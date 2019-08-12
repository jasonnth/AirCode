package com.airbnb.android.login.p339ui;

import com.airbnb.android.login.responses.UserLoginResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.LoginActivity$$Lambda$7 */
final /* synthetic */ class LoginActivity$$Lambda$7 implements Action1 {
    private final LoginActivity arg$1;

    private LoginActivity$$Lambda$7(LoginActivity loginActivity) {
        this.arg$1 = loginActivity;
    }

    public static Action1 lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$7(loginActivity);
    }

    public void call(Object obj) {
        this.arg$1.logInRequestSuccess((UserLoginResponse) obj);
    }
}
