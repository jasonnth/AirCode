package com.airbnb.android.registration;

import com.airbnb.android.core.responses.PhoneNumberVerificationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PhoneNumberRegistrationFragment$$Lambda$1 implements Action1 {
    private final PhoneNumberRegistrationFragment arg$1;

    private PhoneNumberRegistrationFragment$$Lambda$1(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        this.arg$1 = phoneNumberRegistrationFragment;
    }

    public static Action1 lambdaFactory$(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        return new PhoneNumberRegistrationFragment$$Lambda$1(phoneNumberRegistrationFragment);
    }

    public void call(Object obj) {
        PhoneNumberRegistrationFragment.lambda$new$0(this.arg$1, (PhoneNumberVerificationResponse) obj);
    }
}