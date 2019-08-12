package com.airbnb.android.places.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.places.responses.PlaceActivityResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class PlaceActivityRequest extends BaseRequestV2<PlaceActivityResponse> {
    private static final String FORMAT = "activity_pdp_mobile";

    /* renamed from: id */
    private final long f11169id;

    public static PlaceActivityRequest forId(long id) {
        return new PlaceActivityRequest(id);
    }

    private PlaceActivityRequest(long id) {
        this.f11169id = id;
    }

    public Type successResponseType() {
        return PlaceActivityResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, FORMAT);
    }

    public String getPath() {
        return "place_activities/" + this.f11169id;
    }
}
