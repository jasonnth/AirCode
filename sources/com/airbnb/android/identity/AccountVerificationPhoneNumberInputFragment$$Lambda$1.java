package com.airbnb.android.identity;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountVerificationPhoneNumberInputFragment$$Lambda$1 implements OnClickListener {
    private final AccountVerificationPhoneNumberInputFragment arg$1;

    private AccountVerificationPhoneNumberInputFragment$$Lambda$1(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment) {
        this.arg$1 = accountVerificationPhoneNumberInputFragment;
    }

    public static OnClickListener lambdaFactory$(AccountVerificationPhoneNumberInputFragment accountVerificationPhoneNumberInputFragment) {
        return new AccountVerificationPhoneNumberInputFragment$$Lambda$1(accountVerificationPhoneNumberInputFragment);
    }

    public void onClick(View view) {
        AccountVerificationPhoneNumberInputFragment.lambda$new$0(this.arg$1, view);
    }
}
