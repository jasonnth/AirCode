package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PhoneVerificationFragment$$Lambda$2 implements Action1 {
    private final PhoneVerificationFragment arg$1;

    private PhoneVerificationFragment$$Lambda$2(PhoneVerificationFragment phoneVerificationFragment) {
        this.arg$1 = phoneVerificationFragment;
    }

    public static Action1 lambdaFactory$(PhoneVerificationFragment phoneVerificationFragment) {
        return new PhoneVerificationFragment$$Lambda$2(phoneVerificationFragment);
    }

    public void call(Object obj) {
        PhoneVerificationFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
