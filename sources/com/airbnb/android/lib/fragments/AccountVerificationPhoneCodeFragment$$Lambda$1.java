package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity;

final /* synthetic */ class AccountVerificationPhoneCodeFragment$$Lambda$1 implements OnClickListener {
    private final AccountVerificationPhoneCodeFragment arg$1;

    private AccountVerificationPhoneCodeFragment$$Lambda$1(AccountVerificationPhoneCodeFragment accountVerificationPhoneCodeFragment) {
        this.arg$1 = accountVerificationPhoneCodeFragment;
    }

    public static OnClickListener lambdaFactory$(AccountVerificationPhoneCodeFragment accountVerificationPhoneCodeFragment) {
        return new AccountVerificationPhoneCodeFragment$$Lambda$1(accountVerificationPhoneCodeFragment);
    }

    public void onClick(View view) {
        ((OldAccountVerificationActivity) this.arg$1.getActivity()).onBackPressed();
    }
}
