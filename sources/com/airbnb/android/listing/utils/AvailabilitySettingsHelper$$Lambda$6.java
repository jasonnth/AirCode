package com.airbnb.android.listing.utils;

import android.content.Context;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import p032rx.functions.Func1;

final /* synthetic */ class AvailabilitySettingsHelper$$Lambda$6 implements Func1 {
    private final Context arg$1;

    private AvailabilitySettingsHelper$$Lambda$6(Context context) {
        this.arg$1 = context;
    }

    public static Func1 lambdaFactory$(Context context) {
        return new AvailabilitySettingsHelper$$Lambda$6(context);
    }

    public Object call(Object obj) {
        return FutureReservationsDisplay.getLongValue(this.arg$1, (MaxDaysNoticeSetting) obj);
    }
}
