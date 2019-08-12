package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class ArrivalDetails$$Lambda$1 implements Predicate {
    private final CheckinTimeSelectionOptions arg$1;

    private ArrivalDetails$$Lambda$1(CheckinTimeSelectionOptions checkinTimeSelectionOptions) {
        this.arg$1 = checkinTimeSelectionOptions;
    }

    public static Predicate lambdaFactory$(CheckinTimeSelectionOptions checkinTimeSelectionOptions) {
        return new ArrivalDetails$$Lambda$1(checkinTimeSelectionOptions);
    }

    public boolean apply(Object obj) {
        return ((CheckinTimeSelectionOptions) obj).getFormattedHour().equalsIgnoreCase(this.arg$1.getFormattedHour());
    }
}
