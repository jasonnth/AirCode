package com.airbnb.android.lib.adapters.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountSettingsEpoxyController$$Lambda$6 implements OnClickListener {
    private final AccountSettingsEpoxyController arg$1;

    private AccountSettingsEpoxyController$$Lambda$6(AccountSettingsEpoxyController accountSettingsEpoxyController) {
        this.arg$1 = accountSettingsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(AccountSettingsEpoxyController accountSettingsEpoxyController) {
        return new AccountSettingsEpoxyController$$Lambda$6(accountSettingsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onNotificationSettingsClicked();
    }
}