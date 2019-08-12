package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingRegistrationSubmission;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingRegistrationSubmissionResponse extends BaseResponse {
    @JsonProperty("listing_registration_submission")
    public ListingRegistrationSubmission listingRegistrationSubmission;
}
