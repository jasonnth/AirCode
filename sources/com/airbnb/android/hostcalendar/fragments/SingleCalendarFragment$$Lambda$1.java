package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.core.responses.CalendarRulesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class SingleCalendarFragment$$Lambda$1 implements Action1 {
    private final SingleCalendarFragment arg$1;

    private SingleCalendarFragment$$Lambda$1(SingleCalendarFragment singleCalendarFragment) {
        this.arg$1 = singleCalendarFragment;
    }

    public static Action1 lambdaFactory$(SingleCalendarFragment singleCalendarFragment) {
        return new SingleCalendarFragment$$Lambda$1(singleCalendarFragment);
    }

    public void call(Object obj) {
        SingleCalendarFragment.lambda$new$0(this.arg$1, (CalendarRulesResponse) obj);
    }
}
