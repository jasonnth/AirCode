package com.airbnb.android.registration;

final /* synthetic */ class PhoneNumberRegistrationFragment$$Lambda$8 implements Runnable {
    private final PhoneNumberRegistrationFragment arg$1;

    private PhoneNumberRegistrationFragment$$Lambda$8(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        this.arg$1 = phoneNumberRegistrationFragment;
    }

    public static Runnable lambdaFactory$(PhoneNumberRegistrationFragment phoneNumberRegistrationFragment) {
        return new PhoneNumberRegistrationFragment$$Lambda$8(phoneNumberRegistrationFragment);
    }

    public void run() {
        this.arg$1.goToConfirmation();
    }
}
