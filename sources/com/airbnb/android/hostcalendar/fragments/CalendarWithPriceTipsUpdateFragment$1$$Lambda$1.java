package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CalendarWithPriceTipsUpdateFragment$1$$Lambda$1 implements OnClickListener {
    private final C64361 arg$1;

    private CalendarWithPriceTipsUpdateFragment$1$$Lambda$1(C64361 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C64361 r1) {
        return new CalendarWithPriceTipsUpdateFragment$1$$Lambda$1(r1);
    }

    public void onClick(View view) {
        CalendarWithPriceTipsUpdateFragment.this.save();
    }
}
