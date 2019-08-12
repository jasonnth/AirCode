package com.airbnb.android.itinerary.data.models;

import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.itinerary.TimelineTripModel;
import com.airbnb.android.itinerary.TimelineTripModel.Creator;
import java.util.ArrayList;

final /* synthetic */ class TimelineTrip$$Lambda$1 implements Creator {
    private static final TimelineTrip$$Lambda$1 instance = new TimelineTrip$$Lambda$1();

    private TimelineTrip$$Lambda$1() {
    }

    public static Creator lambdaFactory$() {
        return instance;
    }

    public TimelineTripModel create(String str, AirDateTime airDateTime, AirDateTime airDateTime2, AirDateTime airDateTime3, String str2, String str3, String str4, String str5, ArrayList arrayList, String str6, String str7, ArrayList arrayList2, Boolean bool) {
        return new AutoValue_TimelineTrip(str, airDateTime, airDateTime2, airDateTime3, str2, str3, str4, str5, arrayList, str6, str7, arrayList2, bool);
    }
}
