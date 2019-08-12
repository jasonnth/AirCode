package com.airbnb.android.login.p339ui;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/* renamed from: com.airbnb.android.login.ui.LoginFragment$$Lambda$4 */
final /* synthetic */ class LoginFragment$$Lambda$4 implements OnEditorActionListener {
    private final LoginFragment arg$1;

    private LoginFragment$$Lambda$4(LoginFragment loginFragment) {
        this.arg$1 = loginFragment;
    }

    public static OnEditorActionListener lambdaFactory$(LoginFragment loginFragment) {
        return new LoginFragment$$Lambda$4(loginFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return LoginFragment.lambda$setupViews$3(this.arg$1, textView, i, keyEvent);
    }
}
