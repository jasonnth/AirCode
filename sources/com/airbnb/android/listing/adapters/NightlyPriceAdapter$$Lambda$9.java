package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter.NightlyPriceActionListener;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$9 implements OnClickListener {
    private final NightlyPriceAdapter arg$1;
    private final NightlyPriceActionListener arg$2;

    private NightlyPriceAdapter$$Lambda$9(NightlyPriceAdapter nightlyPriceAdapter, NightlyPriceActionListener nightlyPriceActionListener) {
        this.arg$1 = nightlyPriceAdapter;
        this.arg$2 = nightlyPriceActionListener;
    }

    public static OnClickListener lambdaFactory$(NightlyPriceAdapter nightlyPriceAdapter, NightlyPriceActionListener nightlyPriceActionListener) {
        return new NightlyPriceAdapter$$Lambda$9(nightlyPriceAdapter, nightlyPriceActionListener);
    }

    public void onClick(View view) {
        NightlyPriceAdapter.lambda$new$8(this.arg$1, this.arg$2, view);
    }
}
