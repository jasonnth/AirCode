package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Listing;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class HostRecentReviewsPerListingResponse extends BaseResponse {
    @JsonProperty("listings")
    List<Listing> listings;
}
