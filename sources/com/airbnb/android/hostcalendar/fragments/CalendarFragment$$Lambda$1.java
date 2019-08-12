package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CalendarFragment$$Lambda$1 implements Action1 {
    private final CalendarFragment arg$1;

    private CalendarFragment$$Lambda$1(CalendarFragment calendarFragment) {
        this.arg$1 = calendarFragment;
    }

    public static Action1 lambdaFactory$(CalendarFragment calendarFragment) {
        return new CalendarFragment$$Lambda$1(calendarFragment);
    }

    public void call(Object obj) {
        CalendarFragment.lambda$new$0(this.arg$1, (ListingResponse) obj);
    }
}
