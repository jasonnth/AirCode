package com.airbnb.android.login.p339ui;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$2 */
final /* synthetic */ class PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$2 implements Action1 {
    private final PhoneForgotPasswordConfirmSMSCodeFragment arg$1;

    private PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$2(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment) {
        this.arg$1 = phoneForgotPasswordConfirmSMSCodeFragment;
    }

    public static Action1 lambdaFactory$(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment) {
        return new PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$2(phoneForgotPasswordConfirmSMSCodeFragment);
    }

    public void call(Object obj) {
        PhoneForgotPasswordConfirmSMSCodeFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
