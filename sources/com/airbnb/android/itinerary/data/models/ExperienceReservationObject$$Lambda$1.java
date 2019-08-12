package com.airbnb.android.itinerary.data.models;

import com.airbnb.android.itinerary.ExperienceReservationModel;
import com.airbnb.android.itinerary.ExperienceReservationModel.Creator;

final /* synthetic */ class ExperienceReservationObject$$Lambda$1 implements Creator {
    private static final ExperienceReservationObject$$Lambda$1 instance = new ExperienceReservationObject$$Lambda$1();

    private ExperienceReservationObject$$Lambda$1() {
    }

    public static Creator lambdaFactory$() {
        return instance;
    }

    public ExperienceReservationModel create(String str, String str2) {
        return new AutoValue_ExperienceReservationObject(str, str2);
    }
}
