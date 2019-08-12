package com.airbnb.android.core.fragments;

import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.listeners.OnMapMarkerClickListener;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;

final /* synthetic */ class LocalAttractionsFragment$$Lambda$5 implements OnMapMarkerClickListener {
    private final LocalAttractionsFragment arg$1;

    private LocalAttractionsFragment$$Lambda$5(LocalAttractionsFragment localAttractionsFragment) {
        this.arg$1 = localAttractionsFragment;
    }

    public static OnMapMarkerClickListener lambdaFactory$(LocalAttractionsFragment localAttractionsFragment) {
        return new LocalAttractionsFragment$$Lambda$5(localAttractionsFragment);
    }

    public void onMapMarkerClick(AirMapMarker airMapMarker) {
        this.arg$1.selectAttraction((LocalAttraction) airMapMarker.object());
    }
}
