package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class EmailVerificationFragment$$Lambda$1 implements StringTextWatcher {
    private final EmailVerificationFragment arg$1;

    private EmailVerificationFragment$$Lambda$1(EmailVerificationFragment emailVerificationFragment) {
        this.arg$1 = emailVerificationFragment;
    }

    public static StringTextWatcher lambdaFactory$(EmailVerificationFragment emailVerificationFragment) {
        return new EmailVerificationFragment$$Lambda$1(emailVerificationFragment);
    }

    public void textUpdated(String str) {
        this.arg$1.validateEmail(str);
    }
}
