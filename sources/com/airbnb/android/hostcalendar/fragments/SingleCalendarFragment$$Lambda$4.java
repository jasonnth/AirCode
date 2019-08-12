package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SingleCalendarFragment$$Lambda$4 implements OnClickListener {
    private final SingleCalendarFragment arg$1;

    private SingleCalendarFragment$$Lambda$4(SingleCalendarFragment singleCalendarFragment) {
        this.arg$1 = singleCalendarFragment;
    }

    public static OnClickListener lambdaFactory$(SingleCalendarFragment singleCalendarFragment) {
        return new SingleCalendarFragment$$Lambda$4(singleCalendarFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().onBackPressed();
    }
}
