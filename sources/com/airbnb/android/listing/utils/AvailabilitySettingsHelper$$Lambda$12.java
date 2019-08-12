package com.airbnb.android.listing.utils;

import android.content.Context;
import p032rx.functions.Func1;

final /* synthetic */ class AvailabilitySettingsHelper$$Lambda$12 implements Func1 {
    private final Context arg$1;

    private AvailabilitySettingsHelper$$Lambda$12(Context context) {
        this.arg$1 = context;
    }

    public static Func1 lambdaFactory$(Context context) {
        return new AvailabilitySettingsHelper$$Lambda$12(context);
    }

    public Object call(Object obj) {
        return AdvanceNoticeDisplay.getRequestToBookValue(this.arg$1, ((Boolean) obj).booleanValue());
    }
}
