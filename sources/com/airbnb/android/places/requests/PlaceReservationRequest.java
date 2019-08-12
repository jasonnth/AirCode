package com.airbnb.android.places.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.places.responses.PlaceReservationResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class PlaceReservationRequest extends BaseRequestV2<PlaceReservationResponse> {
    private static final String PLACE_RESERVATION_ENDPOINT = "place_reservations/";
    private static final String SCHEDULED_PLACE_ID = "scheduled_place_id";
    private final long meetupId;

    public static PlaceReservationRequest forJoinMeetup(long meetupId2) {
        return new PlaceReservationRequest(meetupId2);
    }

    public PlaceReservationRequest(long meetupId2) {
        this.meetupId = meetupId2;
    }

    public Type successResponseType() {
        return PlaceReservationResponse.class;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getPath() {
        return PLACE_RESERVATION_ENDPOINT;
    }

    public Object getBody() {
        return Strap.make().mo11638kv(SCHEDULED_PLACE_ID, this.meetupId);
    }
}
