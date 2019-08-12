package com.airbnb.android.lib.fragments;

import android.view.View;
import com.airbnb.android.lib.views.LinearListView;
import com.airbnb.android.lib.views.LinearListView.OnItemClickListener;

final /* synthetic */ class AccountVerificationPhonePickerFragment$$Lambda$1 implements OnItemClickListener {
    private final AccountVerificationPhonePickerFragment arg$1;

    private AccountVerificationPhonePickerFragment$$Lambda$1(AccountVerificationPhonePickerFragment accountVerificationPhonePickerFragment) {
        this.arg$1 = accountVerificationPhonePickerFragment;
    }

    public static OnItemClickListener lambdaFactory$(AccountVerificationPhonePickerFragment accountVerificationPhonePickerFragment) {
        return new AccountVerificationPhonePickerFragment$$Lambda$1(accountVerificationPhonePickerFragment);
    }

    public void onItemClick(LinearListView linearListView, View view, int i, long j) {
        AccountVerificationPhonePickerFragment.lambda$onCreateView$0(this.arg$1, linearListView, view, i, j);
    }
}
