package com.airbnb.android.login.p339ui;

import com.airbnb.p027n2.components.SheetInputText.OnShowPasswordToggleListener;

/* renamed from: com.airbnb.android.login.ui.LoginFragment$$Lambda$3 */
final /* synthetic */ class LoginFragment$$Lambda$3 implements OnShowPasswordToggleListener {
    private final LoginFragment arg$1;

    private LoginFragment$$Lambda$3(LoginFragment loginFragment) {
        this.arg$1 = loginFragment;
    }

    public static OnShowPasswordToggleListener lambdaFactory$(LoginFragment loginFragment) {
        return new LoginFragment$$Lambda$3(loginFragment);
    }

    public void onToggled(boolean z) {
        LoginFragment.lambda$setupViews$2(this.arg$1, z);
    }
}
