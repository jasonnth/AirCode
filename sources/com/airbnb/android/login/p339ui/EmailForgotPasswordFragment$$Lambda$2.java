package com.airbnb.android.login.p339ui;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.EmailForgotPasswordFragment$$Lambda$2 */
final /* synthetic */ class EmailForgotPasswordFragment$$Lambda$2 implements Action1 {
    private final EmailForgotPasswordFragment arg$1;

    private EmailForgotPasswordFragment$$Lambda$2(EmailForgotPasswordFragment emailForgotPasswordFragment) {
        this.arg$1 = emailForgotPasswordFragment;
    }

    public static Action1 lambdaFactory$(EmailForgotPasswordFragment emailForgotPasswordFragment) {
        return new EmailForgotPasswordFragment$$Lambda$2(emailForgotPasswordFragment);
    }

    public void call(Object obj) {
        EmailForgotPasswordFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
