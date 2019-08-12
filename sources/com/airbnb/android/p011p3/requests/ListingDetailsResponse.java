package com.airbnb.android.p011p3.requests;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.p011p3.models.ListingDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.p3.requests.ListingDetailsResponse */
public class ListingDetailsResponse extends BaseResponse {
    @JsonProperty
    public ListingDetails pdpListingDetail;
}
