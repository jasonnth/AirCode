package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingRegistration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingRegistrationResponse extends BaseResponse {
    @JsonProperty("listing_registration")
    public ListingRegistration listingRegistration;
}
