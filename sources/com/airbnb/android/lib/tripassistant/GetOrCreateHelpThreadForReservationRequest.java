package com.airbnb.android.lib.tripassistant;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class GetOrCreateHelpThreadForReservationRequest extends BaseRequestV2<HelpThreadResponse> {
    private final Body body;

    private static class Body {
        @JsonProperty("max_major_version")
        int maxMajorVerson;
        @JsonProperty("reservation_code")
        String reservationCode;

        public Body(String reservationCode2, int maxMajorVerson2) {
            this.reservationCode = reservationCode2;
            this.maxMajorVerson = maxMajorVerson2;
        }
    }

    public GetOrCreateHelpThreadForReservationRequest(String reservationCode, int maxMajorVersion) {
        this.body = new Body(reservationCode, maxMajorVersion);
    }

    public Type successResponseType() {
        return HelpThreadResponse.class;
    }

    public Body getBody() {
        return this.body;
    }

    public String getPath() {
        return "help_threads";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
