package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SelectListing;
import com.airbnb.android.core.models.SelectListingMetadata;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SelectListingResponse extends BaseResponse {
    @JsonProperty("metadata")
    public SelectListingMetadata metadata;
    @JsonProperty("select_listing")
    public SelectListing selectListing;
}
