package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.CheckinAmenityResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class UpdateCheckInInformationRequest extends BaseRequestV2<CheckinAmenityResponse> {
    private final long amenityId;
    private final CheckinInformationBody body;

    private static final class CheckinInformationBody {
        @JsonProperty("instruction")
        final String instruction;

        CheckinInformationBody(String instruction2) {
            this.instruction = instruction2;
        }
    }

    public UpdateCheckInInformationRequest(long amenityId2, String instruction) {
        this.amenityId = amenityId2;
        this.body = new CheckinInformationBody(instruction);
    }

    public String getPath() {
        return "listing_amenities/" + this.amenityId;
    }

    public Object getBody() {
        return this.body;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return CheckinAmenityResponse.class;
    }
}
