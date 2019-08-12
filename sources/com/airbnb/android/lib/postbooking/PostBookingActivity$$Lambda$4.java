package com.airbnb.android.lib.postbooking;

import com.airbnb.android.lib.businesstravel.models.IntentPrediction;
import com.airbnb.android.lib.businesstravel.models.IntentPredictionType;
import com.google.common.base.Predicate;

final /* synthetic */ class PostBookingActivity$$Lambda$4 implements Predicate {
    private static final PostBookingActivity$$Lambda$4 instance = new PostBookingActivity$$Lambda$4();

    private PostBookingActivity$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((IntentPrediction) obj).intentPredictionType().equals(IntentPredictionType.BusinessTravel);
    }
}
