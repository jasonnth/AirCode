package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.CheckInStep;
import com.google.common.base.Function;

final /* synthetic */ class ManageListingReorderCheckInStepsFragment$$Lambda$5 implements Function {
    private static final ManageListingReorderCheckInStepsFragment$$Lambda$5 instance = new ManageListingReorderCheckInStepsFragment$$Lambda$5();

    private ManageListingReorderCheckInStepsFragment$$Lambda$5() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((CheckInStep) obj).getId());
    }
}
