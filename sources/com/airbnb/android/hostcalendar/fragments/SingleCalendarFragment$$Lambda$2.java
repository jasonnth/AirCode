package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class SingleCalendarFragment$$Lambda$2 implements Action1 {
    private final SingleCalendarFragment arg$1;

    private SingleCalendarFragment$$Lambda$2(SingleCalendarFragment singleCalendarFragment) {
        this.arg$1 = singleCalendarFragment;
    }

    public static Action1 lambdaFactory$(SingleCalendarFragment singleCalendarFragment) {
        return new SingleCalendarFragment$$Lambda$2(singleCalendarFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
