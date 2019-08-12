package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSCalendarFragment$2$$Lambda$1 implements OnClickListener {
    private final C72762 arg$1;

    private LYSCalendarFragment$2$$Lambda$1(C72762 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C72762 r1) {
        return new LYSCalendarFragment$2$$Lambda$1(r1);
    }

    public void onClick(View view) {
        LYSCalendarFragment.this.getOrReloadCalendar();
    }
}
