package com.airbnb.android.checkin;

import com.airbnb.android.core.models.CheckInStep;
import com.google.common.base.Predicate;

final /* synthetic */ class ViewCheckinActivity$$Lambda$12 implements Predicate {
    private static final ViewCheckinActivity$$Lambda$12 instance = new ViewCheckinActivity$$Lambda$12();

    private ViewCheckinActivity$$Lambda$12() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ViewCheckinActivity.lambda$initPhotoInformation$11((CheckInStep) obj);
    }
}
