package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.CheckinAmenityResponse;
import java.lang.reflect.Type;

public class DeleteCheckInInformationRequest extends BaseRequestV2<CheckinAmenityResponse> {
    private final long listingAmenityId;

    public DeleteCheckInInformationRequest(long listingAmenityId2) {
        this.listingAmenityId = listingAmenityId2;
    }

    public String getPath() {
        return "listing_amenities/" + this.listingAmenityId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return CheckinAmenityResponse.class;
    }
}
