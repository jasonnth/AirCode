package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.sharedcalendar.listeners.InfiniteScrollListener;

final /* synthetic */ class SingleCalendarBaseFragment$$Lambda$1 implements InfiniteScrollListener {
    private final SingleCalendarBaseFragment arg$1;

    private SingleCalendarBaseFragment$$Lambda$1(SingleCalendarBaseFragment singleCalendarBaseFragment) {
        this.arg$1 = singleCalendarBaseFragment;
    }

    public static InfiniteScrollListener lambdaFactory$(SingleCalendarBaseFragment singleCalendarBaseFragment) {
        return new SingleCalendarBaseFragment$$Lambda$1(singleCalendarBaseFragment);
    }

    public void scrollForward(AirDate airDate) {
        this.arg$1.handler.post(SingleCalendarBaseFragment$$Lambda$2.lambdaFactory$(this.arg$1, airDate));
    }
}
