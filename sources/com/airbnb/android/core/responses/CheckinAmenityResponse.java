package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CheckInAmenityInformation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckinAmenityResponse extends BaseResponse {
    @JsonProperty("listing_amenity")
    public CheckInAmenityInformation checkinAmenity;
}
