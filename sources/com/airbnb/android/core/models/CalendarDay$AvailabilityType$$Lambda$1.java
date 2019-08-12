package com.airbnb.android.core.models;

import com.airbnb.android.core.models.CalendarDay.AvailabilityType;
import com.google.common.base.Predicate;

final /* synthetic */ class CalendarDay$AvailabilityType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private CalendarDay$AvailabilityType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new CalendarDay$AvailabilityType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((AvailabilityType) obj).updateRequestValue.equalsIgnoreCase(this.arg$1);
    }
}
