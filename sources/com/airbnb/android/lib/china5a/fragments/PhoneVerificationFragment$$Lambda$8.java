package com.airbnb.android.lib.china5a.fragments;

import p032rx.functions.Action0;

final /* synthetic */ class PhoneVerificationFragment$$Lambda$8 implements Action0 {
    private final PhoneVerificationFragment arg$1;

    private PhoneVerificationFragment$$Lambda$8(PhoneVerificationFragment phoneVerificationFragment) {
        this.arg$1 = phoneVerificationFragment;
    }

    public static Action0 lambdaFactory$(PhoneVerificationFragment phoneVerificationFragment) {
        return new PhoneVerificationFragment$$Lambda$8(phoneVerificationFragment);
    }

    public void call() {
        PhoneVerificationFragment.lambda$startCountdown$8(this.arg$1);
    }
}
