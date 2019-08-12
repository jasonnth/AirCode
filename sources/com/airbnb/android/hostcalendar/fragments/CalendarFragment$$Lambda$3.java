package com.airbnb.android.hostcalendar.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class CalendarFragment$$Lambda$3 implements Action1 {
    private final CalendarFragment arg$1;

    private CalendarFragment$$Lambda$3(CalendarFragment calendarFragment) {
        this.arg$1 = calendarFragment;
    }

    public static Action1 lambdaFactory$(CalendarFragment calendarFragment) {
        return new CalendarFragment$$Lambda$3(calendarFragment);
    }

    public void call(Object obj) {
        this.arg$1.showLoader(false);
    }
}
