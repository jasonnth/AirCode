package com.airbnb.android.places.views;

import com.airbnb.android.core.models.RestaurantAvailability;
import com.google.common.base.Function;

final /* synthetic */ class ResyRow$$Lambda$1 implements Function {
    private final ResyRow arg$1;
    private final int arg$2;

    private ResyRow$$Lambda$1(ResyRow resyRow, int i) {
        this.arg$1 = resyRow;
        this.arg$2 = i;
    }

    public static Function lambdaFactory$(ResyRow resyRow, int i) {
        return new ResyRow$$Lambda$1(resyRow, i);
    }

    public Object apply(Object obj) {
        return this.arg$1.getTimeSlotModel((RestaurantAvailability) obj, this.arg$2);
    }
}
