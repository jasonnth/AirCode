package com.airbnb.android.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class FBAccountKitPhoneNumberVerificationActivity$$Lambda$2 implements Action1 {
    private final FBAccountKitPhoneNumberVerificationActivity arg$1;

    private FBAccountKitPhoneNumberVerificationActivity$$Lambda$2(FBAccountKitPhoneNumberVerificationActivity fBAccountKitPhoneNumberVerificationActivity) {
        this.arg$1 = fBAccountKitPhoneNumberVerificationActivity;
    }

    public static Action1 lambdaFactory$(FBAccountKitPhoneNumberVerificationActivity fBAccountKitPhoneNumberVerificationActivity) {
        return new FBAccountKitPhoneNumberVerificationActivity$$Lambda$2(fBAccountKitPhoneNumberVerificationActivity);
    }

    public void call(Object obj) {
        FBAccountKitPhoneNumberVerificationActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
