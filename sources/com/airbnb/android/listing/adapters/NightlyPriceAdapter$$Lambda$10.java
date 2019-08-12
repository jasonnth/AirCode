package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter.NightlyPriceActionListener;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$10 implements OnClickListener {
    private final NightlyPriceActionListener arg$1;
    private final DynamicPricingControl arg$2;

    private NightlyPriceAdapter$$Lambda$10(NightlyPriceActionListener nightlyPriceActionListener, DynamicPricingControl dynamicPricingControl) {
        this.arg$1 = nightlyPriceActionListener;
        this.arg$2 = dynamicPricingControl;
    }

    public static OnClickListener lambdaFactory$(NightlyPriceActionListener nightlyPriceActionListener, DynamicPricingControl dynamicPricingControl) {
        return new NightlyPriceAdapter$$Lambda$10(nightlyPriceActionListener, dynamicPricingControl);
    }

    public void onClick(View view) {
        this.arg$1.hostingFrequencyInfo(this.arg$2.getDesiredHostingFrequencyVersion());
    }
}
