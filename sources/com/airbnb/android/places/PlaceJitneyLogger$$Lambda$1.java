package com.airbnb.android.places;

import com.airbnb.android.core.models.RestaurantAvailability;
import com.google.common.base.Function;

final /* synthetic */ class PlaceJitneyLogger$$Lambda$1 implements Function {
    private final PlaceJitneyLogger arg$1;

    private PlaceJitneyLogger$$Lambda$1(PlaceJitneyLogger placeJitneyLogger) {
        this.arg$1 = placeJitneyLogger;
    }

    public static Function lambdaFactory$(PlaceJitneyLogger placeJitneyLogger) {
        return new PlaceJitneyLogger$$Lambda$1(placeJitneyLogger);
    }

    public Object apply(Object obj) {
        return this.arg$1.jitneyfy((RestaurantAvailability) obj);
    }
}
