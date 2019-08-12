package com.airbnb.android.places;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ResyController$$Lambda$4 implements Action1 {
    private final ResyController arg$1;

    private ResyController$$Lambda$4(ResyController resyController) {
        this.arg$1 = resyController;
    }

    public static Action1 lambdaFactory$(ResyController resyController) {
        return new ResyController$$Lambda$4(resyController);
    }

    public void call(Object obj) {
        this.arg$1.onRestaurantAvailabilityError((AirRequestNetworkException) obj);
    }
}
