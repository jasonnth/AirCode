package com.airbnb.android.login.p339ui;

import com.airbnb.p027n2.components.SheetInputText.OnShowPasswordToggleListener;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment$$Lambda$8 */
final /* synthetic */ class EmailResetPasswordFragment$$Lambda$8 implements OnShowPasswordToggleListener {
    private final EmailResetPasswordFragment arg$1;

    private EmailResetPasswordFragment$$Lambda$8(EmailResetPasswordFragment emailResetPasswordFragment) {
        this.arg$1 = emailResetPasswordFragment;
    }

    public static OnShowPasswordToggleListener lambdaFactory$(EmailResetPasswordFragment emailResetPasswordFragment) {
        return new EmailResetPasswordFragment$$Lambda$8(emailResetPasswordFragment);
    }

    public void onToggled(boolean z) {
        EmailResetPasswordFragment.lambda$initView$3(this.arg$1, z);
    }
}
