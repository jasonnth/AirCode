package com.airbnb.android.lib.china5a.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PhoneVerificationFragment$$Lambda$1 implements OnClickListener {
    private final PhoneVerificationFragment arg$1;

    private PhoneVerificationFragment$$Lambda$1(PhoneVerificationFragment phoneVerificationFragment) {
        this.arg$1 = phoneVerificationFragment;
    }

    public static OnClickListener lambdaFactory$(PhoneVerificationFragment phoneVerificationFragment) {
        return new PhoneVerificationFragment$$Lambda$1(phoneVerificationFragment);
    }

    public void onClick(View view) {
        PhoneVerificationFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
