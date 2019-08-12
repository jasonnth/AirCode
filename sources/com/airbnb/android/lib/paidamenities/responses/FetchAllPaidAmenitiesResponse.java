package com.airbnb.android.lib.paidamenities.responses;

import com.airbnb.android.core.models.PaidAmenity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FetchAllPaidAmenitiesResponse {
    @JsonProperty("paid_amenities")
    public List<PaidAmenity> paidAmenities;
}
