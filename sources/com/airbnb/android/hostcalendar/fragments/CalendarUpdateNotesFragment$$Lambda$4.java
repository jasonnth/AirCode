package com.airbnb.android.hostcalendar.fragments;

final /* synthetic */ class CalendarUpdateNotesFragment$$Lambda$4 implements Runnable {
    private final CalendarUpdateNotesFragment arg$1;

    private CalendarUpdateNotesFragment$$Lambda$4(CalendarUpdateNotesFragment calendarUpdateNotesFragment) {
        this.arg$1 = calendarUpdateNotesFragment;
    }

    public static Runnable lambdaFactory$(CalendarUpdateNotesFragment calendarUpdateNotesFragment) {
        return new CalendarUpdateNotesFragment$$Lambda$4(calendarUpdateNotesFragment);
    }

    public void run() {
        CalendarUpdateNotesFragment.lambda$onCreateView$1(this.arg$1);
    }
}
