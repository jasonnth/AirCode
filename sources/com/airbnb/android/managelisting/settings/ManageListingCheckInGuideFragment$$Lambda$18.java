package com.airbnb.android.managelisting.settings;

import p032rx.functions.Func1;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$18 implements Func1 {
    private static final ManageListingCheckInGuideFragment$$Lambda$18 instance = new ManageListingCheckInGuideFragment$$Lambda$18();

    private ManageListingCheckInGuideFragment$$Lambda$18() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Integer.valueOf(((EditStepAction) obj).titleRes);
    }
}
