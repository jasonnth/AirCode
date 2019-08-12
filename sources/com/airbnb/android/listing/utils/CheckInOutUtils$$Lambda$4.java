package com.airbnb.android.listing.utils;

import com.google.common.base.Supplier;

final /* synthetic */ class CheckInOutUtils$$Lambda$4 implements Supplier {
    private final String arg$1;

    private CheckInOutUtils$$Lambda$4(String str) {
        this.arg$1 = str;
    }

    public static Supplier lambdaFactory$(String str) {
        return new CheckInOutUtils$$Lambda$4(str);
    }

    public Object get() {
        return CheckInOutUtils.lambda$getTimeOptionForHour$1(this.arg$1);
    }
}
