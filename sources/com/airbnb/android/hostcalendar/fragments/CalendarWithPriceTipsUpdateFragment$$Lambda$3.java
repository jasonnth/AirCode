package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.core.models.CalendarDay;
import com.google.common.base.Predicate;

final /* synthetic */ class CalendarWithPriceTipsUpdateFragment$$Lambda$3 implements Predicate {
    private static final CalendarWithPriceTipsUpdateFragment$$Lambda$3 instance = new CalendarWithPriceTipsUpdateFragment$$Lambda$3();

    private CalendarWithPriceTipsUpdateFragment$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return CalendarWithPriceTipsUpdateFragment.lambda$checkForBlackout$2((CalendarDay) obj);
    }
}
