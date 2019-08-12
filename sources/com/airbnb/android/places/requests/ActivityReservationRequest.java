package com.airbnb.android.places.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.places.responses.ActivityReservationResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class ActivityReservationRequest extends BaseRequestV2<ActivityReservationResponse> {
    private static final String ACTIVITY_ID = "activity_id";
    private static final String ENDS_AT = "ends_at";
    private static final String PLACE_ID = "place_id";
    private static final String STARTS_AT = "starts_at";
    private final long activityId;
    private final String endTime;
    private final long placeId;
    private final String startTime;

    public static ActivityReservationRequest forReservation(long placeId2, long activityId2, String startTime2, String endTime2) {
        return new ActivityReservationRequest(placeId2, activityId2, startTime2, endTime2);
    }

    private ActivityReservationRequest(long placeId2, long activityId2, String startTime2, String endTime2) {
        this.placeId = placeId2;
        this.activityId = activityId2;
        this.startTime = startTime2;
        this.endTime = endTime2;
    }

    public Type successResponseType() {
        return ActivityReservationResponse.class;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getPath() {
        return "activity_reservations/";
    }

    public Object getBody() {
        return Strap.make().mo11638kv(PLACE_ID, this.placeId).mo11638kv("activity_id", this.activityId).mo11639kv("starts_at", this.startTime).mo11639kv("ends_at", this.endTime);
    }
}
