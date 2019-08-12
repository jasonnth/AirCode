package com.airbnb.android.login.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.airbnb.android.login.utils.LoginUtils.GoogleAuthHelper;

final /* synthetic */ class LoginUtils$GoogleAuthHelper$$Lambda$1 implements OnClickListener {
    private final GoogleAuthHelper arg$1;
    private final String[] arg$2;

    private LoginUtils$GoogleAuthHelper$$Lambda$1(GoogleAuthHelper googleAuthHelper, String[] strArr) {
        this.arg$1 = googleAuthHelper;
        this.arg$2 = strArr;
    }

    public static OnClickListener lambdaFactory$(GoogleAuthHelper googleAuthHelper, String[] strArr) {
        return new LoginUtils$GoogleAuthHelper$$Lambda$1(googleAuthHelper, strArr);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        GoogleAuthHelper.lambda$getAccountAndLogin$0(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
