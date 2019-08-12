package com.airbnb.android.registration;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PhoneNumberRegistrationFragment$$Lambda$5 implements OnClickListener {
    private final PhoneNumberRegistrationFragment arg$1;

    private PhoneNumberRegistrationFragment$$Lambda$5(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        this.arg$1 = phoneNumberRegistrationFragment;
    }

    public static OnClickListener lambdaFactory$(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        return new PhoneNumberRegistrationFragment$$Lambda$5(phoneNumberRegistrationFragment);
    }

    public void onClick(View view) {
        PhoneNumberRegistrationFragment.lambda$new$6(this.arg$1, view);
    }
}
