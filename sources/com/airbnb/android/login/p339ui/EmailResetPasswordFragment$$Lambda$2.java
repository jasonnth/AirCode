package com.airbnb.android.login.p339ui;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment$$Lambda$2 */
final /* synthetic */ class EmailResetPasswordFragment$$Lambda$2 implements Action1 {
    private final EmailResetPasswordFragment arg$1;

    private EmailResetPasswordFragment$$Lambda$2(EmailResetPasswordFragment emailResetPasswordFragment) {
        this.arg$1 = emailResetPasswordFragment;
    }

    public static Action1 lambdaFactory$(EmailResetPasswordFragment emailResetPasswordFragment) {
        return new EmailResetPasswordFragment$$Lambda$2(emailResetPasswordFragment);
    }

    public void call(Object obj) {
        this.arg$1.onSecretVerificationFailed((AirRequestNetworkException) obj);
    }
}
