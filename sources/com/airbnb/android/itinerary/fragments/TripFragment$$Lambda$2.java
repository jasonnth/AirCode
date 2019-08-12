package com.airbnb.android.itinerary.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class TripFragment$$Lambda$2 implements OnClickListener {
    private final TripFragment arg$1;

    private TripFragment$$Lambda$2(TripFragment tripFragment) {
        this.arg$1 = tripFragment;
    }

    public static OnClickListener lambdaFactory$(TripFragment tripFragment) {
        return new TripFragment$$Lambda$2(tripFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().onBackPressed();
    }
}
