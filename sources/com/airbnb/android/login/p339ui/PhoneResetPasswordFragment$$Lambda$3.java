package com.airbnb.android.login.p339ui;

import com.airbnb.p027n2.components.SheetInputText.OnShowPasswordToggleListener;

/* renamed from: com.airbnb.android.login.ui.PhoneResetPasswordFragment$$Lambda$3 */
final /* synthetic */ class PhoneResetPasswordFragment$$Lambda$3 implements OnShowPasswordToggleListener {
    private final PhoneResetPasswordFragment arg$1;

    private PhoneResetPasswordFragment$$Lambda$3(PhoneResetPasswordFragment phoneResetPasswordFragment) {
        this.arg$1 = phoneResetPasswordFragment;
    }

    public static OnShowPasswordToggleListener lambdaFactory$(PhoneResetPasswordFragment phoneResetPasswordFragment) {
        return new PhoneResetPasswordFragment$$Lambda$3(phoneResetPasswordFragment);
    }

    public void onToggled(boolean z) {
        PhoneResetPasswordFragment.lambda$onCreateView$3(this.arg$1, z);
    }
}
