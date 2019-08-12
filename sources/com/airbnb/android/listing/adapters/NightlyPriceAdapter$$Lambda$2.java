package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter.NightlyPriceActionListener;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$2 implements OnClickListener {
    private final NightlyPriceActionListener arg$1;

    private NightlyPriceAdapter$$Lambda$2(NightlyPriceActionListener nightlyPriceActionListener) {
        this.arg$1 = nightlyPriceActionListener;
    }

    public static OnClickListener lambdaFactory$(NightlyPriceActionListener nightlyPriceActionListener) {
        return new NightlyPriceAdapter$$Lambda$2(nightlyPriceActionListener);
    }

    public void onClick(View view) {
        this.arg$1.smartPricingTip();
    }
}
