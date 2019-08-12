package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.listing.adapters.BasePriceAdapter.OnCurrencyRowClickListener;

final /* synthetic */ class LYSBasePriceFragment$$Lambda$8 implements OnCurrencyRowClickListener {
    private final LYSBasePriceFragment arg$1;

    private LYSBasePriceFragment$$Lambda$8(LYSBasePriceFragment lYSBasePriceFragment) {
        this.arg$1 = lYSBasePriceFragment;
    }

    public static OnCurrencyRowClickListener lambdaFactory$(LYSBasePriceFragment lYSBasePriceFragment) {
        return new LYSBasePriceFragment$$Lambda$8(lYSBasePriceFragment);
    }

    public void onCurrencyRowClicked(String str) {
        LYSBasePriceFragment.lambda$new$8(this.arg$1, str);
    }
}
