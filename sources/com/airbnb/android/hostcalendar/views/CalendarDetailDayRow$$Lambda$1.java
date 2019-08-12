package com.airbnb.android.hostcalendar.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class CalendarDetailDayRow$$Lambda$1 implements AnimatorUpdateListener {
    private final CalendarDetailDayRow arg$1;

    private CalendarDetailDayRow$$Lambda$1(CalendarDetailDayRow calendarDetailDayRow) {
        this.arg$1 = calendarDetailDayRow;
    }

    public static AnimatorUpdateListener lambdaFactory$(CalendarDetailDayRow calendarDetailDayRow) {
        return new CalendarDetailDayRow$$Lambda$1(calendarDetailDayRow);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        CalendarDetailDayRow.lambda$initAnimators$0(this.arg$1, valueAnimator);
    }
}
