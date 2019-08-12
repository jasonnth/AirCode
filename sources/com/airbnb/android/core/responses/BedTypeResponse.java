package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.BedType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BedTypeResponse extends BaseResponse {
    @JsonProperty("listing_room_amenity")
    public BedType bedType;
}
