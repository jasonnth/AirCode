package com.airbnb.android.lib.fragments.verifications;

import com.airbnb.android.core.responses.VerificationsResponse;
import com.airbnb.android.lib.utils.VerificationsPoller.VerificationsListener;

final /* synthetic */ class PhoneVerificationFragment$$Lambda$8 implements VerificationsListener {
    private final PhoneVerificationFragment arg$1;

    private PhoneVerificationFragment$$Lambda$8(PhoneVerificationFragment phoneVerificationFragment) {
        this.arg$1 = phoneVerificationFragment;
    }

    public static VerificationsListener lambdaFactory$(PhoneVerificationFragment phoneVerificationFragment) {
        return new PhoneVerificationFragment$$Lambda$8(phoneVerificationFragment);
    }

    public void onReceivedVerifications(VerificationsResponse verificationsResponse) {
        PhoneVerificationFragment.lambda$setUpVerificationsListener$7(this.arg$1, verificationsResponse);
    }
}
