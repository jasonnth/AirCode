package com.airbnb.android.lib.tripassistant;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.notifications.NotificationPreferencesGroups;
import com.airbnb.android.core.responses.ReservationsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetTripAssistantEligibleReservationsRequest extends BaseRequestV2<ReservationsResponse> {
    public Type successResponseType() {
        return ReservationsResponse.class;
    }

    public String getPath() {
        return NotificationPreferencesGroups.RESERVATIONS;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_trip_support_eligible").mo7897kv("trip_support_eligible", true);
    }
}
