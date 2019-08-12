package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordFragment$$Lambda$1 */
final /* synthetic */ class PhoneForgotPasswordFragment$$Lambda$1 implements OnClickListener {
    private final PhoneForgotPasswordFragment arg$1;

    private PhoneForgotPasswordFragment$$Lambda$1(PhoneForgotPasswordFragment phoneForgotPasswordFragment) {
        this.arg$1 = phoneForgotPasswordFragment;
    }

    public static OnClickListener lambdaFactory$(PhoneForgotPasswordFragment phoneForgotPasswordFragment) {
        return new PhoneForgotPasswordFragment$$Lambda$1(phoneForgotPasswordFragment);
    }

    public void onClick(View view) {
        PhoneForgotPasswordFragment.lambda$new$0(this.arg$1, view);
    }
}
