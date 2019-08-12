package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.CheckInTimeOption;
import com.google.common.base.Predicate;

final /* synthetic */ class CheckInOutSettingsHelper$$Lambda$6 implements Predicate {
    private final CheckInOutSettingsHelper arg$1;

    private CheckInOutSettingsHelper$$Lambda$6(CheckInOutSettingsHelper checkInOutSettingsHelper) {
        this.arg$1 = checkInOutSettingsHelper;
    }

    public static Predicate lambdaFactory$(CheckInOutSettingsHelper checkInOutSettingsHelper) {
        return new CheckInOutSettingsHelper$$Lambda$6(checkInOutSettingsHelper);
    }

    public boolean apply(Object obj) {
        return CheckInOutSettingsHelper.lambda$null$2(this.arg$1, (CheckInTimeOption) obj);
    }
}
