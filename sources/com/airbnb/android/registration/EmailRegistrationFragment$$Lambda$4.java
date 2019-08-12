package com.airbnb.android.registration;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class EmailRegistrationFragment$$Lambda$4 implements OnEditorActionListener {
    private final EmailRegistrationFragment arg$1;

    private EmailRegistrationFragment$$Lambda$4(EmailRegistrationFragment emailRegistrationFragment) {
        this.arg$1 = emailRegistrationFragment;
    }

    public static OnEditorActionListener lambdaFactory$(EmailRegistrationFragment emailRegistrationFragment) {
        return new EmailRegistrationFragment$$Lambda$4(emailRegistrationFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return EmailRegistrationFragment.lambda$setupViews$3(this.arg$1, textView, i, keyEvent);
    }
}
