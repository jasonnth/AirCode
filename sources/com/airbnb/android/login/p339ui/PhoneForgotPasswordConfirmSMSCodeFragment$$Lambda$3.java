package com.airbnb.android.login.p339ui;

import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$3 */
final /* synthetic */ class PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$3 implements Action1 {
    private final PhoneForgotPasswordConfirmSMSCodeFragment arg$1;

    private PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$3(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment) {
        this.arg$1 = phoneForgotPasswordConfirmSMSCodeFragment;
    }

    public static Action1 lambdaFactory$(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment) {
        return new PhoneForgotPasswordConfirmSMSCodeFragment$$Lambda$3(phoneForgotPasswordConfirmSMSCodeFragment);
    }

    public void call(Object obj) {
        PhoneForgotPasswordConfirmSMSCodeFragment.lambda$onViewCreated$3(this.arg$1, (String) obj);
    }
}
