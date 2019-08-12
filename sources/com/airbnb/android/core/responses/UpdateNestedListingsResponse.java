package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.NestedListing;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateNestedListingsResponse extends BaseResponse {
    @JsonProperty("nested_listing")
    private NestedListing nestedListing;

    public NestedListing getNestedListing() {
        return this.nestedListing;
    }
}
