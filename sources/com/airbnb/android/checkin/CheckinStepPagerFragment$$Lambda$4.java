package com.airbnb.android.checkin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CheckinStepPagerFragment$$Lambda$4 implements OnClickListener {
    private final CheckinStepPagerFragment arg$1;

    private CheckinStepPagerFragment$$Lambda$4(CheckinStepPagerFragment checkinStepPagerFragment) {
        this.arg$1 = checkinStepPagerFragment;
    }

    public static OnClickListener lambdaFactory$(CheckinStepPagerFragment checkinStepPagerFragment) {
        return new CheckinStepPagerFragment$$Lambda$4(checkinStepPagerFragment);
    }

    public void onClick(View view) {
        this.arg$1.sendCheckInNotification();
    }
}
