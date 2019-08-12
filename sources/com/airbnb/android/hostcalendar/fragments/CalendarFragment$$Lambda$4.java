package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CalendarFragment$$Lambda$4 implements OnClickListener {
    private final CalendarFragment arg$1;

    private CalendarFragment$$Lambda$4(CalendarFragment calendarFragment) {
        this.arg$1 = calendarFragment;
    }

    public static OnClickListener lambdaFactory$(CalendarFragment calendarFragment) {
        return new CalendarFragment$$Lambda$4(calendarFragment);
    }

    public void onClick(View view) {
        this.arg$1.retryLoading();
    }
}
