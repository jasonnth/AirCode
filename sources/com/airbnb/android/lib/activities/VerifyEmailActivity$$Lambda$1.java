package com.airbnb.android.lib.activities;

import com.airbnb.android.core.responses.UserResponse;
import p032rx.functions.Action1;

final /* synthetic */ class VerifyEmailActivity$$Lambda$1 implements Action1 {
    private final VerifyEmailActivity arg$1;

    private VerifyEmailActivity$$Lambda$1(VerifyEmailActivity verifyEmailActivity) {
        this.arg$1 = verifyEmailActivity;
    }

    public static Action1 lambdaFactory$(VerifyEmailActivity verifyEmailActivity) {
        return new VerifyEmailActivity$$Lambda$1(verifyEmailActivity);
    }

    public void call(Object obj) {
        VerifyEmailActivity.lambda$new$0(this.arg$1, (UserResponse) obj);
    }
}
