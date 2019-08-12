package com.airbnb.android.lib.china5a.fragments;

import com.airbnb.android.lib.C0880R;
import p032rx.functions.Action1;

final /* synthetic */ class PhoneVerificationFragment$$Lambda$6 implements Action1 {
    private final PhoneVerificationFragment arg$1;

    private PhoneVerificationFragment$$Lambda$6(PhoneVerificationFragment phoneVerificationFragment) {
        this.arg$1 = phoneVerificationFragment;
    }

    public static Action1 lambdaFactory$(PhoneVerificationFragment phoneVerificationFragment) {
        return new PhoneVerificationFragment$$Lambda$6(phoneVerificationFragment);
    }

    public void call(Object obj) {
        this.arg$1.sendCodeBtn.setText(String.format(this.arg$1.getString(C0880R.string.phone_code_verification_resend_countdown), new Object[]{(Integer) obj}));
    }
}
