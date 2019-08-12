package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingCheckInOptionsResponse extends BaseResponse {
    @JsonProperty("listing")
    public ListingCheckInTimeOptions checkInTimeOptions;
}
