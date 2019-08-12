package com.airbnb.android.login.p339ui;

import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordFragment$$Lambda$4 */
final /* synthetic */ class PhoneForgotPasswordFragment$$Lambda$4 implements Action1 {
    private final PhoneForgotPasswordFragment arg$1;

    private PhoneForgotPasswordFragment$$Lambda$4(PhoneForgotPasswordFragment phoneForgotPasswordFragment) {
        this.arg$1 = phoneForgotPasswordFragment;
    }

    public static Action1 lambdaFactory$(PhoneForgotPasswordFragment phoneForgotPasswordFragment) {
        return new PhoneForgotPasswordFragment$$Lambda$4(phoneForgotPasswordFragment);
    }

    public void call(Object obj) {
        this.arg$1.doSumbmit();
    }
}
