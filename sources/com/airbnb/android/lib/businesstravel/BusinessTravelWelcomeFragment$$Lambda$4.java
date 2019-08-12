package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Link;
import com.google.common.base.Function;

final /* synthetic */ class BusinessTravelWelcomeFragment$$Lambda$4 implements Function {
    private static final BusinessTravelWelcomeFragment$$Lambda$4 instance = new BusinessTravelWelcomeFragment$$Lambda$4();

    private BusinessTravelWelcomeFragment$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return BusinessTravelWelcomeFragment.lambda$convertLinksToUrlTexts$3((Link) obj);
    }
}
