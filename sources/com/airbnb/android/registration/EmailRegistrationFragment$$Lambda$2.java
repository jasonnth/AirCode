package com.airbnb.android.registration;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class EmailRegistrationFragment$$Lambda$2 implements Action1 {
    private final EmailRegistrationFragment arg$1;

    private EmailRegistrationFragment$$Lambda$2(EmailRegistrationFragment emailRegistrationFragment) {
        this.arg$1 = emailRegistrationFragment;
    }

    public static Action1 lambdaFactory$(EmailRegistrationFragment emailRegistrationFragment) {
        return new EmailRegistrationFragment$$Lambda$2(emailRegistrationFragment);
    }

    public void call(Object obj) {
        EmailRegistrationFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
