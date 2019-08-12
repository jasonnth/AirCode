package com.airbnb.android.itinerary.animation;

import android.support.p002v7.widget.RecyclerView.ViewHolder;

final /* synthetic */ class ItineraryItemAnimator$$Lambda$2 implements Runnable {
    private final ItineraryItemAnimator arg$1;
    private final ViewHolder arg$2;

    private ItineraryItemAnimator$$Lambda$2(ItineraryItemAnimator itineraryItemAnimator, ViewHolder viewHolder) {
        this.arg$1 = itineraryItemAnimator;
        this.arg$2 = viewHolder;
    }

    public static Runnable lambdaFactory$(ItineraryItemAnimator itineraryItemAnimator, ViewHolder viewHolder) {
        return new ItineraryItemAnimator$$Lambda$2(itineraryItemAnimator, viewHolder);
    }

    public void run() {
        ItineraryItemAnimator.lambda$startCustomAnimation$2(this.arg$1, this.arg$2);
    }
}
