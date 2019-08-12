package com.airbnb.android.registration;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PhoneNumberRegistrationConfirmationFragment$$Lambda$4 implements Action1 {
    private final PhoneNumberRegistrationConfirmationFragment arg$1;

    private PhoneNumberRegistrationConfirmationFragment$$Lambda$4(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment) {
        this.arg$1 = phoneNumberRegistrationConfirmationFragment;
    }

    public static Action1 lambdaFactory$(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment) {
        return new PhoneNumberRegistrationConfirmationFragment$$Lambda$4(phoneNumberRegistrationConfirmationFragment);
    }

    public void call(Object obj) {
        PhoneNumberRegistrationConfirmationFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
