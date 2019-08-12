package com.airbnb.android.registration;

import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.models.AccountRegistrationStep;

final /* synthetic */ class PhoneNumberRegistrationConfirmationFragment$$Lambda$6 implements Runnable {
    private final PhoneNumberRegistrationConfirmationFragment arg$1;

    private PhoneNumberRegistrationConfirmationFragment$$Lambda$6(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment) {
        this.arg$1 = phoneNumberRegistrationConfirmationFragment;
    }

    public static Runnable lambdaFactory$(PhoneNumberRegistrationConfirmationFragment phoneNumberRegistrationConfirmationFragment) {
        return new PhoneNumberRegistrationConfirmationFragment$$Lambda$6(phoneNumberRegistrationConfirmationFragment);
    }

    public void run() {
        this.arg$1.registrationController.onStepFinished(AccountRegistrationStep.AccountIdentifier, AccountRegistrationData.builder().airPhone(this.arg$1.airPhone).accountSource(AccountSource.Phone).build());
    }
}
