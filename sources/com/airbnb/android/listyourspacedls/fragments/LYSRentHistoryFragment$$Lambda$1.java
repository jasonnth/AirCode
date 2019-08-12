package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.CalendarRulesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSRentHistoryFragment$$Lambda$1 implements Action1 {
    private final LYSRentHistoryFragment arg$1;

    private LYSRentHistoryFragment$$Lambda$1(LYSRentHistoryFragment lYSRentHistoryFragment) {
        this.arg$1 = lYSRentHistoryFragment;
    }

    public static Action1 lambdaFactory$(LYSRentHistoryFragment lYSRentHistoryFragment) {
        return new LYSRentHistoryFragment$$Lambda$1(lYSRentHistoryFragment);
    }

    public void call(Object obj) {
        LYSRentHistoryFragment.lambda$new$0(this.arg$1, (CalendarRulesResponse) obj);
    }
}
