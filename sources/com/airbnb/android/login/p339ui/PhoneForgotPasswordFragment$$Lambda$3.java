package com.airbnb.android.login.p339ui;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordFragment$$Lambda$3 */
final /* synthetic */ class PhoneForgotPasswordFragment$$Lambda$3 implements Action1 {
    private final PhoneForgotPasswordFragment arg$1;

    private PhoneForgotPasswordFragment$$Lambda$3(PhoneForgotPasswordFragment phoneForgotPasswordFragment) {
        this.arg$1 = phoneForgotPasswordFragment;
    }

    public static Action1 lambdaFactory$(PhoneForgotPasswordFragment phoneForgotPasswordFragment) {
        return new PhoneForgotPasswordFragment$$Lambda$3(phoneForgotPasswordFragment);
    }

    public void call(Object obj) {
        PhoneForgotPasswordFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
