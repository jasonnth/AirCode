package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BasePriceAdapter$$Lambda$3 implements OnClickListener {
    private final BasePriceAdapter arg$1;

    private BasePriceAdapter$$Lambda$3(BasePriceAdapter basePriceAdapter) {
        this.arg$1 = basePriceAdapter;
    }

    public static OnClickListener lambdaFactory$(BasePriceAdapter basePriceAdapter) {
        return new BasePriceAdapter$$Lambda$3(basePriceAdapter);
    }

    public void onClick(View view) {
        this.arg$1.currencyRowClickListener.onCurrencyRowClicked((String) this.arg$1.currencyInputRow.input());
    }
}
