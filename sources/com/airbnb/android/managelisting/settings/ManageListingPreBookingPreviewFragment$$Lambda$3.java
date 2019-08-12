package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.PreBookingQuestion;
import com.google.common.base.Function;

final /* synthetic */ class ManageListingPreBookingPreviewFragment$$Lambda$3 implements Function {
    private static final ManageListingPreBookingPreviewFragment$$Lambda$3 instance = new ManageListingPreBookingPreviewFragment$$Lambda$3();

    private ManageListingPreBookingPreviewFragment$$Lambda$3() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ManageListingPreBookingPreviewFragment.lambda$formatQuestions$2((PreBookingQuestion) obj);
    }
}
