package com.airbnb.android.registration;

import com.airbnb.android.core.responses.AccountResponse;
import p032rx.functions.Action1;

final /* synthetic */ class EmailRegistrationFragment$$Lambda$1 implements Action1 {
    private final EmailRegistrationFragment arg$1;

    private EmailRegistrationFragment$$Lambda$1(EmailRegistrationFragment emailRegistrationFragment) {
        this.arg$1 = emailRegistrationFragment;
    }

    public static Action1 lambdaFactory$(EmailRegistrationFragment emailRegistrationFragment) {
        return new EmailRegistrationFragment$$Lambda$1(emailRegistrationFragment);
    }

    public void call(Object obj) {
        EmailRegistrationFragment.lambda$new$0(this.arg$1, (AccountResponse) obj);
    }
}
