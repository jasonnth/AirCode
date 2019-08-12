package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingRegistrationOpenSubmission;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingRegistrationOpenSubmissionResponse extends BaseResponse {
    @JsonProperty("listing_registration_open_submission")
    public ListingRegistrationOpenSubmission openSubmission;
}
