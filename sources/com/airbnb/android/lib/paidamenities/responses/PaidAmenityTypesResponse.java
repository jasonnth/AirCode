package com.airbnb.android.lib.paidamenities.responses;

import com.airbnb.android.core.models.PaidAmenityCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PaidAmenityTypesResponse {
    @JsonProperty("paid_amenity_types")
    public List<PaidAmenityCategory> paidAmenityCategories;
}
