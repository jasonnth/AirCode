package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.sharedcalendar.listeners.OnGridDayClickListener;

final /* synthetic */ class LYSCalendarFragment$$Lambda$1 implements OnGridDayClickListener {
    private final LYSCalendarFragment arg$1;

    private LYSCalendarFragment$$Lambda$1(LYSCalendarFragment lYSCalendarFragment) {
        this.arg$1 = lYSCalendarFragment;
    }

    public static OnGridDayClickListener lambdaFactory$(LYSCalendarFragment lYSCalendarFragment) {
        return new LYSCalendarFragment$$Lambda$1(lYSCalendarFragment);
    }

    public void onDayClicked(CalendarDay calendarDay) {
        LYSCalendarFragment.lambda$new$0(this.arg$1, calendarDay);
    }
}
