package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Function;

final /* synthetic */ class HostReviewDetailsRequest$$Lambda$1 implements Function {
    private static final HostReviewDetailsRequest$$Lambda$1 instance = new HostReviewDetailsRequest$$Lambda$1();

    private HostReviewDetailsRequest$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return HostReviewDetailsRequest.lambda$new$0((Listing) obj);
    }
}
