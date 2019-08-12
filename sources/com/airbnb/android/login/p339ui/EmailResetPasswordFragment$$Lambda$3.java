package com.airbnb.android.login.p339ui;

import com.airbnb.android.login.responses.ForgotPasswordResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment$$Lambda$3 */
final /* synthetic */ class EmailResetPasswordFragment$$Lambda$3 implements Action1 {
    private final EmailResetPasswordFragment arg$1;

    private EmailResetPasswordFragment$$Lambda$3(EmailResetPasswordFragment emailResetPasswordFragment) {
        this.arg$1 = emailResetPasswordFragment;
    }

    public static Action1 lambdaFactory$(EmailResetPasswordFragment emailResetPasswordFragment) {
        return new EmailResetPasswordFragment$$Lambda$3(emailResetPasswordFragment);
    }

    public void call(Object obj) {
        this.arg$1.onResetPasswordSuccess((ForgotPasswordResponse) obj);
    }
}
