package com.airbnb.android.checkin;

import com.airbnb.android.core.models.CheckInStep;
import com.google.common.base.Function;

final /* synthetic */ class ViewCheckinActivity$$Lambda$13 implements Function {
    private static final ViewCheckinActivity$$Lambda$13 instance = new ViewCheckinActivity$$Lambda$13();

    private ViewCheckinActivity$$Lambda$13() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((CheckInStep) obj).getPictureUrl();
    }
}
