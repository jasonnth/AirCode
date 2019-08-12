package com.airbnb.android.itinerary.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.responses.PlaceReservationObjectResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class PlaceReservationObjectRequest extends BaseRequestV2<PlaceReservationObjectResponse> {
    private static final String DEFAULT_FORMAT = "for_mobile_itinerary_details";

    /* renamed from: id */
    private final String f8335id;

    public static PlaceReservationObjectRequest newInstance(String id) {
        return new PlaceReservationObjectRequest(id);
    }

    private PlaceReservationObjectRequest(String id) {
        this.f8335id = id;
    }

    public Type successResponseType() {
        return PlaceReservationObjectResponse.class;
    }

    public String getPath() {
        return "place_reservations/" + this.f8335id;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, DEFAULT_FORMAT));
    }

    public long getCacheTimeoutMs() {
        return 1800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 300000;
    }
}
