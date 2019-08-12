package com.airbnb.android.lib.paidamenities.responses;

import com.airbnb.android.core.models.PaidAmenity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaidAmenityResponse {
    @JsonProperty("paid_amenity")
    public PaidAmenity paidAmenity;
}
