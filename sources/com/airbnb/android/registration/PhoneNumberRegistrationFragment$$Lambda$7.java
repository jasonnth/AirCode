package com.airbnb.android.registration;

import p032rx.functions.Action1;

final /* synthetic */ class PhoneNumberRegistrationFragment$$Lambda$7 implements Action1 {
    private final PhoneNumberRegistrationFragment arg$1;

    private PhoneNumberRegistrationFragment$$Lambda$7(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        this.arg$1 = phoneNumberRegistrationFragment;
    }

    public static Action1 lambdaFactory$(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        return new PhoneNumberRegistrationFragment$$Lambda$7(phoneNumberRegistrationFragment);
    }

    public void call(Object obj) {
        this.arg$1.requestConfirmationCode();
    }
}
