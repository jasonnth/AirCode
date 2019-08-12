package com.airbnb.android.lib.activities;

import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.lib.handlerthread.VerificationsPoller.VerificationsListener;

final /* synthetic */ class CompleteProfileActivity$$Lambda$1 implements VerificationsListener {
    private final CompleteProfileActivity arg$1;

    private CompleteProfileActivity$$Lambda$1(CompleteProfileActivity completeProfileActivity) {
        this.arg$1 = completeProfileActivity;
    }

    public static VerificationsListener lambdaFactory$(CompleteProfileActivity completeProfileActivity) {
        return new CompleteProfileActivity$$Lambda$1(completeProfileActivity);
    }

    public void onVerificationsResponse(VerificationRequirements verificationRequirements) {
        this.arg$1.handleVerificationsResponse(verificationRequirements);
    }
}
