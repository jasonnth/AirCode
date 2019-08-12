package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class BasePriceAdapter$$Lambda$1 implements Listener {
    private final BasePriceAdapter arg$1;

    private BasePriceAdapter$$Lambda$1(BasePriceAdapter basePriceAdapter) {
        this.arg$1 = basePriceAdapter;
    }

    public static Listener lambdaFactory$(BasePriceAdapter basePriceAdapter) {
        return new BasePriceAdapter$$Lambda$1(basePriceAdapter);
    }

    public void amountChanged(Integer num) {
        this.arg$1.storeBasePrice(num);
    }
}
