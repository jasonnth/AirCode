package com.airbnb.android.registration;

import com.airbnb.android.core.responses.AccountResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PhoneNumberRegistrationFragment$$Lambda$3 implements Action1 {
    private final PhoneNumberRegistrationFragment arg$1;

    private PhoneNumberRegistrationFragment$$Lambda$3(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        this.arg$1 = phoneNumberRegistrationFragment;
    }

    public static Action1 lambdaFactory$(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        return new PhoneNumberRegistrationFragment$$Lambda$3(phoneNumberRegistrationFragment);
    }

    public void call(Object obj) {
        PhoneNumberRegistrationFragment.lambda$new$4(this.arg$1, (AccountResponse) obj);
    }
}
