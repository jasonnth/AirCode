package com.airbnb.android.lib.activities;

import com.airbnb.android.core.models.Currency;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.lib.fragments.CurrencySelectorDialogFragment.OnCurrencySelectedListener;

final /* synthetic */ class HomeActivity$$Lambda$10 implements OnCurrencySelectedListener {
    private final HomeActivity arg$1;

    private HomeActivity$$Lambda$10(HomeActivity homeActivity) {
        this.arg$1 = homeActivity;
    }

    public static OnCurrencySelectedListener lambdaFactory$(HomeActivity homeActivity) {
        return new HomeActivity$$Lambda$10(homeActivity);
    }

    public void onCurrencySelected(Currency currency) {
        ((CurrencyFormatter) this.arg$1.currencyHelper.get()).setCurrency(currency.getCode(), true, true);
    }
}
