package com.airbnb.android.lib.fragments;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

final /* synthetic */ class AccountVerificationPhoneCodeFragment$$Lambda$3 implements OnKeyListener {
    private final AccountVerificationPhoneCodeFragment arg$1;

    private AccountVerificationPhoneCodeFragment$$Lambda$3(AccountVerificationPhoneCodeFragment accountVerificationPhoneCodeFragment) {
        this.arg$1 = accountVerificationPhoneCodeFragment;
    }

    public static OnKeyListener lambdaFactory$(AccountVerificationPhoneCodeFragment accountVerificationPhoneCodeFragment) {
        return new AccountVerificationPhoneCodeFragment$$Lambda$3(accountVerificationPhoneCodeFragment);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return AccountVerificationPhoneCodeFragment.lambda$onCreateView$2(this.arg$1, view, i, keyEvent);
    }
}
