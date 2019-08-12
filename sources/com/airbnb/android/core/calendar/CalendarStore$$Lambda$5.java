package com.airbnb.android.core.calendar;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CalendarStore$$Lambda$5 implements Action1 {
    private final CalendarStore arg$1;
    private final CalendarStoreListener arg$2;

    private CalendarStore$$Lambda$5(CalendarStore calendarStore, CalendarStoreListener calendarStoreListener) {
        this.arg$1 = calendarStore;
        this.arg$2 = calendarStoreListener;
    }

    public static Action1 lambdaFactory$(CalendarStore calendarStore, CalendarStoreListener calendarStoreListener) {
        return new CalendarStore$$Lambda$5(calendarStore, calendarStoreListener);
    }

    public void call(Object obj) {
        this.arg$1.onError((AirRequestNetworkException) obj, this.arg$2);
    }
}
