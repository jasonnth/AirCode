package com.airbnb.android.login.p339ui;

import com.airbnb.android.login.responses.ForgotPasswordResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.PhoneResetPasswordFragment$$Lambda$1 */
final /* synthetic */ class PhoneResetPasswordFragment$$Lambda$1 implements Action1 {
    private final PhoneResetPasswordFragment arg$1;

    private PhoneResetPasswordFragment$$Lambda$1(PhoneResetPasswordFragment phoneResetPasswordFragment) {
        this.arg$1 = phoneResetPasswordFragment;
    }

    public static Action1 lambdaFactory$(PhoneResetPasswordFragment phoneResetPasswordFragment) {
        return new PhoneResetPasswordFragment$$Lambda$1(phoneResetPasswordFragment);
    }

    public void call(Object obj) {
        PhoneResetPasswordFragment.lambda$new$1(this.arg$1, (ForgotPasswordResponse) obj);
    }
}
