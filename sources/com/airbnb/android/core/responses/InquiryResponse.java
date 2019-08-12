package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Inquiry;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InquiryResponse extends BaseResponse {
    @JsonProperty("thread")
    public Inquiry inquiry;
}
