package com.airbnb.android.hostcalendar.fragments;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SingleCalendarMonthFragment$$Lambda$2 implements OnClickListener {
    private final SingleCalendarMonthFragment arg$1;
    private final Intent arg$2;

    private SingleCalendarMonthFragment$$Lambda$2(SingleCalendarMonthFragment singleCalendarMonthFragment, Intent intent) {
        this.arg$1 = singleCalendarMonthFragment;
        this.arg$2 = intent;
    }

    public static OnClickListener lambdaFactory$(SingleCalendarMonthFragment singleCalendarMonthFragment, Intent intent) {
        return new SingleCalendarMonthFragment$$Lambda$2(singleCalendarMonthFragment, intent);
    }

    public void onClick(View view) {
        this.arg$1.getContext().startActivity(this.arg$2);
    }
}
