package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;

final /* synthetic */ class SingleCalendarDetailFragment$$Lambda$1 implements OnClickListener {
    private final SingleCalendarDetailFragment arg$1;

    private SingleCalendarDetailFragment$$Lambda$1(SingleCalendarDetailFragment singleCalendarDetailFragment) {
        this.arg$1 = singleCalendarDetailFragment;
    }

    public static OnClickListener lambdaFactory$(SingleCalendarDetailFragment singleCalendarDetailFragment) {
        return new SingleCalendarDetailFragment$$Lambda$1(singleCalendarDetailFragment);
    }

    public void onClick(View view) {
        this.arg$1.scrollToTargetDate(AirDate.today());
    }
}
