package com.airbnb.android.registration;

import p032rx.functions.Action1;

final /* synthetic */ class PhoneNumberRegistrationConfirmationFragment$$Lambda$5 implements Action1 {
    private final PhoneNumberRegistrationConfirmationFragment arg$1;

    private PhoneNumberRegistrationConfirmationFragment$$Lambda$5(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment) {
        this.arg$1 = phoneNumberRegistrationConfirmationFragment;
    }

    public static Action1 lambdaFactory$(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment) {
        return new PhoneNumberRegistrationConfirmationFragment$$Lambda$5(phoneNumberRegistrationConfirmationFragment);
    }

    public void call(Object obj) {
        PhoneNumberRegistrationConfirmationFragment.lambda$onViewCreated$3(this.arg$1, (String) obj);
    }
}
