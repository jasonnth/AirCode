package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.LoginFragment$$Lambda$1 */
final /* synthetic */ class LoginFragment$$Lambda$1 implements OnClickListener {
    private final LoginFragment arg$1;

    private LoginFragment$$Lambda$1(LoginFragment loginFragment) {
        this.arg$1 = loginFragment;
    }

    public static OnClickListener lambdaFactory$(LoginFragment loginFragment) {
        return new LoginFragment$$Lambda$1(loginFragment);
    }

    public void onClick(View view) {
        LoginFragment.lambda$new$0(this.arg$1, view);
    }
}
