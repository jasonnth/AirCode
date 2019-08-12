package com.airbnb.android.login.p339ui;

import com.airbnb.android.login.responses.ForgotPasswordResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordFragment$$Lambda$2 */
final /* synthetic */ class PhoneForgotPasswordFragment$$Lambda$2 implements Action1 {
    private final PhoneForgotPasswordFragment arg$1;

    private PhoneForgotPasswordFragment$$Lambda$2(PhoneForgotPasswordFragment phoneForgotPasswordFragment) {
        this.arg$1 = phoneForgotPasswordFragment;
    }

    public static Action1 lambdaFactory$(PhoneForgotPasswordFragment phoneForgotPasswordFragment) {
        return new PhoneForgotPasswordFragment$$Lambda$2(phoneForgotPasswordFragment);
    }

    public void call(Object obj) {
        PhoneForgotPasswordFragment.lambda$new$2(this.arg$1, (ForgotPasswordResponse) obj);
    }
}
