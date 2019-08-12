package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.airdate.AirDate;

final /* synthetic */ class SingleCalendarBaseFragment$$Lambda$2 implements Runnable {
    private final SingleCalendarBaseFragment arg$1;
    private final AirDate arg$2;

    private SingleCalendarBaseFragment$$Lambda$2(SingleCalendarBaseFragment singleCalendarBaseFragment, AirDate airDate) {
        this.arg$1 = singleCalendarBaseFragment;
        this.arg$2 = airDate;
    }

    public static Runnable lambdaFactory$(SingleCalendarBaseFragment singleCalendarBaseFragment, AirDate airDate) {
        return new SingleCalendarBaseFragment$$Lambda$2(singleCalendarBaseFragment, airDate);
    }

    public void run() {
        SingleCalendarBaseFragment.lambda$null$0(this.arg$1, this.arg$2);
    }
}
