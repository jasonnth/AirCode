package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CalendarUpdateNotesFragment$1$$Lambda$1 implements OnClickListener {
    private final C64351 arg$1;

    private CalendarUpdateNotesFragment$1$$Lambda$1(C64351 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C64351 r1) {
        return new CalendarUpdateNotesFragment$1$$Lambda$1(r1);
    }

    public void onClick(View view) {
        CalendarUpdateNotesFragment.this.save();
    }
}
