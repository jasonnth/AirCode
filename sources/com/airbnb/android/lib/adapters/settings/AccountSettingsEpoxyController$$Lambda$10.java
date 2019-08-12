package com.airbnb.android.lib.adapters.settings;

import android.view.View;
import android.view.View.OnLongClickListener;

final /* synthetic */ class AccountSettingsEpoxyController$$Lambda$10 implements OnLongClickListener {
    private final AccountSettingsEpoxyController arg$1;

    private AccountSettingsEpoxyController$$Lambda$10(AccountSettingsEpoxyController accountSettingsEpoxyController) {
        this.arg$1 = accountSettingsEpoxyController;
    }

    public static OnLongClickListener lambdaFactory$(AccountSettingsEpoxyController accountSettingsEpoxyController) {
        return new AccountSettingsEpoxyController$$Lambda$10(accountSettingsEpoxyController);
    }

    public boolean onLongClick(View view) {
        return this.arg$1.listener.onSwitchAccountExplanationClicked();
    }
}
