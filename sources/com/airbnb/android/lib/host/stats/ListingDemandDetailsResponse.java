package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingDemandDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ListingDemandDetailsResponse extends BaseResponse {
    @JsonProperty("metadata")
    public ListingDemandDetails aggregateDemandDetails;
    @JsonProperty("listings")
    public List<ListingDemandDetails> listingDemandDetails;
}
