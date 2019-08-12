package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CalendarUpdateNotesFragment$$Lambda$1 implements OnClickListener {
    private final CalendarUpdateNotesFragment arg$1;

    private CalendarUpdateNotesFragment$$Lambda$1(CalendarUpdateNotesFragment calendarUpdateNotesFragment) {
        this.arg$1 = calendarUpdateNotesFragment;
    }

    public static OnClickListener lambdaFactory$(CalendarUpdateNotesFragment calendarUpdateNotesFragment) {
        return new CalendarUpdateNotesFragment$$Lambda$1(calendarUpdateNotesFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().onBackPressed();
    }
}
