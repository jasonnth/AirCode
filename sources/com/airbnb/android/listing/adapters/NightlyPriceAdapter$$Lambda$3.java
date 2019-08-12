package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$3 implements Listener {
    private final NightlyPriceAdapter arg$1;

    private NightlyPriceAdapter$$Lambda$3(NightlyPriceAdapter nightlyPriceAdapter) {
        this.arg$1 = nightlyPriceAdapter;
    }

    public static Listener lambdaFactory$(NightlyPriceAdapter nightlyPriceAdapter) {
        return new NightlyPriceAdapter$$Lambda$3(nightlyPriceAdapter);
    }

    public void amountChanged(Integer num) {
        NightlyPriceAdapter.lambda$new$2(this.arg$1, num);
    }
}
