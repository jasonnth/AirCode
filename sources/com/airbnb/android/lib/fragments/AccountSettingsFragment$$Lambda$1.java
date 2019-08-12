package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.CurrenciesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AccountSettingsFragment$$Lambda$1 implements Action1 {
    private final AccountSettingsFragment arg$1;

    private AccountSettingsFragment$$Lambda$1(AccountSettingsFragment accountSettingsFragment) {
        this.arg$1 = accountSettingsFragment;
    }

    public static Action1 lambdaFactory$(AccountSettingsFragment accountSettingsFragment) {
        return new AccountSettingsFragment$$Lambda$1(accountSettingsFragment);
    }

    public void call(Object obj) {
        this.arg$1.updateCurrency(((CurrenciesResponse) obj).currencies);
    }
}
