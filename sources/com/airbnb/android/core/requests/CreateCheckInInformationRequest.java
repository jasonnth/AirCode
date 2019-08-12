package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.CheckinAmenityResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class CreateCheckInInformationRequest extends BaseRequestV2<CheckinAmenityResponse> {
    private final CreateCheckinInformationBody body;

    private static final class CreateCheckinInformationBody {
        @JsonProperty("amenity_id")
        final long amenityId;
        @JsonProperty("instruction")
        final String instruction;
        @JsonProperty("listing_id")
        final long listingId;

        CreateCheckinInformationBody(long amenityId2, String instruction2, long listingId2) {
            this.amenityId = amenityId2;
            this.instruction = instruction2;
            this.listingId = listingId2;
        }
    }

    public CreateCheckInInformationRequest(long amenityId, String instruction, long listingId) {
        this.body = new CreateCheckinInformationBody(amenityId, instruction, listingId);
    }

    public String getPath() {
        return "listing_amenities/";
    }

    public Object getBody() {
        return this.body;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return CheckinAmenityResponse.class;
    }
}
