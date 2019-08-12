package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class CalendarFragment$$Lambda$2 implements Action1 {
    private final CalendarFragment arg$1;

    private CalendarFragment$$Lambda$2(CalendarFragment calendarFragment) {
        this.arg$1 = calendarFragment;
    }

    public static Action1 lambdaFactory$(CalendarFragment calendarFragment) {
        return new CalendarFragment$$Lambda$2(calendarFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, CalendarFragment$$Lambda$4.lambdaFactory$(this.arg$1));
    }
}
