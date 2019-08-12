package com.airbnb.android.places;

import com.airbnb.android.places.responses.RestaurantAvailabilityResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ResyController$$Lambda$1 implements Action1 {
    private final ResyController arg$1;

    private ResyController$$Lambda$1(ResyController resyController) {
        this.arg$1 = resyController;
    }

    public static Action1 lambdaFactory$(ResyController resyController) {
        return new ResyController$$Lambda$1(resyController);
    }

    public void call(Object obj) {
        this.arg$1.onRestaurantAvailabilitySuccess(((RestaurantAvailabilityResponse) obj).getAvailabilities());
    }
}
