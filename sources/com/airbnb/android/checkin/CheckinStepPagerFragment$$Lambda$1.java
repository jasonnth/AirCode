package com.airbnb.android.checkin;

import com.airbnb.airrequest.BaseResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CheckinStepPagerFragment$$Lambda$1 implements Action1 {
    private final CheckinStepPagerFragment arg$1;

    private CheckinStepPagerFragment$$Lambda$1(CheckinStepPagerFragment checkinStepPagerFragment) {
        this.arg$1 = checkinStepPagerFragment;
    }

    public static Action1 lambdaFactory$(CheckinStepPagerFragment checkinStepPagerFragment) {
        return new CheckinStepPagerFragment$$Lambda$1(checkinStepPagerFragment);
    }

    public void call(Object obj) {
        CheckinStepPagerFragment.lambda$new$2(this.arg$1, (BaseResponse) obj);
    }
}
