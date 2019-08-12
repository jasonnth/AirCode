package com.airbnb.android.explore.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.PostHomeBookingResponse;
import java.lang.reflect.Type;

public class PostHomeBookingRequest extends BaseRequestV2<PostHomeBookingResponse> {
    private final String reservationId;

    public static PostHomeBookingRequest newInstance(String reservationId2) {
        return new PostHomeBookingRequest(reservationId2);
    }

    private PostHomeBookingRequest(String reservationId2) {
        this.reservationId = reservationId2;
    }

    public Type successResponseType() {
        return PostHomeBookingResponse.class;
    }

    public String getPath() {
        return "post_home_bookings/" + this.reservationId;
    }

    public long getCacheTimeoutMs() {
        return 1800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 300000;
    }
}
