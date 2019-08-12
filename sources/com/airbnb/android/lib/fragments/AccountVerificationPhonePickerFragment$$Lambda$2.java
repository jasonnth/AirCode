package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity;

final /* synthetic */ class AccountVerificationPhonePickerFragment$$Lambda$2 implements OnClickListener {
    private final AccountVerificationPhonePickerFragment arg$1;
    private final OldAccountVerificationActivity arg$2;

    private AccountVerificationPhonePickerFragment$$Lambda$2(AccountVerificationPhonePickerFragment accountVerificationPhonePickerFragment, OldAccountVerificationActivity oldAccountVerificationActivity) {
        this.arg$1 = accountVerificationPhonePickerFragment;
        this.arg$2 = oldAccountVerificationActivity;
    }

    public static OnClickListener lambdaFactory$(AccountVerificationPhonePickerFragment accountVerificationPhonePickerFragment, OldAccountVerificationActivity oldAccountVerificationActivity) {
        return new AccountVerificationPhonePickerFragment$$Lambda$2(accountVerificationPhonePickerFragment, oldAccountVerificationActivity);
    }

    public void onClick(View view) {
        AccountVerificationPhonePickerFragment.lambda$onCreateView$1(this.arg$1, this.arg$2, view);
    }
}
