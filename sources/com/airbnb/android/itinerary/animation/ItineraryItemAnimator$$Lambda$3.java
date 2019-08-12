package com.airbnb.android.itinerary.animation;

final /* synthetic */ class ItineraryItemAnimator$$Lambda$3 implements Runnable {
    private final ItineraryItemAnimator arg$1;

    private ItineraryItemAnimator$$Lambda$3(ItineraryItemAnimator itineraryItemAnimator) {
        this.arg$1 = itineraryItemAnimator;
    }

    public static Runnable lambdaFactory$(ItineraryItemAnimator itineraryItemAnimator) {
        return new ItineraryItemAnimator$$Lambda$3(itineraryItemAnimator);
    }

    public void run() {
        this.arg$1.itineraryNavigationController.popBackStack();
    }
}
