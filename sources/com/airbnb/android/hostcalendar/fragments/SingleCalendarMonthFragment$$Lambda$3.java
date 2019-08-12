package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SingleCalendarMonthFragment$$Lambda$3 implements OnClickListener {
    private final SingleCalendarMonthFragment arg$1;

    private SingleCalendarMonthFragment$$Lambda$3(SingleCalendarMonthFragment singleCalendarMonthFragment) {
        this.arg$1 = singleCalendarMonthFragment;
    }

    public static OnClickListener lambdaFactory$(SingleCalendarMonthFragment singleCalendarMonthFragment) {
        return new SingleCalendarMonthFragment$$Lambda$3(singleCalendarMonthFragment);
    }

    public void onClick(View view) {
        SingleCalendarMonthFragment.lambda$showWarningIfBookedNightsExceedsLimit$2(this.arg$1, view);
    }
}
