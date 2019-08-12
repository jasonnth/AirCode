package com.airbnb.android.lib.fragments;

import android.widget.Toast;
import com.airbnb.android.lib.C0880R;
import p032rx.functions.Action1;

final /* synthetic */ class AccountSettingsFragment$$Lambda$2 implements Action1 {
    private final AccountSettingsFragment arg$1;

    private AccountSettingsFragment$$Lambda$2(AccountSettingsFragment accountSettingsFragment) {
        this.arg$1 = accountSettingsFragment;
    }

    public static Action1 lambdaFactory$(AccountSettingsFragment accountSettingsFragment) {
        return new AccountSettingsFragment$$Lambda$2(accountSettingsFragment);
    }

    public void call(Object obj) {
        Toast.makeText(this.arg$1.getActivity(), C0880R.string.currency_unavailable, 0).show();
    }
}
