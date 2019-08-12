package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountVerificationPhoneCodeFragment$$Lambda$2 implements OnClickListener {
    private final AccountVerificationPhoneCodeFragment arg$1;

    private AccountVerificationPhoneCodeFragment$$Lambda$2(AccountVerificationPhoneCodeFragment accountVerificationPhoneCodeFragment) {
        this.arg$1 = accountVerificationPhoneCodeFragment;
    }

    public static OnClickListener lambdaFactory$(AccountVerificationPhoneCodeFragment accountVerificationPhoneCodeFragment) {
        return new AccountVerificationPhoneCodeFragment$$Lambda$2(accountVerificationPhoneCodeFragment);
    }

    public void onClick(View view) {
        AccountVerificationPhoneCodeFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
