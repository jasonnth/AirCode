package com.airbnb.android.core.calendar;

import com.squareup.otto.Bus;

final /* synthetic */ class CalendarStore$$Lambda$1 implements Runnable {
    private final CalendarStore arg$1;
    private final Bus arg$2;

    private CalendarStore$$Lambda$1(CalendarStore calendarStore, Bus bus) {
        this.arg$1 = calendarStore;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(CalendarStore calendarStore, Bus bus) {
        return new CalendarStore$$Lambda$1(calendarStore, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}
