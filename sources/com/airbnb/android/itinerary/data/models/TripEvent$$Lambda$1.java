package com.airbnb.android.itinerary.data.models;

import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.itinerary.TripEventModel;
import com.airbnb.android.itinerary.TripEventModel.Creator;
import java.util.ArrayList;

final /* synthetic */ class TripEvent$$Lambda$1 implements Creator {
    private static final TripEvent$$Lambda$1 instance = new TripEvent$$Lambda$1();

    private TripEvent$$Lambda$1() {
    }

    public static Creator lambdaFactory$() {
        return instance;
    }

    public TripEventModel create(String str, String str2, Long l, TripEventCardType tripEventCardType, String str3, String str4, String str5, AirDateTime airDateTime, AirDateTime airDateTime2, String str6, String str7, String str8, String str9, ArrayList arrayList) {
        return new AutoValue_TripEvent(str, str2, l, tripEventCardType, str3, str4, str5, airDateTime, airDateTime2, str6, str7, str8, str9, arrayList);
    }
}
