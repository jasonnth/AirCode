package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.listyourspacedls.adapters.RentHistoryAdapter.Listener;

final /* synthetic */ class LYSRentHistoryFragment$$Lambda$5 implements Listener {
    private final LYSRentHistoryFragment arg$1;

    private LYSRentHistoryFragment$$Lambda$5(LYSRentHistoryFragment lYSRentHistoryFragment) {
        this.arg$1 = lYSRentHistoryFragment;
    }

    public static Listener lambdaFactory$(LYSRentHistoryFragment lYSRentHistoryFragment) {
        return new LYSRentHistoryFragment$$Lambda$5(lYSRentHistoryFragment);
    }

    public void onValueSelected(ListingPersonaAnswer listingPersonaAnswer) {
        this.arg$1.onRentHistoryTypeSelected(listingPersonaAnswer);
    }
}
