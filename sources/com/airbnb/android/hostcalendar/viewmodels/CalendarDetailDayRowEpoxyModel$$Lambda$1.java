package com.airbnb.android.hostcalendar.viewmodels;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.hostcalendar.views.CalendarDetailDayRow;

final /* synthetic */ class CalendarDetailDayRowEpoxyModel$$Lambda$1 implements OnClickListener {
    private final CalendarDetailDayRowEpoxyModel arg$1;
    private final CalendarDetailDayRow arg$2;

    private CalendarDetailDayRowEpoxyModel$$Lambda$1(CalendarDetailDayRowEpoxyModel calendarDetailDayRowEpoxyModel, CalendarDetailDayRow calendarDetailDayRow) {
        this.arg$1 = calendarDetailDayRowEpoxyModel;
        this.arg$2 = calendarDetailDayRow;
    }

    public static OnClickListener lambdaFactory$(CalendarDetailDayRowEpoxyModel calendarDetailDayRowEpoxyModel, CalendarDetailDayRow calendarDetailDayRow) {
        return new CalendarDetailDayRowEpoxyModel$$Lambda$1(calendarDetailDayRowEpoxyModel, calendarDetailDayRow);
    }

    public void onClick(View view) {
        this.arg$1.listener.onDayClick(this.arg$2);
    }
}
