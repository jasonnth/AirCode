package com.airbnb.android.listyourspacedls.fragments;

import android.os.Handler;
import com.airbnb.android.listing.adapters.BasePriceAdapter.ValidSettingsListener;

final /* synthetic */ class LYSBasePriceFragment$$Lambda$1 implements ValidSettingsListener {
    private final LYSBasePriceFragment arg$1;

    private LYSBasePriceFragment$$Lambda$1(LYSBasePriceFragment lYSBasePriceFragment) {
        this.arg$1 = lYSBasePriceFragment;
    }

    public static ValidSettingsListener lambdaFactory$(LYSBasePriceFragment lYSBasePriceFragment) {
        return new LYSBasePriceFragment$$Lambda$1(lYSBasePriceFragment);
    }

    public void settingsAreValid(boolean z) {
        new Handler().post(LYSBasePriceFragment$$Lambda$9.lambdaFactory$(this.arg$1, z));
    }
}
