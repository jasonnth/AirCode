package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SingleCalendarBaseFragment$1$$Lambda$1 implements OnClickListener {
    private final C64401 arg$1;

    private SingleCalendarBaseFragment$1$$Lambda$1(C64401 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C64401 r1) {
        return new SingleCalendarBaseFragment$1$$Lambda$1(r1);
    }

    public void onClick(View view) {
        SingleCalendarBaseFragment.this.reloadCalendar(SingleCalendarBaseFragment.this.lastScrollEndDate);
    }
}
