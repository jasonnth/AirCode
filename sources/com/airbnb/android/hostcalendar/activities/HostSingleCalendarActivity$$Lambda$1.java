package com.airbnb.android.hostcalendar.activities;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostSingleCalendarActivity$$Lambda$1 implements Action1 {
    private final HostSingleCalendarActivity arg$1;

    private HostSingleCalendarActivity$$Lambda$1(HostSingleCalendarActivity hostSingleCalendarActivity) {
        this.arg$1 = hostSingleCalendarActivity;
    }

    public static Action1 lambdaFactory$(HostSingleCalendarActivity hostSingleCalendarActivity) {
        return new HostSingleCalendarActivity$$Lambda$1(hostSingleCalendarActivity);
    }

    public void call(Object obj) {
        HostSingleCalendarActivity.lambda$new$0(this.arg$1, (ListingResponse) obj);
    }
}
