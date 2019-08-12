package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ReservationV1Response;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class SharedReservationRequest extends BaseRequestV2<ReservationV1Response> {
    private final String confirmationCode;

    public static SharedReservationRequest forSharedItinerary(String confirmationCode2) {
        return new SharedReservationRequest(confirmationCode2);
    }

    private SharedReservationRequest(String confirmationCode2) {
        this.confirmationCode = confirmationCode2;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "v1_legacy_long");
    }

    public String getPath() {
        return "reservations/" + this.confirmationCode;
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public long getCacheOnlyTimeoutMs() {
        return 3600000;
    }

    public Type successResponseType() {
        return ReservationV1Response.class;
    }
}
