package com.airbnb.android.listing.utils;

import android.content.Context;
import com.airbnb.android.core.models.TurnoverDaysSetting;
import p032rx.functions.Func1;

final /* synthetic */ class AvailabilitySettingsHelper$$Lambda$8 implements Func1 {
    private final Context arg$1;

    private AvailabilitySettingsHelper$$Lambda$8(Context context) {
        this.arg$1 = context;
    }

    public static Func1 lambdaFactory$(Context context) {
        return new AvailabilitySettingsHelper$$Lambda$8(context);
    }

    public Object call(Object obj) {
        return PreparationTimeDisplay.getLongValue(this.arg$1, (TurnoverDaysSetting) obj);
    }
}