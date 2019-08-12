package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment$$Lambda$10 */
final /* synthetic */ class EmailResetPasswordFragment$$Lambda$10 implements OnClickListener {
    private final EmailResetPasswordFragment arg$1;

    private EmailResetPasswordFragment$$Lambda$10(EmailResetPasswordFragment emailResetPasswordFragment) {
        this.arg$1 = emailResetPasswordFragment;
    }

    public static OnClickListener lambdaFactory$(EmailResetPasswordFragment emailResetPasswordFragment) {
        return new EmailResetPasswordFragment$$Lambda$10(emailResetPasswordFragment);
    }

    public void onClick(View view) {
        EmailResetPasswordFragment.lambda$isPasswordValidLocalCheck$5(this.arg$1, view);
    }
}
