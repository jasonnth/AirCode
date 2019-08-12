package com.airbnb.android.login.utils;

import android.content.Intent;
import com.airbnb.android.login.utils.LoginUtils.GoogleAuthHelper;

final /* synthetic */ class LoginUtils$GoogleAuthHelper$$Lambda$4 implements Runnable {
    private final GoogleAuthHelper arg$1;
    private final Intent arg$2;

    private LoginUtils$GoogleAuthHelper$$Lambda$4(GoogleAuthHelper googleAuthHelper, Intent intent) {
        this.arg$1 = googleAuthHelper;
        this.arg$2 = intent;
    }

    public static Runnable lambdaFactory$(GoogleAuthHelper googleAuthHelper, Intent intent) {
        return new LoginUtils$GoogleAuthHelper$$Lambda$4(googleAuthHelper, intent);
    }

    public void run() {
        GoogleAuthHelper.lambda$doPermissions$1(this.arg$1, this.arg$2);
    }
}
