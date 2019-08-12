package com.airbnb.android.lib.fragments.verifications;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PhoneVerificationFragment$$Lambda$7 implements OnClickListener {
    private final PhoneVerificationFragment arg$1;

    private PhoneVerificationFragment$$Lambda$7(PhoneVerificationFragment phoneVerificationFragment) {
        this.arg$1 = phoneVerificationFragment;
    }

    public static OnClickListener lambdaFactory$(PhoneVerificationFragment phoneVerificationFragment) {
        return new PhoneVerificationFragment$$Lambda$7(phoneVerificationFragment);
    }

    public void onClick(View view) {
        PhoneVerificationFragment.lambda$setUpCallingCodeButton$0(this.arg$1, view);
    }
}
