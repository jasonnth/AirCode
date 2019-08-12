package com.airbnb.android.login.p339ui;

import com.airbnb.android.login.responses.ForgotPasswordResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.EmailForgotPasswordFragment$$Lambda$1 */
final /* synthetic */ class EmailForgotPasswordFragment$$Lambda$1 implements Action1 {
    private final EmailForgotPasswordFragment arg$1;

    private EmailForgotPasswordFragment$$Lambda$1(EmailForgotPasswordFragment emailForgotPasswordFragment) {
        this.arg$1 = emailForgotPasswordFragment;
    }

    public static Action1 lambdaFactory$(EmailForgotPasswordFragment emailForgotPasswordFragment) {
        return new EmailForgotPasswordFragment$$Lambda$1(emailForgotPasswordFragment);
    }

    public void call(Object obj) {
        EmailForgotPasswordFragment.lambda$new$1(this.arg$1, (ForgotPasswordResponse) obj);
    }
}
