package com.airbnb.android.core.fragments.datepicker;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class DatesFragment$$Lambda$2 implements Action1 {
    private final DatesFragment arg$1;

    private DatesFragment$$Lambda$2(DatesFragment datesFragment) {
        this.arg$1 = datesFragment;
    }

    public static Action1 lambdaFactory$(DatesFragment datesFragment) {
        return new DatesFragment$$Lambda$2(datesFragment);
    }

    public void call(Object obj) {
        DatesFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
