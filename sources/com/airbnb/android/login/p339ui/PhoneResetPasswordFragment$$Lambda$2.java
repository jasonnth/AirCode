package com.airbnb.android.login.p339ui;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.PhoneResetPasswordFragment$$Lambda$2 */
final /* synthetic */ class PhoneResetPasswordFragment$$Lambda$2 implements Action1 {
    private final PhoneResetPasswordFragment arg$1;

    private PhoneResetPasswordFragment$$Lambda$2(PhoneResetPasswordFragment phoneResetPasswordFragment) {
        this.arg$1 = phoneResetPasswordFragment;
    }

    public static Action1 lambdaFactory$(PhoneResetPasswordFragment phoneResetPasswordFragment) {
        return new PhoneResetPasswordFragment$$Lambda$2(phoneResetPasswordFragment);
    }

    public void call(Object obj) {
        PhoneResetPasswordFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
