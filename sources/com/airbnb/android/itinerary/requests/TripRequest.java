package com.airbnb.android.itinerary.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.TimelineTripModel;
import com.airbnb.android.itinerary.responses.TripResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class TripRequest extends BaseRequestV2<TripResponse> {
    private static final String ARG_CONFIRMATON_CODE = "confirmation_code";
    private final String confirmationCode;

    public static TripRequest newInstance(String confirmationCode2) {
        return new TripRequest(confirmationCode2);
    }

    private TripRequest(String confirmationCode2) {
        this.confirmationCode = confirmationCode2;
    }

    public Type successResponseType() {
        return TripResponse.class;
    }

    public String getPath() {
        return TimelineTripModel.TRIP_SCHEDULE_CARDS;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv("confirmation_code", this.confirmationCode));
    }

    public long getCacheTimeoutMs() {
        return 1800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 300000;
    }
}
