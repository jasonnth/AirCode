package com.airbnb.android.lib.activities;

import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.lib.handlerthread.VerificationsPoller.VerificationsListener;

final /* synthetic */ class VerifiedIdActivity$$Lambda$1 implements VerificationsListener {
    private final VerifiedIdActivity arg$1;

    private VerifiedIdActivity$$Lambda$1(VerifiedIdActivity verifiedIdActivity) {
        this.arg$1 = verifiedIdActivity;
    }

    public static VerificationsListener lambdaFactory$(VerifiedIdActivity verifiedIdActivity) {
        return new VerifiedIdActivity$$Lambda$1(verifiedIdActivity);
    }

    public void onVerificationsResponse(VerificationRequirements verificationRequirements) {
        this.arg$1.handleVerificationsResponse(verificationRequirements);
    }
}
