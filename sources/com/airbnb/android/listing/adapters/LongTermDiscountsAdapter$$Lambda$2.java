package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.models.Listing;
import com.airbnb.jitney.event.logging.LongTermPriceDiscountTypes.p139v1.C2376LongTermPriceDiscountTypes;

final /* synthetic */ class LongTermDiscountsAdapter$$Lambda$2 implements OnClickListener {
    private final PricingJitneyLogger arg$1;
    private final Listing arg$2;

    private LongTermDiscountsAdapter$$Lambda$2(PricingJitneyLogger pricingJitneyLogger, Listing listing) {
        this.arg$1 = pricingJitneyLogger;
        this.arg$2 = listing;
    }

    public static OnClickListener lambdaFactory$(PricingJitneyLogger pricingJitneyLogger, Listing listing) {
        return new LongTermDiscountsAdapter$$Lambda$2(pricingJitneyLogger, listing);
    }

    public void onClick(View view) {
        this.arg$1.adoptLongTermDiscountTip(this.arg$2.getAutoWeeklyFactor(), this.arg$2.getAutoWeeklyFactor(), this.arg$2.getWeeklyPriceFactor().getFactorValue().doubleValue(), C2376LongTermPriceDiscountTypes.Weekly);
    }
}
