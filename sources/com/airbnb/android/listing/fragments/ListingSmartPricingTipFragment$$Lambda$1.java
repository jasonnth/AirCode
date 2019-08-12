package com.airbnb.android.listing.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ListingSmartPricingTipFragment$$Lambda$1 implements OnClickListener {
    private final ListingSmartPricingTipFragment arg$1;

    private ListingSmartPricingTipFragment$$Lambda$1(ListingSmartPricingTipFragment listingSmartPricingTipFragment) {
        this.arg$1 = listingSmartPricingTipFragment;
    }

    public static OnClickListener lambdaFactory$(ListingSmartPricingTipFragment listingSmartPricingTipFragment) {
        return new ListingSmartPricingTipFragment$$Lambda$1(listingSmartPricingTipFragment);
    }

    public void onClick(View view) {
        this.arg$1.getFragmentManager().popBackStack();
    }
}
