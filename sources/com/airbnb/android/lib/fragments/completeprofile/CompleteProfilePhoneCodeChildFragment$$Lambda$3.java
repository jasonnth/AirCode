package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CompleteProfilePhoneCodeChildFragment$$Lambda$3 implements Action1 {
    private final CompleteProfilePhoneCodeChildFragment arg$1;

    private CompleteProfilePhoneCodeChildFragment$$Lambda$3(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        this.arg$1 = completeProfilePhoneCodeChildFragment;
    }

    public static Action1 lambdaFactory$(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        return new CompleteProfilePhoneCodeChildFragment$$Lambda$3(completeProfilePhoneCodeChildFragment);
    }

    public void call(Object obj) {
        CompleteProfilePhoneCodeChildFragment.lambda$new$6(this.arg$1, (AirRequestNetworkException) obj);
    }
}
