package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CompleteProfilePhoneChildFragment$$Lambda$2 implements Action1 {
    private final CompleteProfilePhoneChildFragment arg$1;

    private CompleteProfilePhoneChildFragment$$Lambda$2(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment) {
        this.arg$1 = completeProfilePhoneChildFragment;
    }

    public static Action1 lambdaFactory$(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment) {
        return new CompleteProfilePhoneChildFragment$$Lambda$2(completeProfilePhoneChildFragment);
    }

    public void call(Object obj) {
        CompleteProfilePhoneChildFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
