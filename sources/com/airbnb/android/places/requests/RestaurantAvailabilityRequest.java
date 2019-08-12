package com.airbnb.android.places.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.places.responses.RestaurantAvailabilityResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class RestaurantAvailabilityRequest extends BaseRequestV2<RestaurantAvailabilityResponse> {
    private static final String ACTIVITY_ID = "place_activity_id";
    private static final String DATE = "date";
    private static final String NUM_GUESTS = "num_guests";
    private final long activityId;
    private final AirDate date;
    private final int numGuests;

    public static RestaurantAvailabilityRequest forActivity(long activityId2, AirDate date2, int numGuests2) {
        return new RestaurantAvailabilityRequest(activityId2, date2, numGuests2);
    }

    private RestaurantAvailabilityRequest(long activityId2, AirDate date2, int numGuests2) {
        this.activityId = activityId2;
        this.date = date2;
        this.numGuests = numGuests2;
    }

    public Type successResponseType() {
        return RestaurantAvailabilityResponse.class;
    }

    public String getPath() {
        return "restaurant_availabilities";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv(ACTIVITY_ID, this.activityId).mo7895kv("date", this.date.getIsoDateString()).mo7893kv(NUM_GUESTS, this.numGuests);
    }
}
