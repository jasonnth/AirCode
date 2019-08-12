package com.airbnb.android.lib.china5a.fragments;

import p032rx.functions.Func1;

final /* synthetic */ class PhoneVerificationFragment$$Lambda$4 implements Func1 {
    private final PhoneVerificationFragment arg$1;

    private PhoneVerificationFragment$$Lambda$4(PhoneVerificationFragment phoneVerificationFragment) {
        this.arg$1 = phoneVerificationFragment;
    }

    public static Func1 lambdaFactory$(PhoneVerificationFragment phoneVerificationFragment) {
        return new PhoneVerificationFragment$$Lambda$4(phoneVerificationFragment);
    }

    public Object call(Object obj) {
        return Integer.valueOf(Math.round(((float) ((this.arg$1.lastRequestTime + 60000) - System.currentTimeMillis())) / 1000.0f));
    }
}
