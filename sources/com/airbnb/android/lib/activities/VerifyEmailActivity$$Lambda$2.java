package com.airbnb.android.lib.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class VerifyEmailActivity$$Lambda$2 implements Action1 {
    private final VerifyEmailActivity arg$1;

    private VerifyEmailActivity$$Lambda$2(VerifyEmailActivity verifyEmailActivity) {
        this.arg$1 = verifyEmailActivity;
    }

    public static Action1 lambdaFactory$(VerifyEmailActivity verifyEmailActivity) {
        return new VerifyEmailActivity$$Lambda$2(verifyEmailActivity);
    }

    public void call(Object obj) {
        VerifyEmailActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
