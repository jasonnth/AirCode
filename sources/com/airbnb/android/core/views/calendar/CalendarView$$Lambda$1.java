package com.airbnb.android.core.views.calendar;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CalendarView$$Lambda$1 implements OnClickListener {
    private final CalendarView arg$1;

    private CalendarView$$Lambda$1(CalendarView calendarView) {
        this.arg$1 = calendarView;
    }

    public static OnClickListener lambdaFactory$(CalendarView calendarView) {
        return new CalendarView$$Lambda$1(calendarView);
    }

    public void onClick(View view) {
        CalendarView.lambda$initPopUp$0(this.arg$1, view);
    }
}
