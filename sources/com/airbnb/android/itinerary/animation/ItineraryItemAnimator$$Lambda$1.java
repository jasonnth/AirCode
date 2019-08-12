package com.airbnb.android.itinerary.animation;

import android.view.View;

final /* synthetic */ class ItineraryItemAnimator$$Lambda$1 implements Runnable {
    private final ItineraryItemAnimator arg$1;
    private final boolean arg$2;
    private final View arg$3;

    private ItineraryItemAnimator$$Lambda$1(ItineraryItemAnimator itineraryItemAnimator, boolean z, View view) {
        this.arg$1 = itineraryItemAnimator;
        this.arg$2 = z;
        this.arg$3 = view;
    }

    public static Runnable lambdaFactory$(ItineraryItemAnimator itineraryItemAnimator, boolean z, View view) {
        return new ItineraryItemAnimator$$Lambda$1(itineraryItemAnimator, z, view);
    }

    public void run() {
        ItineraryItemAnimator.lambda$startCustomAnimation$1(this.arg$1, this.arg$2, this.arg$3);
    }
}
