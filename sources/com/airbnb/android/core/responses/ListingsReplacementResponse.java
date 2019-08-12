package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SimilarListing;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ListingsReplacementResponse extends BaseResponse {
    @JsonProperty("listing_replacements")
    public List<SimilarListing> listingReplacements;
}
