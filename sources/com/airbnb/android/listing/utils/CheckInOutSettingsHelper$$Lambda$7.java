package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.CheckInTimeOption;
import p032rx.functions.Func1;

final /* synthetic */ class CheckInOutSettingsHelper$$Lambda$7 implements Func1 {
    private static final CheckInOutSettingsHelper$$Lambda$7 instance = new CheckInOutSettingsHelper$$Lambda$7();

    private CheckInOutSettingsHelper$$Lambda$7() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((CheckInTimeOption) obj).getLocalizedHour();
    }
}
