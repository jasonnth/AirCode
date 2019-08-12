package com.airbnb.android.registration;

import com.airbnb.android.core.responses.PhoneNumberVerificationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PhoneNumberRegistrationConfirmationFragment$$Lambda$1 implements Action1 {
    private final PhoneNumberRegistrationConfirmationFragment arg$1;

    private PhoneNumberRegistrationConfirmationFragment$$Lambda$1(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment) {
        this.arg$1 = phoneNumberRegistrationConfirmationFragment;
    }

    public static Action1 lambdaFactory$(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment) {
        return new PhoneNumberRegistrationConfirmationFragment$$Lambda$1(phoneNumberRegistrationConfirmationFragment);
    }

    public void call(Object obj) {
        PhoneNumberRegistrationConfirmationFragment.lambda$new$1(this.arg$1, (PhoneNumberVerificationResponse) obj);
    }
}
