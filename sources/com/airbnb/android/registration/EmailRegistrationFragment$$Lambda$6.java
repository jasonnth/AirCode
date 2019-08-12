package com.airbnb.android.registration;

final /* synthetic */ class EmailRegistrationFragment$$Lambda$6 implements Runnable {
    private final EmailRegistrationFragment arg$1;

    private EmailRegistrationFragment$$Lambda$6(EmailRegistrationFragment emailRegistrationFragment) {
        this.arg$1 = emailRegistrationFragment;
    }

    public static Runnable lambdaFactory$(EmailRegistrationFragment emailRegistrationFragment) {
        return new EmailRegistrationFragment$$Lambda$6(emailRegistrationFragment);
    }

    public void run() {
        this.arg$1.onEmailValidated();
    }
}
