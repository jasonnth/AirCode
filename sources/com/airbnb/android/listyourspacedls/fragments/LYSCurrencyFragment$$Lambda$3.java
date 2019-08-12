package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSCurrencyFragment$$Lambda$3 implements OnClickListener {
    private final LYSCurrencyFragment arg$1;

    private LYSCurrencyFragment$$Lambda$3(LYSCurrencyFragment lYSCurrencyFragment) {
        this.arg$1 = lYSCurrencyFragment;
    }

    public static OnClickListener lambdaFactory$(LYSCurrencyFragment lYSCurrencyFragment) {
        return new LYSCurrencyFragment$$Lambda$3(lYSCurrencyFragment);
    }

    public void onClick(View view) {
        this.arg$1.makeCurrenciesRequest();
    }
}
