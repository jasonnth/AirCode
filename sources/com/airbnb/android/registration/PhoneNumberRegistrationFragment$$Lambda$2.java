package com.airbnb.android.registration;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PhoneNumberRegistrationFragment$$Lambda$2 implements Action1 {
    private final PhoneNumberRegistrationFragment arg$1;

    private PhoneNumberRegistrationFragment$$Lambda$2(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        this.arg$1 = phoneNumberRegistrationFragment;
    }

    public static Action1 lambdaFactory$(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        return new PhoneNumberRegistrationFragment$$Lambda$2(phoneNumberRegistrationFragment);
    }

    public void call(Object obj) {
        PhoneNumberRegistrationFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
