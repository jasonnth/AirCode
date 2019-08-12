package com.airbnb.android.itinerary.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.responses.HomeReservationObjectResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class HomeReservationObjectRequest extends BaseRequestV2<HomeReservationObjectResponse> {
    private static final String DEFAULT_FORMAT = "for_mobile_itinerary_details";
    private static final String PENDING_FORMAT = "for_mobile_itinerary_pending";

    /* renamed from: id */
    private final String f8334id;
    private final boolean isPending;

    public static HomeReservationObjectRequest newInstance(String id, boolean isPending2) {
        return new HomeReservationObjectRequest(id, isPending2);
    }

    private HomeReservationObjectRequest(String id, boolean isPending2) {
        this.f8334id = id;
        this.isPending = isPending2;
    }

    public Type successResponseType() {
        return HomeReservationObjectResponse.class;
    }

    public String getPath() {
        return "reservations/" + this.f8334id;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, this.isPending ? PENDING_FORMAT : DEFAULT_FORMAT));
    }

    public long getCacheTimeoutMs() {
        return 1800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 300000;
    }
}
