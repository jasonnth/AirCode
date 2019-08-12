package com.airbnb.android.hostcalendar.adapters;

import com.airbnb.android.hostcalendar.views.CalendarDetailDayRow;
import com.airbnb.android.hostcalendar.views.CalendarDetailDayRow.CalendarDetailDayClickListener;

final /* synthetic */ class CalendarDetailAdapter$$Lambda$1 implements CalendarDetailDayClickListener {
    private final CalendarDetailAdapter arg$1;

    private CalendarDetailAdapter$$Lambda$1(CalendarDetailAdapter calendarDetailAdapter) {
        this.arg$1 = calendarDetailAdapter;
    }

    public static CalendarDetailDayClickListener lambdaFactory$(CalendarDetailAdapter calendarDetailAdapter) {
        return new CalendarDetailAdapter$$Lambda$1(calendarDetailAdapter);
    }

    public void onDayClick(CalendarDetailDayRow calendarDetailDayRow) {
        CalendarDetailAdapter.lambda$new$0(this.arg$1, calendarDetailDayRow);
    }
}
