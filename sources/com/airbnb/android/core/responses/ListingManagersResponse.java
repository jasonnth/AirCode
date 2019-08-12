package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingManager;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ListingManagersResponse extends BaseResponse {
    @JsonProperty("listing_managers")
    public List<ListingManager> listingManagers;
}
