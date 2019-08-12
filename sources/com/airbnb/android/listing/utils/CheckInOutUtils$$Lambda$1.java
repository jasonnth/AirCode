package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.CheckInTimeOption;
import com.google.common.base.Predicate;

final /* synthetic */ class CheckInOutUtils$$Lambda$1 implements Predicate {
    private final String arg$1;

    private CheckInOutUtils$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new CheckInOutUtils$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return CheckInOutUtils.lambda$getTimeOptionForHour$0(this.arg$1, (CheckInTimeOption) obj);
    }
}
