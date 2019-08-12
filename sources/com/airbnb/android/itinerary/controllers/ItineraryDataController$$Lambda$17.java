package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.data.models.ExperienceReservationObject;
import com.airbnb.android.itinerary.responses.ExperienceReservationObjectResponse;
import com.google.gson.jpush.Gson;
import p032rx.Observable;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.functions.Action1;
import p032rx.schedulers.Schedulers;

final /* synthetic */ class ItineraryDataController$$Lambda$17 implements Action1 {
    private final ItineraryDataController arg$1;

    private ItineraryDataController$$Lambda$17(ItineraryDataController itineraryDataController) {
        this.arg$1 = itineraryDataController;
    }

    public static Action1 lambdaFactory$(ItineraryDataController itineraryDataController) {
        return new ItineraryDataController$$Lambda$17(itineraryDataController);
    }

    public void call(Object obj) {
        Observable.fromCallable(ItineraryDataController$$Lambda$29.lambdaFactory$(this.arg$1, ExperienceReservationObject.builder().mo10303id(this.arg$1.reservationObjectId).reservation(new Gson().toJson(((ExperienceReservationObjectResponse) obj).reservation)).build())).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ItineraryDataController$$Lambda$30.lambdaFactory$(this.arg$1));
    }
}
