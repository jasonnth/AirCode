package com.airbnb.android.lib.china5a.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EmailVerificationFragment$$Lambda$1 implements OnClickListener {
    private final EmailVerificationFragment arg$1;

    private EmailVerificationFragment$$Lambda$1(EmailVerificationFragment emailVerificationFragment) {
        this.arg$1 = emailVerificationFragment;
    }

    public static OnClickListener lambdaFactory$(EmailVerificationFragment emailVerificationFragment) {
        return new EmailVerificationFragment$$Lambda$1(emailVerificationFragment);
    }

    public void onClick(View view) {
        EmailVerificationFragment.lambda$showSnackbar$0(this.arg$1, view);
    }
}
