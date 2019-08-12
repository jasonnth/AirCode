package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment$$Lambda$5 */
final /* synthetic */ class EmailResetPasswordFragment$$Lambda$5 implements OnClickListener {
    private final EmailResetPasswordFragment arg$1;

    private EmailResetPasswordFragment$$Lambda$5(EmailResetPasswordFragment emailResetPasswordFragment) {
        this.arg$1 = emailResetPasswordFragment;
    }

    public static OnClickListener lambdaFactory$(EmailResetPasswordFragment emailResetPasswordFragment) {
        return new EmailResetPasswordFragment$$Lambda$5(emailResetPasswordFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(EmailForgotPasswordFragment.newIntent(this.arg$1.getActivity(), this.arg$1.email));
    }
}
