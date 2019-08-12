package com.airbnb.android.itinerary.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ItineraryParentFragment$$Lambda$1 implements OnClickListener {
    private final ItineraryParentFragment arg$1;

    private ItineraryParentFragment$$Lambda$1(ItineraryParentFragment itineraryParentFragment) {
        this.arg$1 = itineraryParentFragment;
    }

    public static OnClickListener lambdaFactory$(ItineraryParentFragment itineraryParentFragment) {
        return new ItineraryParentFragment$$Lambda$1(itineraryParentFragment);
    }

    public void onClick(View view) {
        this.arg$1.dimissErrorSnackbar();
    }
}
