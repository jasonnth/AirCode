package com.airbnb.android.hostcalendar.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class CalendarDetailDayRow$$Lambda$2 implements AnimatorUpdateListener {
    private final CalendarDetailDayRow arg$1;

    private CalendarDetailDayRow$$Lambda$2(CalendarDetailDayRow calendarDetailDayRow) {
        this.arg$1 = calendarDetailDayRow;
    }

    public static AnimatorUpdateListener lambdaFactory$(CalendarDetailDayRow calendarDetailDayRow) {
        return new CalendarDetailDayRow$$Lambda$2(calendarDetailDayRow);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        CalendarDetailDayRow.lambda$initAnimators$1(this.arg$1, valueAnimator);
    }
}
