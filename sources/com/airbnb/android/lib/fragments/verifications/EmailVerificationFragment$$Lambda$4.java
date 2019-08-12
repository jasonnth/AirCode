package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.android.core.responses.VerificationsResponse;
import com.airbnb.android.lib.utils.VerificationsPoller.VerificationsListener;

final /* synthetic */ class EmailVerificationFragment$$Lambda$4 implements VerificationsListener {
    private final EmailVerificationFragment arg$1;

    private EmailVerificationFragment$$Lambda$4(EmailVerificationFragment emailVerificationFragment) {
        this.arg$1 = emailVerificationFragment;
    }

    public static VerificationsListener lambdaFactory$(EmailVerificationFragment emailVerificationFragment) {
        return new EmailVerificationFragment$$Lambda$4(emailVerificationFragment);
    }

    public void onReceivedVerifications(VerificationsResponse verificationsResponse) {
        EmailVerificationFragment.lambda$setUpVerificationsListener$2(this.arg$1, verificationsResponse);
    }
}
