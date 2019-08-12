package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.PhoneNumberConfirmation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneNumberVerificationResponse extends BaseResponse {
    @JsonProperty("mobile_confirmation")
    public PhoneNumberConfirmation confirmation;
}
