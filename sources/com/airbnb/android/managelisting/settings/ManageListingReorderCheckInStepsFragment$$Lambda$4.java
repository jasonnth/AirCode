package com.airbnb.android.managelisting.settings;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

final /* synthetic */ class ManageListingReorderCheckInStepsFragment$$Lambda$4 implements Function {
    private final ImmutableMap arg$1;

    private ManageListingReorderCheckInStepsFragment$$Lambda$4(ImmutableMap immutableMap) {
        this.arg$1 = immutableMap;
    }

    public static Function lambdaFactory$(ImmutableMap immutableMap) {
        return new ManageListingReorderCheckInStepsFragment$$Lambda$4(immutableMap);
    }

    public Object apply(Object obj) {
        return this.arg$1.get((Long) obj);
    }
}
