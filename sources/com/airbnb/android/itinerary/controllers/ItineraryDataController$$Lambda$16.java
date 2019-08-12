package com.airbnb.android.itinerary.controllers;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.itinerary.data.models.BaseReservationObject;
import p032rx.functions.Action1;

final /* synthetic */ class ItineraryDataController$$Lambda$16 implements Action1 {
    private final ItineraryDataController arg$1;

    private ItineraryDataController$$Lambda$16(ItineraryDataController itineraryDataController) {
        this.arg$1 = itineraryDataController;
    }

    public static Action1 lambdaFactory$(ItineraryDataController itineraryDataController) {
        return new ItineraryDataController$$Lambda$16(itineraryDataController);
    }

    public void call(Object obj) {
        this.arg$1.notifyReservationObjectContentUpdated((BaseReservationObject) null, ((AirRequestNetworkException) obj).getMessage());
    }
}
