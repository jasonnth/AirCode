package com.airbnb.android.registration;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.registration.models.AccountLoginData;

final /* synthetic */ class PhoneNumberRegistrationFragment$$Lambda$6 implements OnClickListener {
    private final PhoneNumberRegistrationFragment arg$1;

    private PhoneNumberRegistrationFragment$$Lambda$6(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        this.arg$1 = phoneNumberRegistrationFragment;
    }

    public static OnClickListener lambdaFactory$(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        return new PhoneNumberRegistrationFragment$$Lambda$6(phoneNumberRegistrationFragment);
    }

    public void onClick(View view) {
        this.arg$1.registrationController.switchToLoginWithLoginData(AccountLoginData.builder(AccountSource.Phone).airPhone(this.arg$1.airPhone).build());
    }
}
