package com.airbnb.android.hostcalendar.fragments;

final /* synthetic */ class SingleCalendarFragment$$Lambda$5 implements Runnable {
    private final SingleCalendarFragment arg$1;

    private SingleCalendarFragment$$Lambda$5(SingleCalendarFragment singleCalendarFragment) {
        this.arg$1 = singleCalendarFragment;
    }

    public static Runnable lambdaFactory$(SingleCalendarFragment singleCalendarFragment) {
        return new SingleCalendarFragment$$Lambda$5(singleCalendarFragment);
    }

    public void run() {
        SingleCalendarFragment.lambda$onActivityResult$4(this.arg$1);
    }
}
