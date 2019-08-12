package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SimilarListing;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SimilarListingsResponse extends BaseResponse {
    @JsonProperty("similar_listings")
    public List<SimilarListing> similarListings;
}
