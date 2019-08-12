package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.listyourspacedls.adapters.LYSHostingFrequencyAdapter.Listener;

final /* synthetic */ class LYSHostingFrequencyFragment$$Lambda$7 implements Listener {
    private final LYSHostingFrequencyFragment arg$1;

    private LYSHostingFrequencyFragment$$Lambda$7(LYSHostingFrequencyFragment lYSHostingFrequencyFragment) {
        this.arg$1 = lYSHostingFrequencyFragment;
    }

    public static Listener lambdaFactory$(LYSHostingFrequencyFragment lYSHostingFrequencyFragment) {
        return new LYSHostingFrequencyFragment$$Lambda$7(lYSHostingFrequencyFragment);
    }

    public void onValueSelected(ListingPersonaAnswer listingPersonaAnswer) {
        this.arg$1.onFrequencySelected(listingPersonaAnswer);
    }
}
