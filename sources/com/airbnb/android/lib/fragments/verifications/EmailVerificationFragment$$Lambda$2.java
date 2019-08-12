package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class EmailVerificationFragment$$Lambda$2 implements Action1 {
    private final EmailVerificationFragment arg$1;

    private EmailVerificationFragment$$Lambda$2(EmailVerificationFragment emailVerificationFragment) {
        this.arg$1 = emailVerificationFragment;
    }

    public static Action1 lambdaFactory$(EmailVerificationFragment emailVerificationFragment) {
        return new EmailVerificationFragment$$Lambda$2(emailVerificationFragment);
    }

    public void call(Object obj) {
        EmailVerificationFragment.lambda$new$0(this.arg$1, (BaseResponse) obj);
    }
}
