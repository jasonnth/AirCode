package com.airbnb.android.login.p339ui;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment$$Lambda$7 */
final /* synthetic */ class EmailResetPasswordFragment$$Lambda$7 implements OnEditorActionListener {
    private final EmailResetPasswordFragment arg$1;

    private EmailResetPasswordFragment$$Lambda$7(EmailResetPasswordFragment emailResetPasswordFragment) {
        this.arg$1 = emailResetPasswordFragment;
    }

    public static OnEditorActionListener lambdaFactory$(EmailResetPasswordFragment emailResetPasswordFragment) {
        return new EmailResetPasswordFragment$$Lambda$7(emailResetPasswordFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return EmailResetPasswordFragment.lambda$initView$2(this.arg$1, textView, i, keyEvent);
    }
}
