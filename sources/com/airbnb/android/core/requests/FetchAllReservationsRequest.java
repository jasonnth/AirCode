package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.responses.FetchAllReservationsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class FetchAllReservationsRequest extends BaseRequestV2<FetchAllReservationsResponse> {
    private final boolean isHost;
    private final long threadId;

    public FetchAllReservationsRequest(long threadId2, boolean isHost2) {
        this.threadId = threadId2;
        this.isHost = isHost2;
    }

    public Type successResponseType() {
        return FetchAllReservationsResponse.class;
    }

    public String getPath() {
        return "reservations/";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.isHost ? "for_mobile_host" : "for_mobile_guest").mo7897kv("all_reservations", true).mo7895kv("role", this.isHost ? TripRole.ROLE_KEY_HOST : TripRole.ROLE_KEY_GUEST).mo7894kv("thread_id", this.threadId);
    }
}
