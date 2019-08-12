package com.airbnb.android.login.p339ui;

import com.airbnb.android.core.responses.AccountResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.LoginActivity$$Lambda$3 */
final /* synthetic */ class LoginActivity$$Lambda$3 implements Action1 {
    private final LoginActivity arg$1;

    private LoginActivity$$Lambda$3(LoginActivity loginActivity) {
        this.arg$1 = loginActivity;
    }

    public static Action1 lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$3(loginActivity);
    }

    public void call(Object obj) {
        LoginActivity.lambda$new$1(this.arg$1, (AccountResponse) obj);
    }
}
