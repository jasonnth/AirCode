package com.airbnb.android.checkin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CheckinStepPagerFragment$$Lambda$3 implements OnClickListener {
    private final CheckinStepPagerFragment arg$1;

    private CheckinStepPagerFragment$$Lambda$3(CheckinStepPagerFragment checkinStepPagerFragment) {
        this.arg$1 = checkinStepPagerFragment;
    }

    public static OnClickListener lambdaFactory$(CheckinStepPagerFragment checkinStepPagerFragment) {
        return new CheckinStepPagerFragment$$Lambda$3(checkinStepPagerFragment);
    }

    public void onClick(View view) {
        CheckinStepPagerFragment.lambda$updateHeaderAndFooter$0(this.arg$1, view);
    }
}
