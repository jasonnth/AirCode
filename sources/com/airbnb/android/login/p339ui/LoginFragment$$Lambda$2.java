package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.LoginFragment$$Lambda$2 */
final /* synthetic */ class LoginFragment$$Lambda$2 implements OnClickListener {
    private final LoginFragment arg$1;

    private LoginFragment$$Lambda$2(LoginFragment loginFragment) {
        this.arg$1 = loginFragment;
    }

    public static OnClickListener lambdaFactory$(LoginFragment loginFragment) {
        return new LoginFragment$$Lambda$2(loginFragment);
    }

    public void onClick(View view) {
        this.arg$1.editPassword.showPassword(true);
    }
}
