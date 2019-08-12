package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.android.core.responses.PhoneNumberVerificationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CompleteProfilePhoneChildFragment$$Lambda$1 implements Action1 {
    private final CompleteProfilePhoneChildFragment arg$1;

    private CompleteProfilePhoneChildFragment$$Lambda$1(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment) {
        this.arg$1 = completeProfilePhoneChildFragment;
    }

    public static Action1 lambdaFactory$(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment) {
        return new CompleteProfilePhoneChildFragment$$Lambda$1(completeProfilePhoneChildFragment);
    }

    public void call(Object obj) {
        CompleteProfilePhoneChildFragment.lambda$new$3(this.arg$1, (PhoneNumberVerificationResponse) obj);
    }
}
