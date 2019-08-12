package com.airbnb.android.login.p339ui;

import com.airbnb.p027n2.components.SheetInputText.OnShowPasswordToggleListener;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment$$Lambda$9 */
final /* synthetic */ class EmailResetPasswordFragment$$Lambda$9 implements OnShowPasswordToggleListener {
    private final EmailResetPasswordFragment arg$1;

    private EmailResetPasswordFragment$$Lambda$9(EmailResetPasswordFragment emailResetPasswordFragment) {
        this.arg$1 = emailResetPasswordFragment;
    }

    public static OnShowPasswordToggleListener lambdaFactory$(EmailResetPasswordFragment emailResetPasswordFragment) {
        return new EmailResetPasswordFragment$$Lambda$9(emailResetPasswordFragment);
    }

    public void onToggled(boolean z) {
        EmailResetPasswordFragment.lambda$initView$4(this.arg$1, z);
    }
}
