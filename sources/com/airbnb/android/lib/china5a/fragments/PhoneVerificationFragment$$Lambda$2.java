package com.airbnb.android.lib.china5a.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class PhoneVerificationFragment$$Lambda$2 implements Action1 {
    private final PhoneVerificationFragment arg$1;

    private PhoneVerificationFragment$$Lambda$2(PhoneVerificationFragment phoneVerificationFragment) {
        this.arg$1 = phoneVerificationFragment;
    }

    public static Action1 lambdaFactory$(PhoneVerificationFragment phoneVerificationFragment) {
        return new PhoneVerificationFragment$$Lambda$2(phoneVerificationFragment);
    }

    public void call(Object obj) {
        PhoneVerificationFragment.lambda$startListeningToSMS$2(this.arg$1, (String) obj);
    }
}
