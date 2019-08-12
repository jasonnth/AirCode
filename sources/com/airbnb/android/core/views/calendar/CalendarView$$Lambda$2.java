package com.airbnb.android.core.views.calendar;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CalendarView$$Lambda$2 implements OnClickListener {
    private final CalendarView arg$1;

    private CalendarView$$Lambda$2(CalendarView calendarView) {
        this.arg$1 = calendarView;
    }

    public static OnClickListener lambdaFactory$(CalendarView calendarView) {
        return new CalendarView$$Lambda$2(calendarView);
    }

    public void onClick(View view) {
        this.arg$1.onApplyClicked();
    }
}
