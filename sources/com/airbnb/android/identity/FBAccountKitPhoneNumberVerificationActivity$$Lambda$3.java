package com.airbnb.android.identity;

import p032rx.functions.Action1;

final /* synthetic */ class FBAccountKitPhoneNumberVerificationActivity$$Lambda$3 implements Action1 {
    private final FBAccountKitPhoneNumberVerificationActivity arg$1;

    private FBAccountKitPhoneNumberVerificationActivity$$Lambda$3(FBAccountKitPhoneNumberVerificationActivity fBAccountKitPhoneNumberVerificationActivity) {
        this.arg$1 = fBAccountKitPhoneNumberVerificationActivity;
    }

    public static Action1 lambdaFactory$(FBAccountKitPhoneNumberVerificationActivity fBAccountKitPhoneNumberVerificationActivity) {
        return new FBAccountKitPhoneNumberVerificationActivity$$Lambda$3(fBAccountKitPhoneNumberVerificationActivity);
    }

    public void call(Object obj) {
        this.arg$1.finish();
    }
}
