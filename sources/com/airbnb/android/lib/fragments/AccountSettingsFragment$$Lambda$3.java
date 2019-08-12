package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.ExternalAppUtils;

final /* synthetic */ class AccountSettingsFragment$$Lambda$3 implements OnClickListener {
    private final AccountSettingsFragment arg$1;

    private AccountSettingsFragment$$Lambda$3(AccountSettingsFragment accountSettingsFragment) {
        this.arg$1 = accountSettingsFragment;
    }

    public static OnClickListener lambdaFactory$(AccountSettingsFragment accountSettingsFragment) {
        return new AccountSettingsFragment$$Lambda$3(accountSettingsFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(ExternalAppUtils.intentToAppSettings());
    }
}
