package com.airbnb.android.itinerary.fragments;

final /* synthetic */ class TimelineFragment$$Lambda$1 implements Runnable {
    private final TimelineFragment arg$1;

    private TimelineFragment$$Lambda$1(TimelineFragment timelineFragment) {
        this.arg$1 = timelineFragment;
    }

    public static Runnable lambdaFactory$(TimelineFragment timelineFragment) {
        return new TimelineFragment$$Lambda$1(timelineFragment);
    }

    public void run() {
        this.arg$1.loadingView.setVisibility(0);
    }
}
