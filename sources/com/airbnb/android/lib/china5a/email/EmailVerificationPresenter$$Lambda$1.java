package com.airbnb.android.lib.china5a.email;

import com.airbnb.android.lib.china5a.VerificationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class EmailVerificationPresenter$$Lambda$1 implements Action1 {
    private final EmailVerificationPresenter arg$1;

    private EmailVerificationPresenter$$Lambda$1(EmailVerificationPresenter emailVerificationPresenter) {
        this.arg$1 = emailVerificationPresenter;
    }

    public static Action1 lambdaFactory$(EmailVerificationPresenter emailVerificationPresenter) {
        return new EmailVerificationPresenter$$Lambda$1(emailVerificationPresenter);
    }

    public void call(Object obj) {
        EmailVerificationPresenter.lambda$start$0(this.arg$1, (VerificationResponse) obj);
    }
}
