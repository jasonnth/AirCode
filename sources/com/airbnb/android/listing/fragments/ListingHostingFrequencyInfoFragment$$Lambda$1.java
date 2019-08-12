package com.airbnb.android.listing.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ListingHostingFrequencyInfoFragment$$Lambda$1 implements OnClickListener {
    private final ListingHostingFrequencyInfoFragment arg$1;

    private ListingHostingFrequencyInfoFragment$$Lambda$1(ListingHostingFrequencyInfoFragment listingHostingFrequencyInfoFragment) {
        this.arg$1 = listingHostingFrequencyInfoFragment;
    }

    public static OnClickListener lambdaFactory$(ListingHostingFrequencyInfoFragment listingHostingFrequencyInfoFragment) {
        return new ListingHostingFrequencyInfoFragment$$Lambda$1(listingHostingFrequencyInfoFragment);
    }

    public void onClick(View view) {
        this.arg$1.getFragmentManager().popBackStack();
    }
}
