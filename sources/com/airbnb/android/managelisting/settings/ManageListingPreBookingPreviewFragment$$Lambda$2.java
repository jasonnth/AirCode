package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.PreBookingQuestion;
import com.google.common.base.Predicate;

final /* synthetic */ class ManageListingPreBookingPreviewFragment$$Lambda$2 implements Predicate {
    private static final ManageListingPreBookingPreviewFragment$$Lambda$2 instance = new ManageListingPreBookingPreviewFragment$$Lambda$2();

    private ManageListingPreBookingPreviewFragment$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((PreBookingQuestion) obj).isChecked();
    }
}
