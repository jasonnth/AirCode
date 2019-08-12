package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.data.models.HomeReservationObject;
import com.airbnb.android.itinerary.responses.HomeReservationObjectResponse;
import com.google.gson.jpush.Gson;
import p032rx.Observable;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.functions.Action1;
import p032rx.schedulers.Schedulers;

final /* synthetic */ class ItineraryDataController$$Lambda$11 implements Action1 {
    private final ItineraryDataController arg$1;

    private ItineraryDataController$$Lambda$11(ItineraryDataController itineraryDataController) {
        this.arg$1 = itineraryDataController;
    }

    public static Action1 lambdaFactory$(ItineraryDataController itineraryDataController) {
        return new ItineraryDataController$$Lambda$11(itineraryDataController);
    }

    public void call(Object obj) {
        Observable.fromCallable(ItineraryDataController$$Lambda$33.lambdaFactory$(this.arg$1, HomeReservationObject.builder().mo10310id(this.arg$1.reservationObjectId).reservation(new Gson().toJson(((HomeReservationObjectResponse) obj).reservation)).build())).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ItineraryDataController$$Lambda$34.lambdaFactory$(this.arg$1));
    }
}
